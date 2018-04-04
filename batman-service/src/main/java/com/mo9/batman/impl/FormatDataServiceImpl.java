package com.mo9.batman.impl;

import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.service.ExcelService;
import com.mo9.batman.service.FormatDataService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:05 2018/4/3
 * @Modified By:
 */
@Component
public class FormatDataServiceImpl implements FormatDataService {

    @Resource
    private ExcelService excelService;


    @Override
    public Map<String, List<Map<String, String>>> formatFinancialIndex(FinancialIndexBO financialIndexBO, String stockCode) {
        List<Map<String, String>> result = new ArrayList<>();
        if (Objects.nonNull(financialIndexBO) && !CollectionUtils.isEmpty(financialIndexBO.getTitle())) {
            List<String> titles = financialIndexBO.getTitle();
            if (!CollectionUtils.isEmpty(financialIndexBO.getYear())) {
                for (int row = 0; row < financialIndexBO.getYear().get(0).size(); row++) {
                    Map<String, String> rowData = new HashMap<>(titles.size());
                    for (int column = 0; column < titles.size(); column++) {
                        rowData.put(titles.get(column), financialIndexBO.getYear().get(column).get(row));
                    }
                    rowData.put("股票代码", stockCode);
                    if (!StringUtils.isEmpty(rowData.get("科目\\时间"))) {
                        result.add(rowData);
                    }
                }
            }
        }
        try {
            return result.stream().collect(groupingBy(a -> a.get("科目\\时间").substring(0, 4)));
        } catch (Exception e) {
            System.out.println(result);
        }
        return null;
    }

    @Override
    public void writeRowTExcel(Map<String, List<Map<String, String>>> format, Workbook xssfWorkbook, String sheetPrefix) {
        if (!CollectionUtils.isEmpty(format)) {
            for (Map.Entry<String, List<Map<String, String>>> entry : format.entrySet()) {
                Sheet sheet = excelService.createOrGetSheet(sheetPrefix + entry.getKey(), xssfWorkbook);
                for (Map<String, String> rowMap : entry.getValue()) {
                    int row = excelService.getRow(sheet);
                    for (Map.Entry<String, String> cell : rowMap.entrySet()) {
                        int column = excelService.getTitleIndex(sheet.getSheetName(), cell.getKey());
                        excelService.writeCell(cell.getValue(), row, column, sheet);
                    }
                }
            }
        }
    }

    @Override
    public void writeTitleTail(Workbook xssfWorkbook, Map<String, List<String>> titles) {
        if (!CollectionUtils.isEmpty(titles)) {
            for (Map.Entry<String, List<String>> entry : titles.entrySet()) {
                Sheet sheet = xssfWorkbook.getSheet(entry.getKey());
                if (!CollectionUtils.isEmpty(entry.getValue())) {
                    List<String> titleNames = entry.getValue();
                    int row = excelService.getRow(sheet);
                    for (int column = 0; column < titleNames.size(); column++) {
                        excelService.writeCell(titleNames.get(column), row, column, sheet);
                    }
                }
            }
        }
    }
}
