package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:24 2018/4/17
 * @Modified By:
 */
@Data
@TableName("summary_benefit")
public class SummaryBenefit {

    @TableId
    private Long id;
    /**
     * 营业总收入_元
     */
    private BigDecimal operationTotalIncome;
    /**
     * 科目时间
     */
    private String time;
    /**
     * 每股净资产_元
     */
    private BigDecimal perNetAsset;
    /**
     * 净资产收益率_
     */
    private BigDecimal netAssetEarningsRate;
    /**
     * 销售毛利率_
     */
    private BigDecimal saleGrossProfitRate;
    /**
     * 股票代码
     */
    private String code;
    /**
     * 扣非净利润_元
     */
    private BigDecimal ridNotNetProfit;
    /**
     * 每股资本公积金_元
     */
    private BigDecimal perCapitalReserves;
    /**
     * 营业总收入同比增长率_
     */
    private BigDecimal operationTotalIncomeYoyIncrease;
    /**
     * 净利润_元
     */
    private BigDecimal netProfit;
    /**
     * 扣非净利润同比增长率_
     */
    private BigDecimal ridNotNetProfitYoyIncreaseRate;
    /**
     * 销售净利率_
     */
    private BigDecimal saleNetProfit;
    /**
     * 净利润同比增长率_
     */
    private BigDecimal netProfitYoyIncreaseRate;
    /**
     * 每股未分配利润_元
     */
    private BigDecimal perNotAllotProfit;
    /**
     * 净资产收益率_摊薄_
     */
    private BigDecimal netAssetEarningsRateDilute;
    /**
     * 每股经营现金流_元
     */
    private BigDecimal perManageCashFlow;
    /**
     * 基本每股收益_元
     */
    private BigDecimal generalPerEarnings;
    /**
     * 资产负债比率_
     */
    private BigDecimal assetDebtRate;
    /**
     * 存货周转率_
     */
    private BigDecimal goodsRevolveRate;

}
