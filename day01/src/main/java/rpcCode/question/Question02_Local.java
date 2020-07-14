package rpcCode.question;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 求两个文件的 UserID 交集
 **/
public class Question02_Local {

    public static void main(String[] args) {

        // 读取数据
        String yesterdayInputPath = "C:\\bigdata-data\\nx_tc01\\question02\\input_yesterday\\ask_2020.06.20.log";
        String todayInputPath = "C:\\bigdata-data\\nx_tc01\\question02\\input_today\\ask_2020.06.21.log";
        Set<String> yesterdayResultSet = readQuestion(yesterdayInputPath);
        Set<String> todayResultSet = readQuestion(todayInputPath);

        // 求交集
        Set<String> result = new HashSet<>();
        result.addAll(yesterdayResultSet);
        result.retainAll(todayResultSet);

        // 存结果
        String outputPath = "C:\\bigdata-data\\nx_tc01\\question02\\output\\result.txt";
        writeResult(result, outputPath);
    }

    /**
     * 打印输出
     * @param result
     */
    public static void printResult(Set<String> result) {
        for (String record : result) {
            System.out.println(record);
        }
    }

    public static Set<String> readQuestion(String inputPath) {
        HashSet<String> resultSet = new HashSet<>();
        // 读取数据
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputPath)));

            // 解析搜索问题，然后存储内存容器，做次数统计
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\t");
                String keyword = words[2];
                resultSet.add(keyword);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("指定文件" + inputPath + "不存在");
        } catch (IOException e) {
            System.out.println("读取文件内容异常");
        }
        return resultSet;
    }

    public static void writeResult(Set<String> resultSet, String outputPath) {
        writeResult(resultSet, outputPath, Integer.MAX_VALUE);
    }

    /**
     * 写出结果
     *
     * @param resultSet
     */
    public static void writeResult(Set<String> resultSet, String outputPath, int topN) {
        // 写出结果到文件中
        try {

            PrintWriter printWriter = new PrintWriter(new File(outputPath));
            if (topN != Integer.MAX_VALUE) {
                // 控制输出的结果条数
                int counter = 0;
                for (String record : resultSet) {
                    if (counter < topN) {
                        printWriter.println(record);
                        counter++;
                    } else {
                        break;
                    }
                }
            } else {
                for (String record : resultSet) {
                    printWriter.println(record);
                }
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("指定输出文件" + outputPath + "不存在");
        }
    }
}
