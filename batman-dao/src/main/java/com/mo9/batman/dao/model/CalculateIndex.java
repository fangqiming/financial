package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:21 2018/4/17
 * @Modified By:
 */
@Data
@TableName("calculate_index")
public class CalculateIndex {
    @TableId
    private Long id;
    /**
     * 每股净资产
     */
    private BigDecimal NetAssetsPerShare;
    /**
     * 解禁数量
     */
    private BigDecimal liftBanNumber;
    /**
     * '市盈率静态'
     */
    private BigDecimal PE_S;
    /**
     * '每股未分配利润'
     */
    private BigDecimal UDPPS;
    /**
     * '所属行业'
     */
    private String industry;
    /**
     * '净资产收益率'
     */
    private BigDecimal ROE;
    /**
     * '占总股本比例'
     */
    private BigDecimal inTotalEquityRate;
    /**
     * '流通A股
     */
    private BigDecimal circulateAStock;
    /**
     * '每股经营现金流'
     */
    private BigDecimal perManageCacheFLow;
    /**
     * '毛利率'
     */
    private BigDecimal grossProfitRate;
    /**
     * '市净率'
     */
    private BigDecimal pb;
    /**
     * '股票代码'
     */
    private String code;
    /**
     * '每股收益'
     */
    private BigDecimal perEarnings;
    /**
     * '总市值'
     */
    private BigDecimal totalMarketValue;

    /**
     * '净利润'
     */
    private BigDecimal netProfit;
    /**
     * '解禁股份类型'
     */
    private String liftBanStockType;
    /**
     * '市盈率(动态)'
     */
    private BigDecimal pe_d;
    /**
     * '营业收入'
     */
    private BigDecimal operatingIncome;
    /**
     * '总股本'
     */
    private BigDecimal totalEquity;
    /**
     * 时间
     */
    private String time;
    /**
     * '每股资本公积金'
     */
    private BigDecimal perCapitalReserves;
}
