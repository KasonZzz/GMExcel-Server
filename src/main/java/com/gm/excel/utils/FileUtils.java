package com.gm.excel.utils;

import org.junit.Test;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/22 09 19
 * @Description:
 */
public class FileUtils {
    /**
     *获取文件名
     * @param filename 获取文件名
     * @return 文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取文件后缀名
     * @param filename 文件名称
     * @return 文件后缀名
     */
    public static String getFileEx(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    @Test
    public void fileTest(){
        String filename = "kaso.n.jpg";
        System.out.println(getFileNameNoEx(filename));
        System.out.println(getFileEx(filename));
    }


}
