package com.mo9.batman.entity.change;

import com.mo9.batman.entity.MetaData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 11:51 2018/3/26
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialChangeIndex {
    /**
     * 变动科目
     */
    private MetaData changeSubject;

    /**
     * 本期指数
     */
    private MetaData currentValue;

    /**
     * 上期指数
     */
    private MetaData previousValue;

    /**
     * 变动幅度
     */
    private MetaData amplitude;

    /**
     * 变动原因
     */
    private MetaData reason;
}
