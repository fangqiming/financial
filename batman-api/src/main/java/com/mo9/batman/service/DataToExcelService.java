package com.mo9.batman.service;

import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.entity.MetaData;
import com.mo9.batman.entity.change.FinancialChangeIndexBO;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 18:15 2018/3/26
 * @Modified By:
 */
public interface DataToExcelService {
    /**
     * 用于将财务指标信息写入excel
     *
     * @param xssfWorkbook
     * @param financialIndexBO
     * @param stockCode
     */
    void writeFinancialIndex2Excel(Workbook xssfWorkbook, FinancialIndexBO financialIndexBO, String stockCode);


    /**
     * 用于将财务指标变动原因写入excel
     *
     * @param xssfWorkbook
     * @param changeIndex
     * @param stockCode
     */
    void writeChangeIndex2Excel(Workbook xssfWorkbook, List<FinancialChangeIndexBO> changeIndex, String stockCode);

    /**
     * 用于将资产负债信息写入excel
     *
     * @param xssfWorkbook
     * @param asset
     * @param stockCode
     */
    void writeAsset2Excel(Workbook xssfWorkbook, List<MetaData> asset, String stockCode);

    /**
     * 用于将缓存中的数据写入到excel中
     *
     * @param titles
     * @param path
     */
    @Deprecated
    void writeTitle(Map<String, List<String>> titles, String path);

    /**
     * 将标题写入到行位
     *
     * @param xssfWorkbook
     * @param titles
     */
    void writeTitleTail(Workbook xssfWorkbook, Map<String, List<String>> titles);
}
