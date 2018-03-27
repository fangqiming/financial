package com.mo9.batman.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.entity.MetaData;
import com.mo9.batman.entity.change.FinancialChangeIndex;
import com.mo9.batman.entity.change.FinancialChangeIndexBO;
import com.mo9.batman.service.HtmlParseService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 11:03 2018/3/26
 * @Modified By:
 */
@Component
public class HtmlParseServiceImpl implements HtmlParseService {

    @Override
    public FinancialIndexBO getFinancialIndex(Document doc) {
        try {
            Element mainElement = doc.getElementById("main");
            JSONObject obj = JSONObject.parseObject(mainElement.html());
            //去掉不规范的数据
//            JSONArray title = obj.getJSONArray("title");
//            title.remove(0);
//            title.add(0, "[时间,科目]");
//            obj.put("title", title);
            return JSONObject.parseObject(obj.toJSONString(), FinancialIndexBO.class);
        } catch (NullPointerException e) {
            return FinancialIndexBO.builder().build();
        }
    }

    @Override
    public List<FinancialChangeIndexBO> getFinancialChange(Document doc) {
        List<FinancialChangeIndexBO> result = new ArrayList<>();
        Elements elements = doc.getElementsByClass("m_tab_content");
        if (Objects.nonNull(elements)) {
            for (Element element : elements) {
                FinancialChangeIndexBO financialChangeIndexBO = new FinancialChangeIndexBO();
                //每次过来就是一个季度的情况
                try {
                    Element tableElement = element.child(0);
                    String data = tableElement.attr("id").split("_")[1];
                    financialChangeIndexBO.setData(data);
                    //追加title信息
                    Element title = tableElement.getElementsByTag("thead").get(0).getElementsByTag("tr").get(0);
                    //获取一行的元数据列表
                    Elements trs = tableElement.getElementsByTag("tbody").get(0).getElementsByTag("tr");
                    List<FinancialChangeIndex> financialChangeIndices = new ArrayList<>(trs.size());
                    //获取到所有的tr
                    for (Element th : trs) {
                        MetaData changeSubject = MetaData.builder()
                                .title(title.child(0).html()).value(th.child(0).html()).build();
                        MetaData currentValue = MetaData.builder()
                                .title(title.child(1).html()).value(th.child(1).html()).build();
                        MetaData previousValue = MetaData.builder()
                                .title(title.child(2).html()).value(th.child(2).html()).build();
                        MetaData amplitude = MetaData.builder()
                                .title(title.child(3).html()).value(th.child(3).child(1).html()).build();
                        MetaData reason = MetaData.builder()
                                .title(title.child(4).html()).value(th.child(4).html()).build();
                        FinancialChangeIndex tmp = FinancialChangeIndex.builder().changeSubject(changeSubject)
                                .currentValue(currentValue).previousValue(previousValue).amplitude(amplitude)
                                .reason(reason).build();
                        financialChangeIndices.add(tmp);
                    }
                    financialChangeIndexBO.setIndex(financialChangeIndices);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                result.add(financialChangeIndexBO);
            }
        }
        return result;
    }

    @Override
    public List<MetaData> getAssetLiabilities(Document doc) {
        List<MetaData> result = new ArrayList<>();
        try {
            Element assetElement = doc.getElementById("assetdebt");
            Elements assets = assetElement.getElementsByTag("table");
            for (Element element : assets) {
                Element tbody = element.getElementsByTag("tbody").get(0);
                Elements trs = tbody.getElementsByTag("tr");
                for (Element tr : trs) {
                    result.add(MetaData.builder().title(tr.child(0).text())
                            .value(tr.child(1).html()).build());
                }
            }
        } catch (NullPointerException e) {

        }
        return result;
    }
}
