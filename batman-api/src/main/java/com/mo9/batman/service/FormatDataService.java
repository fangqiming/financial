package com.mo9.batman.service;

import com.mo9.batman.entity.FinancialIndexBO;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:01 2018/4/3
 * @Modified By:
 */
public interface FormatDataService {

    /**
     * 用于将标准的数据格式化为规整的数据
     *
     * @param financialIndexBO
     * @param stockCode
     * @return
     */
    Map<String, List<Map<String, String>>> formatFinancialIndex(FinancialIndexBO financialIndexBO, String stockCode);

    /**
     * 用于将标准的数据，写入到excel中
     *
     * @param format
     * @param xssfWorkbook
     * @param sheetPrefix
     */
    void writeRowTExcel(Map<String, List<Map<String, String>>> format, Workbook xssfWorkbook, String sheetPrefix);


    /**
     * 将规整的title信息写入到excel中
     *
     * @param xssfWorkbook
     * @param titles
     */
    void writeTitleTail(Workbook xssfWorkbook, Map<String, List<String>> titles);


}
