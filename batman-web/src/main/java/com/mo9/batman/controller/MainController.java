package com.mo9.batman.controller;

import com.mo9.batman.config.StockConfig;
import com.mo9.batman.service.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author : xjding
 * @date :   2018-01-18 15:02
 */
@RestController
@RequestMapping
public class MainController {

    @Autowired
    private StockConfig stockConfig;

    @Resource
    private SplitSaveService splitSaveService;

    @Resource
    private ExcelService excelService;

    @Resource
    private OriginalIndexToExcelService originalIndexToExcelService;

    @Resource
    private FormatDataService formatDataService;

    /**
     * 127.0.0.1:8887/createHandlerIndex
     *
     * @return
     */
    @RequestMapping(value = "/createHandlerIndex")
    public String create() throws Exception {
        System.out.println("total:" + stockConfig.getStock().size());
        Long start = System.currentTimeMillis();
        Map<String, List<String>> pathSplit = splitSaveService.createSplit(stockConfig.getPath(), stockConfig.getFileNum(), stockConfig.getStock());
        for (Map.Entry<String, List<String>> entry : pathSplit.entrySet()) {
            splitSaveService.createExcelObj(entry.getKey(), entry.getValue());
        }
        Long end = System.currentTimeMillis();

        //每个文件夹的title可能不一样
        return "任务已完成 一共花费时间 :" + ((end - start) / 1000) + "s";
    }


    /**
     * 127.0.0.1:8887/createOriginalIndex
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/createOriginalIndex")
    public String createOriginal() throws Exception {
        System.out.println("total:" + stockConfig.getStock().size());
        Long start = System.currentTimeMillis();
        String path = "C:\\mo9Project\\test_0.xlsx";
        SXSSFWorkbook workbook = new SXSSFWorkbook(excelService.create(path), 500);
        for (String stockCode : stockConfig.getStock()) {
            originalIndexToExcelService.write(stockCode, workbook);
        }
        formatDataService.writeTitleTail(workbook, excelService.getTitleCache());

        //将数据写入到excel中
        excelService.write2file(path, workbook);
        Long end = System.currentTimeMillis();
        return "任务已完成 一共花费时间 :" + ((end - start) / 1000) + "s";
    }

}
