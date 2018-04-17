package com.mo9.batman.dao.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:23 2018/4/17
 * @Modified By:
 */
@TableName("original_operate")
@Data
public class OriginalOperate {

    @TableId
    private Long id;

    /**
     * 营业周期_
     */
    private BigDecimal operationCycle;
    /**
     * 科目时间
     */
    private String time;
    /**
     * 存货周转天数_
     */
    private BigDecimal goodsRevolveDay;
    /**
     * 应收账款周转天数_
     */
    private BigDecimal needGatherCreditRevolveDay;
    /**
     * 存货周转率_
     */
    private BigDecimal goodsRevolveRate;
    /**
     * 股票代码
     */
    private String code;

}
