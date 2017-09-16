package com.fooco.FoodTruc.query.vo;

public class UserQueryVO {
	private int queryNum;
	private String queryType;
	private String queryTitle;
	private String queryContent;
	private String queryRead;
	private java.sql.Date queryDate;
	private String memberId;
	private String sellerId;
	
	public UserQueryVO(){}

	public int getQueryNum() {
		return queryNum;
	}

	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryTitle() {
		return queryTitle;
	}

	public void setQueryTitle(String queryTitle) {
		this.queryTitle = queryTitle;
	}

	public String getQueryContent() {
		return queryContent;
	}

	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}

	public String getQueryRead() {
		return queryRead;
	}

	public void setQueryRead(String queryRead) {
		this.queryRead = queryRead;
	}

	public java.sql.Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(java.sql.Date queryDate) {
		this.queryDate = queryDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	
}






























