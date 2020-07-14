package com.atguigu.groupingcomparator;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {
    private OrderBean orderBean = new OrderBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        orderBean.setOrderID(fields[0]);
        orderBean.setProductID(fields[1]);
        orderBean.setPrice(Double.parseDouble(fields[2]));
        context.write(orderBean,NullWritable.get());

    }
}
