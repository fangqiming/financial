package com.mo9.batman.impl;

import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.entity.MetaData;
import com.mo9.batman.entity.change.FinancialChangeIndex;
import com.mo9.batman.entity.change.FinancialChangeIndexBO;
import com.mo9.batman.service.DataToExcelService;
import com.mo9.batman.service.ExcelService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * @Author:qmfang
 * @Description: 注意规整数据的接口已经独立出来到了接口FormatDataService中
 * 但并没有至此处将其独立出来，因而存在大量的重复代码
 * @Date:Created in 18:15 2018/3/26
 * @Modified By:
 */
@Component
public class DataToExcelServiceImpl implements DataToExcelService {

    @Resource
    private ExcelService excelService;

    @Override
    public void writeFinancialIndex2Excel(Workbook xssfWorkbook, FinancialIndexBO financialIndexBO, String stockCode) {
        if (Objects.nonNull(financialIndexBO) && !CollectionUtils.isEmpty(financialIndexBO.getYear())) {
            Map<String, List<Map<String, String>>> format = formatFinancialIndex(financialIndexBO, stockCode);
            writeRowTExcel(format, xssfWorkbook, "financial_");
        }
    }

    @Override
    public void writeChangeIndex2Excel(Workbook xssfWorkbook, List<FinancialChangeIndexBO> changeIndex, String stockCode) {
        if (!CollectionUtils.isEmpty(changeIndex)) {
            Map<String, List<Map<String, String>>> format = formatFinancialChange(changeIndex, stockCode);
            writeRowTExcel(format, xssfWorkbook, "change_");
        }
    }

    @Override
    public void writeAsset2Excel(Workbook xssfWorkbook, List<MetaData> asset, String stockCode) {
        if (!CollectionUtils.isEmpty(asset)) {
            Map<String, List<Map<String, String>>> format = formatFinancialAsset(asset, stockCode);
            writeRowTExcel(format, xssfWorkbook, "asset_");
        }
    }


    @Override
    @Deprecated
    public void writeTitle(Map<String, List<String>> titles, String path) {
        if (!CollectionUtils.isEmpty(titles)) {
            XSSFWorkbook xssfWorkbook = excelService.create(path);
            for (Map.Entry<String, List<String>> map : titles.entrySet()) {
                Sheet sheet = xssfWorkbook.getSheet(map.getKey());
                List<String> titleName = map.getValue();
                if (!CollectionUtils.isEmpty(titleName)) {
                    for (int column = 0; column < titleName.size(); column++) {
                        excelService.writeCell(titleName.get(column), 0, column, sheet);
                    }
                }
            }
            excelService.write2file(path, xssfWorkbook);
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

    private Map<String, List<Map<String, String>>> formatFinancialIndex(FinancialIndexBO financialIndexBO, String stockCode) {
        List<Map<String, String>> result = new ArrayList<>();
        if (Objects.nonNull(financialIndexBO) && !CollectionUtils.isEmpty(financialIndexBO.getTitle())) {
            List<String> titles = financialIndexBO.getTitle();
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
        try {
            return result.stream().collect(groupingBy(a -> a.get("科目\\时间").substring(0, 4)));
        } catch (Exception e) {
            System.out.println(result);
        }
        return null;
    }

    private Map<String, List<Map<String, String>>> formatFinancialChange(List<FinancialChangeIndexBO> changeIndex, String stockCode) {
        List<Map<String, String>> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(changeIndex)) {
            //获取到一个季度的变化原因
            for (FinancialChangeIndexBO financialChangeIndexBO : changeIndex) {
                List<FinancialChangeIndex> changeMate = financialChangeIndexBO.getIndex();
                if (!CollectionUtils.isEmpty(changeMate)) {
                    //该季度的所有的产生变化了的指标
                    for (FinancialChangeIndex financialChangeIndex : changeMate) {
                        Map<String, String> rowData = new HashMap<>(7);
                        //此处应该是一天的数据才对
                        rowData.put("股票代码", stockCode);
                        rowData.put("时间", financialChangeIndexBO.getData());
                        rowData.put(financialChangeIndex.getChangeSubject().getTitle(), financialChangeIndex.getChangeSubject().getValue());
                        rowData.put(financialChangeIndex.getCurrentValue().getTitle(), financialChangeIndex.getCurrentValue().getValue());
                        rowData.put(financialChangeIndex.getPreviousValue().getTitle(), financialChangeIndex.getPreviousValue().getValue());
                        rowData.put(financialChangeIndex.getAmplitude().getTitle(), financialChangeIndex.getAmplitude().getValue());
                        rowData.put(financialChangeIndex.getReason().getTitle(), financialChangeIndex.getReason().getValue());
                        result.add(rowData);
                    }

                }
            }
        }
        return result.stream().collect(groupingBy(a -> a.get("时间").substring(0, 4)));
    }

    private Map<String, List<Map<String, String>>> formatFinancialAsset(List<MetaData> asset, String stockCode) {
        List<Map<String, String>> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(asset)) {
            Map<String, String> row = new HashMap<>(asset.size());
            for (MetaData mateData : asset) {
                row.put(mateData.getTitle(), mateData.getValue());
            }
            row.put("股票代码", stockCode);
            row.put("时间", "yyyy-MM-dd");
            result.add(row);
        }
        return result.stream().collect(groupingBy(a -> a.get("时间")));
    }

    private void writeRowTExcel(Map<String, List<Map<String, String>>> format, Workbook xssfWorkbook, String sheetPrefix) {
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
