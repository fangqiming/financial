package com.mo9.batman.service;

import com.mo9.batman.entity.MetaData;
import org.jsoup.nodes.Document;
import com.mo9.batman.entity.change.FinancialChangeIndexBO;
import com.mo9.batman.entity.FinancialIndexBO;

import java.util.List;

/**
 * @Author:qmfang
 * @Description: 用于解析Html文件
 * @Date:Created in 10:15 2018/3/26
 * @Modified By:
 */
public interface HtmlParseService {

    /**
     * 用于获取财务指标数据
     *
     * @param doc
     * @return
     */
    FinancialIndexBO getFinancialIndex(Document doc);

    /**
     * 获取财务变动指标
     *
     * @param doc
     * @return
     */
    List<FinancialChangeIndexBO> getFinancialChange(Document doc);

    /**
     * 获取资产负债指标
     *
     * @param doc
     * @return
     */
    List<MetaData> getAssetLiabilities(Document doc);
}
