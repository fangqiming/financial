package com.mo9.batman.service;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 17:10 2018/4/3
 * @Modified By:
 */
public interface OriginalIndexToExcelService {

    /**
     * 将财务报表的原始数据写入到excel中
     *
     * @param stockCode
     * @param workbook
     */
    void write(String stockCode, Workbook workbook);

}
