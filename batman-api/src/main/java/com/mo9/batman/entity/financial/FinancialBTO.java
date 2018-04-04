package com.mo9.batman.entity.financial;

import com.mo9.batman.entity.FinancialIndexBO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:43 2018/4/3
 * @Modified By:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialBTO {
    private FinancialIndexBO fieldflashData;
    private FinancialIndexBO flashData;
    private String stockCode;
}
