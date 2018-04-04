package com.mo9.batman.impl;

import com.mo9.batman.entity.financial.FinancialBTO;
import com.mo9.batman.service.FinancialStatementService;
import com.mo9.batman.service.FormatDataService;
import com.mo9.batman.service.OriginalIndexToExcelService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:29 2018/4/3
 * @Modified By:
 */
@Component
public class OriginalIndexToExcelServiceImpl implements OriginalIndexToExcelService {

    @Resource
    private FormatDataService formatDataService;

    @Resource
    private FinancialStatementService financialStatementService;

    @Override
    public void write(String stockCode, Workbook workbook) {
        Map<String, FinancialBTO> financialBTOMap = financialStatementService.getOriginalFinancial(stockCode);
        for (Map.Entry<String, FinancialBTO> entry : financialBTOMap.entrySet()) {
            FinancialBTO value = entry.getValue();
            Map<String, List<Map<String, String>>> format =
                    formatDataService.formatFinancialIndex(value.getFieldflashData(), value.getStockCode());
            formatDataService.writeRowTExcel(format, workbook, entry.getKey());
        }
    }
}
