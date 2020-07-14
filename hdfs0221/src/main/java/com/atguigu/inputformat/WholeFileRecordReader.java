package com.atguigu.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 处理一个文件
 */
public class WholeFileRecordReader extends RecordReader<Text, BytesWritable> {
    private boolean notRead = true;
    private Text key =new Text();
    private BytesWritable value = new BytesWritable();
    private FSDataInputStream inputStream;
    private FileSplit fs;
    /**
     * 初始化方法,开始时调用
     * @param inputSplit
     * @param taskAttemptContext
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        fs = (FileSplit) inputSplit;
        Path path = fs.getPath();
        FileSystem fileSystem = path.getFileSystem(taskAttemptContext.getConfiguration());
        inputStream = fileSystem.open(path);
    }

    /**
     * 类似迭代器
     * @return
     * @throws IOException
     * @throws InterruptedException
     */

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(notRead){
            //读文件的过程
            //读key
            key.set(fs.getPath().toString());
            //读value
            byte[] buf = new byte[(int) fs.getLength()];
            inputStream.read(buf);
            value.set(buf,0,buf.length);
            notRead = false;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return notRead?0:1;
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeStream(inputStream);
    }
}
