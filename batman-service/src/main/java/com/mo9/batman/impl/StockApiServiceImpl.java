package com.mo9.batman.impl;

import com.alibaba.fastjson.JSONObject;
import com.mo9.batman.entity.FinancialIndexBO;
import com.mo9.batman.entity.financial.FinancialBTO;
import com.mo9.batman.external.StockApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 11:05 2018/4/3
 * @Modified By:
 */
@Slf4j
@Component
public class StockApiServiceImpl {

    private StockApiService service;

    @PostConstruct
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://basic.10jqka.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(StockApiService.class);
    }

    public FinancialBTO get(String str) {
        Call<JSONObject> call = service.find(str);
        JSONObject obj = execute(call);
        if (Objects.nonNull(obj)) {

            if (Objects.isNull(obj.getJSONObject("fieldflashData"))) {
                return null;
            }
            FinancialIndexBO field1 =
                    JSONObject.parseObject(obj.getJSONObject("fieldflashData").toJSONString(), FinancialIndexBO.class);

            FinancialIndexBO field2 =
                    JSONObject.parseObject(obj.getJSONObject("flashData").toJSONString(), FinancialIndexBO.class);
            return FinancialBTO.builder().fieldflashData(field1).flashData(field2).build();
        }
        return null;
    }

    private <T> T execute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException(String.format("请求失败, 请求地址: %s, HttpStatus: %s", call.request().url(), response.code()));
            } else {
                return response.body();
            }
        } catch (Exception e) {
            log.error("远端接口访问失败:" + call.request().url(), e);
            return null;
        }
    }

}
