package com.gm.excel.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: KasonZzz
 * @Date: 2020/10/20 18 47
 * @Description:
 */
@Data
@ToString
public class SearchFileEx {

    @Excel(name = "序号")
    private String id;

    @Excel(name = "考生号")
    private String num;

    @Excel(name = "姓名")
    private String name;


}
