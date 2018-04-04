package com.mo9.batman.impl;

import com.mo9.batman.config.StockSuffixConfig;
import com.mo9.batman.entity.financial.FinancialBTO;
import com.mo9.batman.service.FinancialStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:13 2018/4/3
 * @Modified By:
 */
@Component
public class FinancialStatementServiceImpl implements FinancialStatementService {

    @Autowired
    private StockApiServiceImpl stockApiService;

    @Autowired
    private StockSuffixConfig stockSuffixConfig;

    private List<String> suffix = Arrays.asList("debt", "benefit", "cash", "bank", "grow", "pay", "operate", "each");

    @Override
    public FinancialBTO getDebt(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getDebtUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getBenefit(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getBenefitUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getCash(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getCashUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getBank(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getBankUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getGrow(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getGrowUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getPay(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getPayUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getOperate(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getOperateUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public FinancialBTO getEach(String stockCode) {
        if (!StringUtils.isEmpty(stockCode)) {
            FinancialBTO result = stockApiService.get(stockCode + stockSuffixConfig.getEachUrlSuffix());
            result.setStockCode(stockCode);
            return result;
        }
        return null;
    }

    @Override
    public Map<String, FinancialBTO> getOriginalFinancial(String stockCode) {
        Map<String, FinancialBTO> result = new HashMap<>(suffix.size());
        if (!StringUtils.isEmpty(stockCode)) {
            for (String name : suffix) {
                FinancialBTO financialBTO = stockApiService.get(stockCode + "_" + name + ".json");
                System.out.println(stockCode+"  "+name);
                if (Objects.nonNull(financialBTO)) {
                    financialBTO.setStockCode(stockCode);
                    result.put(name, financialBTO);
                }

                //停顿2秒钟防止Ip被封
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }

            }
        }
        return result;
    }
}
