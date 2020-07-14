package com.atguigu.writablecoparable2;

import com.atguigu.writablecomparable.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition2 extends Partitioner<FlowBean, Text> {
    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
        String phone = text.toString();
        switch (phone.substring(0,3)){
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}
