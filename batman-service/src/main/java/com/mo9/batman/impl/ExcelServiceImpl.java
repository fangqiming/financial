package com.mo9.batman.impl;

import com.mo9.batman.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 16:41 2018/3/26
 * @Modified By:
 */
@Slf4j
@Component
public class ExcelServiceImpl implements ExcelService {

    /**
     * Map<Sheet的名称, List<title值>>
     */
    private Map<String, List<String>> sheetMap = new HashMap<>();

    @Override
    public XSSFWorkbook create(String path) {
        if (!StringUtils.isEmpty(path)) {
            if (!path.endsWith("xlsx")) {
                throw new RuntimeException("file must end with .xlsx");
            }
            try {
                File file = new File(path);
                String basePath = file.getParent();
                File baseFile = new File(basePath);
                if (!baseFile.exists()) {
                    baseFile.mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                return new XSSFWorkbook(new FileInputStream(path));
            } catch (IOException e) {
                log.error("创建Excel文件失败 或excel流文件失败");
            }
        }
        return null;
    }

    @Override
    public Sheet createOrGetSheet(String name, Workbook wb) {
        Sheet result = null;
        if (!StringUtils.isEmpty(name)) {
            try {
                result = wb.createSheet(name);
            } catch (IllegalArgumentException e) {
                result = wb.getSheet(name);
            }
        }
        return result;
    }


    @Override
    public void writeCell(String value, int row, int column, Sheet sheet) {
        Row newRow = createOrGetRow(row, sheet);
        Cell cell = newRow.getCell(column) == null ? newRow.createCell(column) : newRow.getCell(column);
        cell.setCellValue(value);
    }

    @Override
    public void write2file(String path, Workbook wb) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            wb.write(out);
            out.close();
        } catch (IOException e) {
            log.error("数据写入磁盘失败", e);
        }

    }

    @Override
    public void writeNext(String value, Sheet sheet) {
        int rowCount = sheet.getLastRowNum();
        int column = getColumn(sheet, rowCount);
        writeCell(value, rowCount, column, sheet);
    }

    @Override
    public void writeNewRow(String value, Sheet sheet) {
        int rowCount = sheet.getLastRowNum() + 1;
        writeCell(value, rowCount, 0, sheet);
    }

    @Override
    public Row createOrGetRow(int row, Sheet sheet) {
        return sheet.getRow(row) == null ? sheet.createRow(row) : sheet.getRow(row);
    }

    @Override
    public int getValueIndexOrCreate(Row xssfRow, String value, Sheet sheet) {
        for (Cell cell : xssfRow) {
            if (value.equals(cell.getStringCellValue())) {
                return cell.getColumnIndex();
            }
        }
        int row = xssfRow.getRowNum();
        return writeNextInRow(value, sheet, row);
    }

    @Override
    public int writeNextInRow(String value, Sheet sheet, int row) {
        int column = getColumn(sheet, row);
        writeCell(value, row, column, sheet);
        return column;
    }

    @Override
    public int getRow(Sheet sheet) {
        return sheet.getLastRowNum() + 1;
    }

    private int getColumn(Sheet sheet, int rowCount) {
        try {
            int result = sheet.getRow(rowCount).getLastCellNum();
            return result > 0 ? result : 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    /**
     * 用来获取全部的title 等到保存完成之后，需要再次读取excel文件将title信息追加到title
     *
     * @param sheetName
     * @param title
     * @return
     */
    @Override
    public int getTitleIndex(String sheetName, String title) {
        if (sheetMap.containsKey(sheetName)) {
            List<String> titles = sheetMap.get(sheetName);
            if (!titles.contains(title)) {
                titles.add(title);
            }
            return titles.indexOf(title);
        } else {
            List<String> list = new ArrayList<>(32);
            list.add(title);
            sheetMap.put(sheetName, list);
            return list.indexOf(title);
        }
    }

    public Map<String, List<String>> getTitleCache() {
        return this.sheetMap;
    }
}
