package com.fooco.FoodTruc.member.vo;

import java.sql.Date;

public class SellerVO {
	//  판매자 아이디
    private String sellerId;
    //  판매자 비밀번호
    private String sellerPw;
    //  판매자 이름
    private String sellerName;
    //  판매자 이메일
    private String sellerEmail;
    //  판매자 휴대전화
    private String sellerPhone;
    //  사업자등록코드
    private String sellerCode;
    //  사업자등록코드 대표자명
    private String codeName;
    //  주 판매지역
    private String sellerLocation;
    //  판매자 개인블로그
    private String sellerBlogaddr;
    //  디폴트 새싹
    private String sellerGrade;
    //  판매자 가입날짜
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
