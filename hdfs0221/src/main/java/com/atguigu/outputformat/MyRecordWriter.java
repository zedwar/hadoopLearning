package com.atguigu.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyRecordWriter extends RecordWriter<LongWritable, Text> {
    private FSDataOutputStream atguigu;
    private FSDataOutputStream other;
    public void initialize(TaskAttemptContext taskAttemptContext) throws IOException {
        String outdir = taskAttemptContext.getConfiguration().get(FileOutputFormat.OUTDIR);
        FileSystem fileSystem = FileSystem.get(taskAttemptContext.getConfiguration());
        atguigu = fileSystem.create(new Path(outdir + "/atguigu.log"));
        other = fileSystem.create(new Path(outdir + "/other.log"));
    }
    /**
     *
     * @param longWritable
     * @param text
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(LongWritable longWritable, Text text) throws IOException, InterruptedException {
        String out = text.toString() + "\n";
        if (out.contains("atguigu")){
            atguigu.write(out.getBytes());
        }else{
            other.write(out.getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);
    }
}
