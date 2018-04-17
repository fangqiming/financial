package com.mo9.batman.dao.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 16:49 2018/4/17
 * @Modified By:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContinuousVo {

    @NotNull(message = "表名不能为空")
    private String tableName;
    @NotNull(message = "开始时间不能为空")
    private String start;
    @NotNull(message = "结束时间不能为空")
    private String end;
    @NotNull(message = "属性名不能为空")
    private String name;
    @NotNull(message = "值不能为空")
    private BigDecimal value;
    @NotNull(message = "比较符号不能为空 lt gt")
    private String symbol;

    private Integer gap;

    public ContinuousVo setGap() {
        this.gap = Integer.valueOf(end) - Integer.valueOf(start) + 1;
        return this;
    }
}
