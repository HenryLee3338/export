package cn.henry.domain.vo;

import java.io.Serializable;

public class FactoryAmountVo implements Serializable {
    private String factoryId;
    private double amountAll;

    public FactoryAmountVo() {
    }

    public FactoryAmountVo(String factoryId, double amountAll) {
        this.factoryId = factoryId;
        this.amountAll = amountAll;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public double getAmountAll() {
        return amountAll;
    }

    public void setAmountAll(double amountAll) {
        this.amountAll = amountAll;
    }

    @Override
    public String toString() {
        return "FactoryAmountVo{" +
                "factoryId='" + factoryId + '\'' +
                ", amountAll=" + amountAll +
                '}';
    }
}
