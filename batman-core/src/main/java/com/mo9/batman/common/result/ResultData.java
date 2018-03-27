package com.mo9.batman.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * @author : xjding
 * @date :   2017-12-01 11:07
 */
public class ResultData {

    private final Map<String, Object> data;

    private ResultData(ResultDataBuilder builder) {
        this.data = builder.data;
    }

    public Map<String, Object> value() {
        return this.data;
    }

    public static ResultDataBuilder builder() {
        return new ResultDataBuilder();
    }

    public static class ResultDataBuilder {

        private final Map<String, Object> data;

        public ResultDataBuilder() {
            this.data = new HashMap<>();
        }

        public ResultDataBuilder append(String displayName, Object object) {
            if (object instanceof ResultData) {
                data.put(displayName, ((ResultData) object).value());
            } else {
                data.put(displayName, object);
            }
            return this;
        }

        public ResultData build() {
            return new ResultData(this);
        }

    }

}
