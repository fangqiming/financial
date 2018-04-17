package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:25 2018/4/17
 * @Modified By:
 */
@Data
@TableName("original_grow")
public class OriginalGrow {

    @TableId
    private Long id;

    /**
     * 营业收入同比增长率_
     */
    private BigDecimal operationIncomeYoyGrowthRate;
    /**
     * 净资产收益率同比增长率_
     */
    private BigDecimal netAssetEarningsYoyGrowthRate;
    /**
     * 净利润同比增长率_
     */
    private BigDecimal netProfitYoyGrowthRate;
    /**
     * 每股收益同比增长率_
     */
    private BigDecimal perEarningsYoyGrowthRate;
    /**
     * 科目时间
     */
    private String time;
    /**
     * 股票代码
     */
    private String code;

}
