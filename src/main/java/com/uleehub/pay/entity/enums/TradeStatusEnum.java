package com.uleehub.pay.entity.enums;
/**
 * <p>User: mtwu
 * <p>Date: 2014-7-3
 * <p>Version: 1.0
 */
public enum TradeStatusEnum {
	PROCESS(0, "交易中"), SUCCESS(1, "交易成功"), FAIL(2, "交易失败"), SUSPICIOUS(3, "交易可疑");

    private final Integer value;
    private final String info;

    private TradeStatusEnum(Integer value, String info) {
        this.value = value;
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public Integer getValue() {
        return value;
    }
}
