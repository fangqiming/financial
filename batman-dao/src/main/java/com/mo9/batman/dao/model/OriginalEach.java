package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 13:50 2018/4/17
 * @Modified By:
 */
@Data
@TableName("original_each")
public class OriginalEach {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * '每股资本公积金_元'
     */
    private BigDecimal perCapitalReserves;
    /**
     * 时间
     */
    private String time;
    /**
     * '每股净资产_元'
     */
    private BigDecimal perNetAsset;
    /**
     * '每股未分配利润_元'
     */
    private BigDecimal perNotAllotProfit;
    /**
     * '每股经营现金流_元'
     */
    private BigDecimal perManageCashFlow;
    /**
     * '基本每股收益_元'
     */
    private BigDecimal generalPerEarnings;
    /**
     * '股票代码'
     */
    private String code;
}
