package com.gm.excel.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.gm.excel.pojo.*;
import com.gm.excel.utils.FileUtils;
import com.gm.excel.utils.KasonExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/19 13 35
 * @Description:
 */
@RestController
@RequestMapping("pic")
@Slf4j
public class SolvePicController {

    @PostMapping("excelSearchFile")
    public BaseResult excelSearchFile(@RequestBody MultipartFile[] files,@RequestBody MultipartFile xlsfile)throws Exception{
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        List<SearchFileEx> SearchFileExs = ExcelImportUtil.importExcel(xlsfile.getInputStream(), SearchFileEx.class, params);
        List<SolvePic> solvePics = new ArrayList<>();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String fileNameNoEx = FileUtils.getFileNameNoEx(originalFilename);
            String Ex = originalFilename.substring(originalFilename.lastIndexOf("."));
            for (SearchFileEx searchFileEx : SearchFileExs) {
                if (fileNameNoEx.equals(searchFileEx.getNum())){
                    byte[] bytes = file.getBytes();
                    String base64= Base64.getEncoder().encodeToString(bytes);
                    SolvePic solvePic = new SolvePic(searchFileEx.getNum()+searchFileEx.getName()+Ex,base64);
                    solvePics.add(solvePic);
                }
            }
        }
        return new BaseResult(solvePics);
    }






}
