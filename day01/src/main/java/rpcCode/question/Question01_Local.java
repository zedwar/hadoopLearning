package rpcCode.question;

import java.io.*;
import java.util.*;

/**
 * 求 TopN 的单线程版本
 **/
public class Question01_Local {

    public static void main(String[] args) {

        // 指定输入输出目录
        String inputPath = "C:\\bigdata-data\\nx_tc01\\question01\\input\\ask_2020.06.21.log";
        String outputPath = "C:\\bigdata-data\\nx_tc01\\question01\\output\\question_result01.txt";

        // 提供一个内存容器
        HashMap<String, Long> wordCountContext = new HashMap<String, Long>();

        // 读取数据
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputPath)));

            // 解析搜索问题，然后存储内存容器，做次数统计
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\t");
                String keyword = words[1];
                if (wordCountContext.containsKey(keyword)) {
                    wordCountContext.put(keyword, wordCountContext.get(keyword) + 1);
                } else {
                    wordCountContext.put(keyword, 1L);
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("指定文件" + inputPath + "不存在");
        } catch (IOException e) {
            System.out.println("读取文件内容异常");
        }

        // 排序
        List<Map.Entry<String, Long>> keyWordCountList =
                new ArrayList<Map.Entry<String, Long>>(wordCountContext.entrySet());
        Collections.sort(keyWordCountList, new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });

        // 写出结果到文件中
        try {
            // 控制输出的结果条数
            int topN = 3;
            int counter = 0;
            PrintWriter printWriter = new PrintWriter(new File(outputPath));
            for (Map.Entry<String, Long> keywordCount : keyWordCountList) {
                if(counter < topN){
                    System.out.println(keywordCount.getKey() +"\t" + keywordCount.getValue());
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("指定文件" + outputPath + "不存在");
        }
    }
}
