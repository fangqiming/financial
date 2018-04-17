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
@TableName("original_bank")
public class OriginalBank {

    @TableId
    private Long id;
    /**
     * 净息差_
     */
    private BigDecimal netInterestGap;
    /**
     * 存贷款比例_
     */
    private BigDecimal depositLoanRate;
    /**
     * 最大十家客户贷款比例_
     */
    private BigDecimal tenClientLoanRate;
    /**
     * 核心资本充足率_
     */
    private BigDecimal coreCapitalEnoughRate;
    /**
     * 科目时间
     */
    private String time;
    /**
     * 不良贷款_元
     */
    private BigDecimal badLoan;
    /**
     * 存款总额_元
     */
    private BigDecimal depositTotal;
    /**
     * 贷款总额_元
     */
    private BigDecimal loanTotal;
    /**
     * 单一最大客户贷款比例_
     */
    private BigDecimal maxClientLoanRate;
    /**
     * 核心资本净额_元
     */
    private BigDecimal coreCapitalNetAmount;
    /**
     * 非利息收入_元
     */
    private BigDecimal notInterestIncome;
    /**
     * 非利息收入占比_
     */
    private BigDecimal notInterestIncomeRate;
    /**
     * 资本充足率_
     */
    private BigDecimal capitalEnoughRate;
    /**
     * 股票代码
     */
    private String code;
    /**
     * 净利差_
     */
    private BigDecimal netEquityGap;

    /**
     * 资本净额_元
     */
    private BigDecimal AssetNetAmount;
    /**
     * 拨备覆盖率_
     */
    private BigDecimal setAsideCoverRate;
    /**
     * 不良贷款率_
     */
    private BigDecimal badLoanRate;
    /**
     * 加权风险资产净额_元
     */
    private BigDecimal weightingRiskNetAsset;
    /**
     * 短期资产流动性比例_
     */
    private BigDecimal shortAssetFlowRate;

}
