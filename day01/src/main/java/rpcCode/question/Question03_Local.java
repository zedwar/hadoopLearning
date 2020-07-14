package rpcCode.question;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 判断某个用户是否存在于集合中
 **/
public class Question03_Local {

    public static void main(String[] args) {

        String inputDir = "C:\\bigdata-data\\nx_tc01\\question03\\input";
        HashSet<String> resultSet = new HashSet<>();
        readLast7DaysQuestion(resultSet, inputDir);

        String userID = "user7936454524";      // 存在的ID
//        String userID = "user7936454521";       // 不存在的ID

        if(resultSet.contains(userID)){
            System.out.println("这个用户["+userID+"]过去7天有登录");
        }else{
            System.out.println("这个用户["+userID+"]过去7天没有有登录");
        }
    }

    public static void readLast7DaysQuestion(Set<String> context, String inputDir){
        File inputDirectory = new File(inputDir);
        File[] files = inputDirectory.listFiles();
        for(File f: files){
            readQuestion(context, f.getAbsolutePath());
        }
    }

    public static void readQuestion(Set<String> context, String inputPath) {
        // 读取数据
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputPath)));

            // 解析搜索问题，然后存储内存容器，做次数统计
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\t");
                String keyword = words[2];
                context.add(keyword);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("指定文件" + inputPath + "不存在");
        } catch (IOException e) {
            System.out.println("读取文件内容异常");
        }
    }
}
