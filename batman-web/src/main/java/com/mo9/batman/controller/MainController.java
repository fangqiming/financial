package com.mo9.batman.controller;

import com.mo9.batman.config.StockConfig;
import com.mo9.batman.service.SplitSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
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

    /**
     * 127.0.0.1:8888/create
     *
     * @return
     */
    @RequestMapping(value = "/create")
    public String create() throws Exception {


        Long start = System.currentTimeMillis();
        Map<String, List<String>> pathSplit = splitSaveService.createSplit(stockConfig.getPath(), 2, stockConfig.getStock());
        for (Map.Entry<String, List<String>> entry : pathSplit.entrySet()) {
            splitSaveService.createExcelObj(entry.getKey(), entry.getValue());
        }
        Long end = System.currentTimeMillis();

        //每个文件夹的title可能不一样
        return "任务已完成 一共花费时间 :" + ((end - start) / 1000) + "s";
    }

}
