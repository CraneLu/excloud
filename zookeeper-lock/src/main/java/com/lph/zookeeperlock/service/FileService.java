package com.lph.zookeeperlock.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;

@Service
public class FileService {

    public void addOne(){
        String path = "D:/zk.txt";
        String content = this.readFile(path);
        if(StringUtils.isEmpty(content)){
            return;
        }
        Integer num = Integer.valueOf(content) + 1;
        this.writeFile(path, num.toString());

    }

    /**
     * 读入TXT文件
     */
    private String readFile(String path) {
        String content = "";
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("...读取文件失败："+e.getMessage());
        }
        return content;
    }

    /**
     * 写入TXT文件
     */
    private void writeFile(String path, String content) {
        try {
            File writeName = new File(path);
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(content);
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            System.out.println("...写入文件失败："+e.getMessage());
        }
    }

}
