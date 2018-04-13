package com.mo9.batman.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 20:33 2018/4/10
 * @Modified By:
 */
public interface PeIndexToExcelService {

    /**
     * 用于将指标数据写入到excel文件中
     *
     * @param stockCode
     * @param workbook
     * @throws IOException
     */
    void write(String stockCode, Workbook workbook) throws IOException;
}
