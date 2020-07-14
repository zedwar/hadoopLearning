package com.atguigu.hdfsclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSClient {
    @Test
    public void put() throws IOException, InterruptedException {
        // 根据给定的URI和用户名，访问hdfs的配置参数
        Configuration conf = new Configuration();

        // 允许访问datanode时使用域名，默认使用IP
        conf.set("dfs.client.use.datanode.hostname","true");
        // Hadoop的用户名,应当与远端启动hdfs进程的用户名相同，或者根据hdfs的安全设置自行配置
        String hdfsUserName = "root";

        URI hdfsUri = null;
        try {
            // NameNode的访问路径
            hdfsUri = new URI("hdfs://master:9000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            // 创建FileSystem对象,并调用copyToLocalFile函数将hdfs中的一个文件拷贝到本地目录
            FileSystem fs = FileSystem.get(hdfsUri, conf, hdfsUserName);
            fs.copyFromLocalFile(new Path("d:\\1.txt"),
                    new Path("/tmp"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void rename() throws IOException, InterruptedException {
        // 根据给定的URI和用户名，访问hdfs的配置参数
        Configuration conf = new Configuration();

        // 允许访问datanode时使用域名，默认使用IP
        conf.set("dfs.client.use.datanode.hostname","true");
        // Hadoop的用户名,应当与远端启动hdfs进程的用户名相同，或者根据hdfs的安全设置自行配置
        String hdfsUserName = "root";

        URI hdfsUri = null;
        try {
            // NameNode的访问路径
            hdfsUri = new URI("hdfs://master:9000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            // 创建FileSystem对象,并调用copyToLocalFile函数将hdfs中的一个文件拷贝到本地目录
            FileSystem fs = FileSystem.get(hdfsUri, conf, hdfsUserName);
            fs.rename(new Path("/test"),
                    new Path("/test2"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
