package com.mo9.batman.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 16:01 2018/3/26
 * @Modified By:
 */
public interface ExcelService {

    /**
     * 创建excel对象
     *
     * @param ptah
     * @return
     */
    XSSFWorkbook create(String ptah);

    /**
     * 创建或者获取sheet
     *
     * @param name
     * @param wb
     * @return
     */
    Sheet createOrGetSheet(String name, Workbook wb);


    /**
     * 向excel中的指定位置写入数据
     *
     * @param value
     * @param row
     * @param column
     * @param sheet
     */
    void writeCell(String value, int row, int column, Sheet sheet);

    /**
     * 将数据写入到excel中
     *
     * @param path
     * @param wb
     */
    void write2file(String path, Workbook wb);

    /**
     * 将数据写入到下一个空的cell
     *
     * @param value
     * @param sheet
     */
    void writeNext(String value, Sheet sheet);

    /**
     * 数据写入到下一行的行首位置
     *
     * @param value
     * @param sheet
     */
    void writeNewRow(String value, Sheet sheet);

    /**
     * 创建或者获取指定行
     *
     * @param row
     * @param sheet
     * @return
     */
    Row createOrGetRow(int row, Sheet sheet);

    /**
     * 获取指定值所在的位置
     *
     * @param xssfRow
     * @param value
     * @return
     */
    int getValueIndexOrCreate(Row xssfRow, String value, Sheet sheet);


    /**
     * 在指定的行的末尾追加数据
     *
     * @param value
     * @param sheet
     * @param row
     */
    int writeNextInRow(String value, Sheet sheet, int row);

    /**
     * 获取最新的空行
     *
     * @param sheet
     * @return
     */
    int getRow(Sheet sheet);

    /**
     * 根据sheetName获取缓存的title的位置
     *
     * @param sheetName
     * @param title
     * @return
     */
    int getTitleIndex(String sheetName, String title);

    /**
     * 用于获取所有缓存中的title
     *
     * @return
     */
    Map<String, List<String>> getTitleCache();

}
