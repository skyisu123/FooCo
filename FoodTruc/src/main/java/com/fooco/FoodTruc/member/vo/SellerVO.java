package com.fooco.FoodTruc.member.vo;

import java.sql.Date;

public class SellerVO {
	//  �Ǹ��� ���̵�
    private String sellerId;
    //  �Ǹ��� ��й�ȣ
    private String sellerPw;
    //  �Ǹ��� �̸�
    private String sellerName;
    //  �Ǹ��� �̸���
    private String sellerEmail;
    //  �Ǹ��� �޴���ȭ
    private String sellerPhone;
    //  ����ڵ���ڵ�
    private String sellerCode;
    //  ����ڵ���ڵ� ��ǥ�ڸ�
    private String codeName;
    //  �� �Ǹ�����
    private String sellerLocation;
    //  �Ǹ��� ���κ�α�
    private String sellerBlogaddr;
    //  ����Ʈ ����
    private String sellerGrade;
    //  �Ǹ��� ���Գ�¥
    private Date sellerDate;
	
    public SellerVO() {}
    
    
    public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerPw() {
		return sellerPw;
	}
	public void setSellerPw(String sellerPw) {
		this.sellerPw = sellerPw;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getSellerPhone() {
		return sellerPhone;
	}
	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}
	public String getSellerCode() {
		return sellerCode;
	}
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getSellerLocation() {
		return sellerLocation;
	}
	public void setSellerLocation(String sellerLocation) {
		this.sellerLocation = sellerLocation;
	}
	public String getSellerBlogaddr() {
		return sellerBlogaddr;
	}
	public void setSellerBlogaddr(String sellerBlogaddr) {
		this.sellerBlogaddr = sellerBlogaddr;
	}
	public String getSellerGrade() {
		return sellerGrade;
	}
	public void setSellerGrade(String sellerGrade) {
		this.sellerGrade = sellerGrade;
	}
	public Date getSellerDate() {
		return sellerDate;
	}
	public void setSellerDate(Date sellerDate) {
		this.sellerDate = sellerDate;
	}
    
    
}
