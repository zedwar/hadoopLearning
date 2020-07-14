package com.atguigu.mapjoin;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MJMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    private Map<String,String> pmap =new HashMap<>();
    private Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        String path = cacheFiles[0].getPath().toString();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((new FileInputStream(path))));
        String line;
        while(StringUtils.isNotEmpty(line = bufferedReader.readLine())){
            String[] fields =
                    line.split("\t");
            pmap.put(fields[0],fields[1]);
        }
        IOUtils.closeStream(bufferedReader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        String pname = pmap.get(fields[1]);
        k.set(fields[0]+"\t"+pname+"\t"+fields[2]);
        context.write(k,NullWritable.get());
    }
}
