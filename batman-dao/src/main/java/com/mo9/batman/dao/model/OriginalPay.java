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
@TableName("original_pay")
public class OriginalPay {

    @TableId
    private Long id;

    /**
     * 流动比率_
     */
    private BigDecimal currentRate;
    /**
     * 速动比率_
     */
    private BigDecimal quickRate;
    /**
     * 产权比率_
     */
    private BigDecimal propertyRightsRate;
    /**
     * 科目时间
     */
    private String time;
    /**
     * 股票代码
     */
    private BigDecimal conservativeQuickRate;

    /**
     * 保守速动比率_
     */
    private String code;

}
