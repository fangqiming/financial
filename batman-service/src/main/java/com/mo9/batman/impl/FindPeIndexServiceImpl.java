package com.mo9.batman.impl;

import com.mo9.batman.entity.MetaData;
import com.mo9.batman.service.FindPeIndexService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 19:31 2018/4/10
 * @Modified By:
 */
@Component
public class FindPeIndexServiceImpl implements FindPeIndexService {

    @Override
    public List<MetaData> findIndex(String stockCode) throws IOException {
        List<MetaData> result = new ArrayList<>(32);
        try {
            result.add(MetaData.builder().title("股票代码").value(stockCode).build());
            if (!StringUtils.isEmpty(stockCode)) {
                Document doc = Jsoup.connect("http://basic.10jqka.com.cn/" + stockCode + "/").get();
                result.add(createIndustry(doc, stockCode));
                Element element = doc.getElementsByClass("m_table_db").get(1);
                List<Element> index = new ArrayList<>(32);
                element.getElementsByClass("hltip").forEach(a -> {
                    if (a.hasClass("f12")) {
                        index.add(a);
                    }
                });

                if (!CollectionUtils.isEmpty(index)) {
                    for (Element element1 : index) {
                        Element next = element1.nextElementSibling();
                        if (Objects.nonNull(next) && next.hasClass("tip") && next.hasClass("f12")) {
                            result.add(MetaData.builder().title(element1.text()).value(next.text()).build());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("获取常规指标出错：" + stockCode);
            e.printStackTrace();
        }

        return result;
    }

    private MetaData createIndustry(Document doc, String stockCode) {
        try {
            Elements m_table_db = doc.getElementsByClass("m_table_db");
            Elements industry = m_table_db.get(0).getElementsByTag("tr").get(0).getElementsByTag("td").get(1).getElementsByTag("span");
            return MetaData.builder().title(industry.get(0).text()).value(industry.get(1).text()).build();
        } catch (Exception e) {
            System.out.println("获取行业指标错误:" + stockCode);
            e.printStackTrace();
            return MetaData.builder().title("所属行业：").value("未知").build();
        }
    }
}
