package com.mo9.batman.external;

import com.alibaba.fastjson.JSONObject;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 14:38 2018/4/3
 * @Modified By:
 */
public interface StockApiService {

    /**
     * 用于向同花顺发起访问获取财务指标数据
     *
     * @param financialType
     * @return
     */
    @HTTP(method = "GET", path = "api/stock/finance/{financialType}")
    Call<JSONObject> find(@Path("financialType") String financialType);
}
