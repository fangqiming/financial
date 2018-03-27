package com.mo9.batman.impl;

import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.entity.MetaData;
import com.mo9.batman.entity.change.FinancialChangeIndexBO;
import com.mo9.batman.service.ExcelService;
import com.mo9.batman.service.HtmlParseService;
import com.mo9.batman.service.SplitSaveService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:58 2018/3/27
 * @Modified By:
 */
@Component
public class SplitSaveServiceImpl implements SplitSaveService {

    @Resource
    private HtmlParseService htmlParseService;

    @Resource
    private ExcelService excelService;

    @Autowired
    private DataToExcelServiceImpl dataToExcelService;

    @Override
    public void createExcelObj(String path, List<String> stockCode) {
        if (!CollectionUtils.isEmpty(stockCode)) {
            SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook(excelService.create(path), 500);
            for (String code : stockCode) {
                try {
                    sleep(2 * 1000);
                    Document doc = Jsoup.connect("http://basic.10jqka.com.cn/" + code + "/finance.html#stockpage").get();
                    List<MetaData> assets = htmlParseService.getAssetLiabilities(doc);
                    List<FinancialChangeIndexBO> change = htmlParseService.getFinancialChange(doc);
                    FinancialIndexBO financialIndex = htmlParseService.getFinancialIndex(doc);
                    dataToExcelService.writeChangeIndex2Excel(xssfWorkbook, change, code);
                    dataToExcelService.writeFinancialIndex2Excel(xssfWorkbook, financialIndex, code);
                    dataToExcelService.writeAsset2Excel(xssfWorkbook, assets, code);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("access failed :" + "http://basic.10jqka.com.cn/" + code + "/finance.html#stockpage");
                }
            }
            excelService.write2file(path, xssfWorkbook);
            xssfWorkbook.dispose();
            dataToExcelService.writeTitle(excelService.getTitleCache(), path);
        }
    }

    @Override
    public Map<String, List<String>> createSplit(String path, int num, List<String> stockCode) {
        String[] filePath = path.split("\\.");
        Map<String, List<String>> result = new HashMap<>(16);
        if (!CollectionUtils.isEmpty(stockCode)) {
            int splitNum = (int) Math.ceil(stockCode.size() / 1.0 / num);
            for (int split = 0; split < num; split++) {
                String newPath = filePath[0] + "_" + split + "." + filePath[1];
                int end = splitNum * (split + 1) > stockCode.size() ? stockCode.size() : splitNum * (split + 1);
                result.put(newPath, stockCode.subList(split * splitNum, end));
            }
        }
        return result;
    }

    private void sleep(int sec) {
        try {
            Thread.sleep(sec);
        } catch (Exception e) {
            System.out.println("sleep exception");
        }
    }
}
