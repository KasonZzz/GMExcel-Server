package com.gm.excel.bean;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.view.EasypoiTemplateExcelView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/15 23 15
 * @Description:
 */
public class Test {
    @org.junit.Test
    public void  test(){
        String filePath = "C:\\Users\\Administrator\\Desktop\\1.xlsx";
        List<Object> apiInfos = readExcel(filePath, 0, Object.class);
        for (Object apiInfo : apiInfos) {
            System.out.println(apiInfo);
        }
    }

    public <E> List<E> readExcel(String filePath, int sheetIndex, Class<E> clazz) {
        // 定义输入流
        FileInputStream fis = null;
        List<E> datas = null;

        try {
            // 创建输入流对象
            fis = new FileInputStream(filePath);
            // 创建一个easypoi使用的配置类
            ImportParams params = new ImportParams();
            // 设置表格坐标
            params.setStartSheetIndex(sheetIndex);
            // 校验Excel文件，去掉空行
            params.setNeedVerfiy(true);
            // 读取数据
            datas = ExcelImportUtil.importExcel(fis, clazz, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return datas;
    }
}
