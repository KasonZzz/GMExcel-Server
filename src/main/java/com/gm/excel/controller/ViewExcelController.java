package com.gm.excel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.gm.excel.pojo.BaseResult;
import com.gm.excel.pojo.SolveExcel;
import com.gm.excel.utils.KasonExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/15 22 54
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("view")
public class ViewExcelController {


    /**
     * 对比两个Excel
     * 如果type存在并且是字符串download则导出Excel
     * 否则返回数据
     * @param files 前端传来的多个Excel
     * @param type  是否下载，空或者“download”
     * @param response 设置下载的http文件属性
     * @return 下载返回字节流，不下载返回对象
     * @throws Exception 可能会抛出IOException 这里统一抛出
     */
    @PostMapping("getList")
    public BaseResult toHtmlOf07Base(@RequestBody MultipartFile[] files,String type,HttpServletResponse response)throws Exception  {
        if (ObjectUtils.isEmpty(files)){
            return new BaseResult("无文件上传");
        }
        if (files.length != 2){
            return new BaseResult("文件上传数目有问题");
        }
        Map<String, String> excelFiles1 =KasonExcelUtils.ReadExcel(files[0]);
        Map<String, String> excelFiles2 =  KasonExcelUtils.ReadExcel(files[1]);
        List<SolveExcel> list = compareMap(excelFiles1, excelFiles2);
        if (!ObjectUtils.isEmpty(type) && type.equals("download")){
            KasonExcelUtils.exportExcel(response,list);
        }
        return new BaseResult(list);
    }


    /**
     * 对比Excel解析出的Map
     * 将不同的元素存入对象
     * @param m1 EXCEL的值
     * @param m2 EXCEL的值
     * @return 不一样元素的集合
     */
    public static List<SolveExcel> compareMap(Map<String, String> m1,Map<String, String> m2){
        List<SolveExcel> list = new ArrayList<>();
        for(Map.Entry<String, String> entry1:m1.entrySet()){
            String m1value = entry1.getValue() == null?"":entry1.getValue();
            String m2value = m2.get(entry1.getKey())==null?"":m2.get(entry1.getKey());
            /* 若两个map中相同key对应的value不相等 */
            if (!m1value.equals(m2value)) {
                SolveExcel solveExcel = new SolveExcel(entry1.getKey(), m1value, m2value);
                list.add(solveExcel);
            }
        }
        return list;
    }



}
