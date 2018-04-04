package com.mo9.batman.service;

import com.mo9.batman.entity.financial.FinancialBTO;

import java.util.Map;

/**
 * @Author:qmfang
 * @Description: 用于获取财务表原始数据信息
 * @Date:Created in 14:42 2018/4/3
 * @Modified By:
 */
public interface FinancialStatementService {

    /**
     * 用于获取资产负债表
     *
     * @param stockCode 股票代码
     * @return
     */
    FinancialBTO getDebt(String stockCode);

    /**
     * 用于获取利润表
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getBenefit(String stockCode);

    /**
     * 用于获取现金流量表
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getCash(String stockCode);

    /**
     * 用于获取银行专项指标
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getBank(String stockCode);

    /**
     * 用于获取成长能力
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getGrow(String stockCode);

    /**
     * 用于获取偿债能力
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getPay(String stockCode);


    /**
     * 用于获取运营能力
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getOperate(String stockCode);


    /**
     * 用于获取每股能力
     *
     * @param stockCode
     * @return
     */
    FinancialBTO getEach(String stockCode);

    /**
     * 用于一次性获取全部的相关数据
     *
     * @param stockCode
     * @return
     */
    Map<String, FinancialBTO> getOriginalFinancial(String stockCode);
}
