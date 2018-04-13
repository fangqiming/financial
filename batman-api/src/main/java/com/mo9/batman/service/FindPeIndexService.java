package com.mo9.batman.service;

import com.mo9.batman.entity.MetaData;

import java.io.IOException;
import java.util.List;

/**
 * @Author:qmfang
 * @Description: 用于获取市盈率，每股收益等相关指标
 * @Date:Created in 13:46 2018/4/9
 * @Modified By:
 */
public interface FindPeIndexService {
    /**
     * 根据传入的股票代码获取指标数据
     *
     * @param stockCode
     * @return
     */
    List<MetaData> findIndex(String stockCode) throws IOException;
}
