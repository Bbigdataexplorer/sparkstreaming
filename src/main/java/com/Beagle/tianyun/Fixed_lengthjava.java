package com.Beagle.tianyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * AUTHOR: Wanggc
 * Date : 2018/9/18 17:42
 */
public class Fixed_lengthjava {
    /*
    * 202110667022  兴业银行         1234567890
    202110667023  招商银行         2134556678
    202110667024  中国银行         2134556678
    202110667025  厦门国际银行     2134556678
    202110667026  建设银行         2134556678
    202110667027  交通银行上海分行 2134556678
    * */
    public static void main(String[] args) {
        String fileName="";
            File file = new File(fileName);
            BufferedReader reader = null;
            try {
                System.out.println("以行为单位读取文件内容，一次读一整行：");
                reader = new BufferedReader(new FileReader(file));
                String tempString = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    // 显示行号
                    System.out.println("line " + line + ": " + tempString);
                    line++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }
        }
}
