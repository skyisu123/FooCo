package com.fooco.FoodTruc.query.vo;

public class ReplyQueryVO {
	private String sellerId;
	private String replyTitle;
	private String replyContent;
	private java.sql.Date replyDate;
	private int queryNum;
	private int queryCode;
	
	public ReplyQueryVO(){}
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getReplyTitle() {
		return replyTitle;
	}
	public void setReplyTitle(String replyTitle) {
		this.replyTitle = replyTitle;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public java.sql.Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(java.sql.Date replyDate) {
		this.replyDate = replyDate;
	}
	public int getQueryNum() {
		return queryNum;
	}
	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}
	public int getQueryCode() {
		return queryCode;
	}
	public void setQueryCode(int queryCode) {
		this.queryCode = queryCode;
	}
	
	
}
