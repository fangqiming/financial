package com.mo9.batman.entity.change;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 10:39 2018/3/26
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialChangeIndexBO {
    /**
     * 日期
     */
    private String data;

    List<FinancialChangeIndex> index;

}
