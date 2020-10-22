package com.gm.excel.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/18 23 07
 * @Description:
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SolveExcel implements Serializable {
    @Excel(name = "位置（position）",width = 20)
    private String position;
    @Excel(name = "表1值",width = 20)
    private String tabValue;
    @Excel(name = "表2值",width = 20)
    private String antabValue;
}
