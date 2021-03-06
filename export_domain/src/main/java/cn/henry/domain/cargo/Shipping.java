package cn.henry.domain.cargo;

import cn.henry.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class Shipping extends BaseEntity implements Serializable {

    private String shippingOrderId;//货运单id
    private String orderType;//货运类型(海运/空运)
    private String shipper;//运输公司
    private String notifyParty;//收件人邮箱
    private String portOfLoading;//装运港
    private String portOfTrans;//转运港
    private String portOfDischarge;//卸货港
    private Integer state;//状态(1.草稿/2.委托)
    private Date createTime;//创建时间
    private String companyId;//公司id
    private String companyName;//公司名字

    private String consignee;//收件人
    private String lcNo;//提单号



    private Date loadingDate;

    private Date limitDate;

    private String isBatch;

    private String isTrans;

    private String copyNum;

    private String remark;

    private String specialCondition;

    private String freight;

    private String checkBy;

    private String createBy;

    private String createDept;


    @Override
    public String toString() {
        return "Shipping{" +
                "shippingOrderId='" + shippingOrderId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", shipper='" + shipper + '\'' +
                ", notifyParty='" + notifyParty + '\'' +
                ", portOfLoading='" + portOfLoading + '\'' +
                ", portOfTrans='" + portOfTrans + '\'' +
                ", portOfDischarge='" + portOfDischarge + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", consignee='" + consignee + '\'' +
                ", lcNo='" + lcNo + '\'' +
                ", loadingDate=" + loadingDate +
                ", limitDate=" + limitDate +
                ", isBatch='" + isBatch + '\'' +
                ", isTrans='" + isTrans + '\'' +
                ", copyNum='" + copyNum + '\'' +
                ", remark='" + remark + '\'' +
                ", specialCondition='" + specialCondition + '\'' +
                ", freight='" + freight + '\'' +
                ", checkBy='" + checkBy + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDept='" + createDept + '\'' +
                '}';
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.SHIPPING_ORDER_ID
     *
     * @return the value of co_shipping_order.SHIPPING_ORDER_ID
     *
     * @mbg.generated
     */
    public String getShippingOrderId() {
        return shippingOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.SHIPPING_ORDER_ID
     *
     * @param shippingOrderId the value for co_shipping_order.SHIPPING_ORDER_ID
     *
     * @mbg.generated
     */
    public void setShippingOrderId(String shippingOrderId) {
        this.shippingOrderId = shippingOrderId == null ? null : shippingOrderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.ORDER_TYPE
     *
     * @return the value of co_shipping_order.ORDER_TYPE
     *
     * @mbg.generated
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.ORDER_TYPE
     *
     * @param orderType the value for co_shipping_order.ORDER_TYPE
     *
     * @mbg.generated
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.SHIPPER
     *
     * @return the value of co_shipping_order.SHIPPER
     *
     * @mbg.generated
     */
    public String getShipper() {
        return shipper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.SHIPPER
     *
     * @param shipper the value for co_shipping_order.SHIPPER
     *
     * @mbg.generated
     */
    public void setShipper(String shipper) {
        this.shipper = shipper == null ? null : shipper.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.CONSIGNEE
     *
     * @return the value of co_shipping_order.CONSIGNEE
     *
     * @mbg.generated
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.CONSIGNEE
     *
     * @param consignee the value for co_shipping_order.CONSIGNEE
     *
     * @mbg.generated
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.NOTIFY_PARTY
     *
     * @return the value of co_shipping_order.NOTIFY_PARTY
     *
     * @mbg.generated
     */
    public String getNotifyParty() {
        return notifyParty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.NOTIFY_PARTY
     *
     * @param notifyParty the value for co_shipping_order.NOTIFY_PARTY
     *
     * @mbg.generated
     */
    public void setNotifyParty(String notifyParty) {
        this.notifyParty = notifyParty == null ? null : notifyParty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.LC_NO
     *
     * @return the value of co_shipping_order.LC_NO
     *
     * @mbg.generated
     */
    public String getLcNo() {
        return lcNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.LC_NO
     *
     * @param lcNo the value for co_shipping_order.LC_NO
     *
     * @mbg.generated
     */
    public void setLcNo(String lcNo) {
        this.lcNo = lcNo == null ? null : lcNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.PORT_OF_LOADING
     *
     * @return the value of co_shipping_order.PORT_OF_LOADING
     *
     * @mbg.generated
     */
    public String getPortOfLoading() {
        return portOfLoading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.PORT_OF_LOADING
     *
     * @param portOfLoading the value for co_shipping_order.PORT_OF_LOADING
     *
     * @mbg.generated
     */
    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading == null ? null : portOfLoading.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.PORT_OF_TRANS
     *
     * @return the value of co_shipping_order.PORT_OF_TRANS
     *
     * @mbg.generated
     */
    public String getPortOfTrans() {
        return portOfTrans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.PORT_OF_TRANS
     *
     * @param portOfTrans the value for co_shipping_order.PORT_OF_TRANS
     *
     * @mbg.generated
     */
    public void setPortOfTrans(String portOfTrans) {
        this.portOfTrans = portOfTrans == null ? null : portOfTrans.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.PORT_OF_DISCHARGE
     *
     * @return the value of co_shipping_order.PORT_OF_DISCHARGE
     *
     * @mbg.generated
     */
    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.PORT_OF_DISCHARGE
     *
     * @param portOfDischarge the value for co_shipping_order.PORT_OF_DISCHARGE
     *
     * @mbg.generated
     */
    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge == null ? null : portOfDischarge.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.LOADING_DATE
     *
     * @return the value of co_shipping_order.LOADING_DATE
     *
     * @mbg.generated
     */
    public Date getLoadingDate() {
        return loadingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.LOADING_DATE
     *
     * @param loadingDate the value for co_shipping_order.LOADING_DATE
     *
     * @mbg.generated
     */
    public void setLoadingDate(Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.LIMIT_DATE
     *
     * @return the value of co_shipping_order.LIMIT_DATE
     *
     * @mbg.generated
     */
    public Date getLimitDate() {
        return limitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.LIMIT_DATE
     *
     * @param limitDate the value for co_shipping_order.LIMIT_DATE
     *
     * @mbg.generated
     */
    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.IS_BATCH
     *
     * @return the value of co_shipping_order.IS_BATCH
     *
     * @mbg.generated
     */
    public String getIsBatch() {
        return isBatch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.IS_BATCH
     *
     * @param isBatch the value for co_shipping_order.IS_BATCH
     *
     * @mbg.generated
     */
    public void setIsBatch(String isBatch) {
        this.isBatch = isBatch == null ? null : isBatch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.IS_TRANS
     *
     * @return the value of co_shipping_order.IS_TRANS
     *
     * @mbg.generated
     */
    public String getIsTrans() {
        return isTrans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.IS_TRANS
     *
     * @param isTrans the value for co_shipping_order.IS_TRANS
     *
     * @mbg.generated
     */
    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans == null ? null : isTrans.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.COPY_NUM
     *
     * @return the value of co_shipping_order.COPY_NUM
     *
     * @mbg.generated
     */
    public String getCopyNum() {
        return copyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.COPY_NUM
     *
     * @param copyNum the value for co_shipping_order.COPY_NUM
     *
     * @mbg.generated
     */
    public void setCopyNum(String copyNum) {
        this.copyNum = copyNum == null ? null : copyNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.REMARK
     *
     * @return the value of co_shipping_order.REMARK
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.REMARK
     *
     * @param remark the value for co_shipping_order.REMARK
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.SPECIAL_CONDITION
     *
     * @return the value of co_shipping_order.SPECIAL_CONDITION
     *
     * @mbg.generated
     */
    public String getSpecialCondition() {
        return specialCondition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.SPECIAL_CONDITION
     *
     * @param specialCondition the value for co_shipping_order.SPECIAL_CONDITION
     *
     * @mbg.generated
     */
    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition == null ? null : specialCondition.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.FREIGHT
     *
     * @return the value of co_shipping_order.FREIGHT
     *
     * @mbg.generated
     */
    public String getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.FREIGHT
     *
     * @param freight the value for co_shipping_order.FREIGHT
     *
     * @mbg.generated
     */
    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.CHECK_BY
     *
     * @return the value of co_shipping_order.CHECK_BY
     *
     * @mbg.generated
     */
    public String getCheckBy() {
        return checkBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.CHECK_BY
     *
     * @param checkBy the value for co_shipping_order.CHECK_BY
     *
     * @mbg.generated
     */
    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy == null ? null : checkBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.STATE
     *
     * @return the value of co_shipping_order.STATE
     *
     * @mbg.generated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.STATE
     *
     * @param state the value for co_shipping_order.STATE
     *
     * @mbg.generated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.CREATE_BY
     *
     * @return the value of co_shipping_order.CREATE_BY
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.CREATE_BY
     *
     * @param createBy the value for co_shipping_order.CREATE_BY
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.CREATE_DEPT
     *
     * @return the value of co_shipping_order.CREATE_DEPT
     *
     * @mbg.generated
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.CREATE_DEPT
     *
     * @param createDept the value for co_shipping_order.CREATE_DEPT
     *
     * @mbg.generated
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.CREATE_TIME
     *
     * @return the value of co_shipping_order.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.CREATE_TIME
     *
     * @param createTime the value for co_shipping_order.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.company_id
     *
     * @return the value of co_shipping_order.company_id
     *
     * @mbg.generated
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.company_id
     *
     * @param companyId the value for co_shipping_order.company_id
     *
     * @mbg.generated
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_shipping_order.company_name
     *
     * @return the value of co_shipping_order.company_name
     *
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_shipping_order.company_name
     *
     * @param companyName the value for co_shipping_order.company_name
     *
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }
}