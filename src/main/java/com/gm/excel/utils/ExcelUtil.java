//package com.gm.excel.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellAddress;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.gm.excel.utils.KasonExcelUtils.RowCellMap;
//
///**
// * @Author: KasonZzz
// * @Date: 2020/10/15 23 27
// * @Description:
// */
//@Slf4j
//public class ExcelUtil {
//
//
//
//    /**
//     * 读入excel文件，解析后返回
//     * @param file
//     * @throws IOException
//     */
//    public static List<String[]> readExcel(MultipartFile file) throws IOException{
//        //检查文件
//        checkExcelFile(file);
//        //获得Workbook工作薄对象
//        Workbook workbook = getWorkBook(file);
//        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
//        List<String[]> list = new ArrayList<String[]>();
//        if(workbook != null){
//            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
//                //获得当前sheet工作表
//                Sheet sheet = workbook.getSheetAt(sheetNum);
//                if(sheet == null){
//                    continue;
//                }
//                //获得当前sheet的开始行
//                int firstRowNum  = sheet.getFirstRowNum();
//                //获得当前sheet的结束行
//                int lastRowNum = sheet.getLastRowNum();
//                //firstRowNum+1是循环除了第一行的所有行
//                for(int rowNum = firstRowNum;rowNum <= lastRowNum;rowNum++){
//                    //获得当前行
//                    Row row = sheet.getRow(rowNum);
//                    if(row == null){
//                        continue;
//                    }
//                    //获得当前行的开始列
//                    int firstCellNum = row.getFirstCellNum();
//                    //获得当前行的列数
//                    int lastCellNum = row.getPhysicalNumberOfCells();
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
//                    //循环当前行
//                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
//                        Cell cell = row.getCell(cellNum);
////                        cells[cellNum] = getCellValue(cell);
//                        HashMap<String, String> map = RowCellMap(cell);
//                        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
//                            System.out.println(stringStringEntry);
//                        }
//
//
//                    }
//                    list.add(cells);
//                }
//            }
//            workbook.close();
//        }
//        return list;
//    }
//
//
//
//
//    /**
//     * 查看文件格式是否正确
//     * @param file
//     * @throws IOException
//     */
//    public static void checkExcelFile(MultipartFile file) throws IOException {
//        //判断文件是否存在
//        if (null == file) {
//            log.error("文件不存在！");
//            throw new FileNotFoundException("文件不存在！");
//        }
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //判断文件是否是excel文件
//        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
//            log.error(fileName + "不是excel文件");
//            throw new IOException(fileName + "不是excel文件");
//        }
//    }
//
//    /**
//     * 获取工作簿
//     * @param file
//     * @return
//     */
//    public static Workbook getWorkBook(MultipartFile file) {
//        //获得文件名
//        String fileName = file.getOriginalFilename();
//        //创建Workbook工作薄对象，表示整个excel
//        Workbook workbook = null;
//        try {
//            //获取excel文件的io流
//            InputStream is = file.getInputStream();
//            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
//            if(fileName.endsWith("xls")){
//                //2003
//                workbook = new HSSFWorkbook(is);
//            }else if(fileName.endsWith("xlsx")){
//                //2007
//                workbook = new XSSFWorkbook(is);
//            }
//        } catch (IOException e) {
//            log.info(e.getMessage());
//        }
//        return workbook;
//    }
//
//
//
////    /** 通过 */
////    public static HashMap<String,String> RowCellMap(Cell cell){
////
////        String cellValue = "";
////        HashMap<String, String> map = new HashMap<>();
////        if (ObjectUtils.isEmpty(cell)){
////            return map;
////        }
////
////        String key = "";
////        if(!ObjectUtils.isEmpty(cell.getAddress())){
////            key = cell.getAddress().toString();
////        }
////        map.put(key,cellValue);
////        //把数字当成String来读，避免出现1读成1.0的情况
////        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
////            cell.setCellType(Cell.CELL_TYPE_STRING);
////        }
////        //判断数据的类型
////        switch (cell.getCellType()){
////            case Cell.CELL_TYPE_NUMERIC: //数字
////                cellValue = String.valueOf(cell.getNumericCellValue());
////                break;
////            case Cell.CELL_TYPE_STRING: //字符串
////                cellValue = String.valueOf(cell.getStringCellValue());
////                break;
////            case Cell.CELL_TYPE_BOOLEAN: //Boolean
////                cellValue = String.valueOf(cell.getBooleanCellValue());
////                break;
////            case Cell.CELL_TYPE_FORMULA: //公式
////                cellValue = String.valueOf(cell.getCellFormula());
////                break;
////            case Cell.CELL_TYPE_BLANK: //空值
////                cellValue = "";
////                break;
////            case Cell.CELL_TYPE_ERROR: //故障
////                cellValue = "非法字符";
////                break;
////            default:
////                cellValue = "未知类型";
////                break;
////        }
////
////        map.put(key,cellValue);
////
////
////        return map;
////    }
//
//
//
//
//
//
//    /**
//     * 获取Cell单元格的值
//     * @param cell
//     * @return
//     */
//    public static String getCellValue(Cell cell){
//        String cellValue = "";
//        if(cell == null){
//            return cellValue;
//        }
////        Row row = cell.getRow();
//        int columnIndex = cell.getColumnIndex();
//        int rowIndex = cell.getRowIndex();
//
//
////        System.out.println("getPhysicalNumberOfCells:"+row.getPhysicalNumberOfCells());
////        System.out.println("getFirstCellNum:"+row.getFirstCellNum());
////        System.out.println("getRowStyle:"+row.getRowStyle());
////        System.out.println("getZeroHeight:"+row.getZeroHeight());
//
//
//        //把数字当成String来读，避免出现1读成1.0的情况
//        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//        }
//        //判断数据的类型
//        switch (cell.getCellType()){
//            case Cell.CELL_TYPE_NUMERIC: //数字
//                cellValue = String.valueOf(cell.getNumericCellValue());
//                break;
//            case Cell.CELL_TYPE_STRING: //字符串
//                cellValue = String.valueOf(cell.getStringCellValue());
//                break;
//            case Cell.CELL_TYPE_BOOLEAN: //Boolean
//                cellValue = String.valueOf(cell.getBooleanCellValue());
//                break;
//            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
//                break;
//            case Cell.CELL_TYPE_BLANK: //空值
//                cellValue = "";
//                break;
//            case Cell.CELL_TYPE_ERROR: //故障
//                cellValue = "非法字符";
//                break;
//            default:
//                cellValue = "未知类型";
//                break;
//        }
//
//
//        return cellValue;
//    }
//
//
//}
