package com.mo9.batman.service;

import java.util.List;
import java.util.Map;

/**
 * @Author:qmfang
 * @Description: 统一对外提供的接口，用于将股票计算好的财务信息写入到excel中
 * @Date:Created in 15:56 2018/3/27
 * @Modified By:
 */
public interface SplitSaveService {

    /**
     * 创建文档流并写入到相应的excel文件中
     *
     * @param path
     * @param stockCode
     */
    void createExcelObj(String path, List<String> stockCode);

    /**
     * 将股票代码进行分割
     *
     * @param path
     * @param num
     * @param stockCode
     * @return
     */
    Map<String, List<String>> createSplit(String path, int num, List<String> stockCode);
}
