package com.mo9.batman.common.result;

/**
 * 通用返回枚举
 * @author : xjding
 * @date :   2017-12-01 11:07
 */
public enum ResultConstant {

    SUCCESS(0, "ok"),

    REQUEST_ERROR(1, "请求发生错误"),
    PARAMS_ERROR(2, "请求参数不完整或参数格式有误"),
    ENTITY_NOT_FOUND(3, "请求实体不存在"),
    PERMISSION_ERROR(9, "对不起, 您暂时没有此项权限"),


    WITHDRAW_ORDER_CODE_REPEAT(1000, "申请提现失败, 订单编号重复"),
    WITHDRAW_CREATE_FAILED(1001, "申请提现失败, 请重试"),

    CREATE_ACCOUNT_ERROR(2000, "创建账户失败"),


    ;

    public final long code;
    public final String message;

    ResultConstant(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
