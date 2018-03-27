package com.mo9.batman.common.result;

import lombok.Data;

import java.time.Instant;
import java.util.HashMap;

/**
 * 通用返回实体
 * @author : xjding
 * @date :   2017-12-01 11:07
 */
@Data
public class Results {

    //系统默认前缀
    private static final int BASE = 10200000;

    private Long code;
    private String message;
    private Object data = new HashMap<>();
    private Long timestamp;

    private Results(Long code, String message, Object data) {
        //这里已经将系统前缀加上
        if (code != ResultConstant.SUCCESS.code) {
            code = BASE + code;
        }
        this.code = code;
        this.message = message;
        if (data != null) {
            this.data = data;
        }
        this.timestamp = Instant.now().getEpochSecond();
    }

    public static Results ok() {
        return new Results(ResultConstant.SUCCESS.code, ResultConstant.SUCCESS.message, null);
    }

    public static Results ok(ResultData data) {
        return new Results(ResultConstant.SUCCESS.code, ResultConstant.SUCCESS.message, data.value());
    }

    public static Results ok(String message) {
        return new Results(ResultConstant.SUCCESS.code, message, null);
    }

    public static Results ok(String message, ResultData data) {
        return new Results(ResultConstant.SUCCESS.code, message, data.value());
    }

    public static Results nok(ResultConstant c) {
        return new Results(c.code, c.message, null);
    }

    public static Results nok(ResultConstant c, ResultData data) {
        return new Results(c.code, c.message, data.value());
    }

    public static Results nok(ResultConstant c, String message) {
        return new Results(c.code, message, null);
    }

    public static Results nok(ResultConstant c, String message, ResultData data) {
        return new Results(c.code, message, data.value());
    }

}
