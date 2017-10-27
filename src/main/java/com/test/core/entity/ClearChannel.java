package com.test.core.entity;

public class ClearChannel {
    
    private String channelCode;
    private String currency;
    private String payTime;
    private String paymentId;
    private String paymentIdOld;
    private String transNo;
    private String transNoOld;
    private String successAmount;
    private String transType;
    
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentIdOld() {
		return paymentIdOld;
	}
	public void setPaymentIdOld(String paymentIdOld) {
		this.paymentIdOld = paymentIdOld;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getTransNoOld() {
		return transNoOld;
	}
	public void setTransNoOld(String transNoOld) {
		this.transNoOld = transNoOld;
	}
	public String getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(String successAmount) {
		this.successAmount = successAmount;
	}
    
}
