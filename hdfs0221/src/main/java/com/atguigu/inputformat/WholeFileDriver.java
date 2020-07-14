package com.atguigu.inputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WholeFileDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        //2.设置我们的类路径（Classpath）
        job.setJarByClass(WholeFileDriver.class);
        //3.设置Mapper和Reducer

        //4。设置map和reduced输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //设置输入输出数据
        job.setInputFormatClass(WholeFileInputFormat.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交我们的job
        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
