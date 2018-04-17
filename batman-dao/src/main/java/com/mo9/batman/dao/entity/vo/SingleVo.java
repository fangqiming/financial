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
 * @Date:Created in 15:14 2018/4/17
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleVo {

    @NotNull(message = "时间不能为空")
    private String time;
    @NotNull(message = "属性名不能为空")
    private String name;
    @NotNull(message = "比较符号不能为空 lt gt")
    private String symbol;
    @NotNull(message = "表名不能为空")
    private String tableName;
    @NotNull(message = "值不能为空")
    private BigDecimal value;

}
