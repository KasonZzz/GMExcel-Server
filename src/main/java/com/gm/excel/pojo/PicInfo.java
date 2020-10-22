package com.gm.excel.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/19 14 43
 * @Description:
 */
@Data
@ToString
public class PicInfo {
    @Excel(name = "学号")
    private String stuNum;
    @Excel(name = "姓名")
    private String stuName;
}
