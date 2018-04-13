package com.mo9.batman.impl;

import com.mo9.batman.entity.MetaData;
import com.mo9.batman.service.FindPeIndexService;
import com.mo9.batman.service.FormatDataService;
import com.mo9.batman.service.PeIndexToExcelService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 20:19 2018/4/10
 * @Modified By:
 */
@Component
public class PeIndexToExcelServiceImpl implements PeIndexToExcelService {

    @Resource
    private FormatDataService formatDataService;

    @Resource
    private FindPeIndexService findPeIndexService;

    @Override
    public void write(String stockCode, Workbook workbook) throws IOException {
        List<MetaData> index = findPeIndexService.findIndex(stockCode);
        Map<String, List<Map<String, String>>> pe = formatDataService.formatFinancialAsset(index, "PE");
        formatDataService.writeRowTExcel(pe, workbook, "index_");
    }
}
