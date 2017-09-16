package com.fooco.FoodTruc.member.vo;

import java.sql.Date;

public class UserVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private String memberAddr;
	private Date memberDate;
	
	public UserVO() {}
	
	public UserVO(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone,
			String memberAddr, Date memberDate) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberAddr = memberAddr;
		this.memberDate = memberDate;
	}


	public String getmemberId() {
		return memberId;
	}
	public void setmemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getmemberPw() {
		return memberPw;
	}
	public void setmemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getmemberName() {
		return memberName;
	}
	public void setmemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getmemberEmail() {
		return memberEmail;
	}
	public void setmemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getmemberPhone() {
		return memberPhone;
	}
	public void setmemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getmemberAddr() {
		return memberAddr;
	}
	public void setmemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public Date getmemberDate() {
		return memberDate;
	}
	public void setmemberDate(Date memberDate) {
		this.memberDate = memberDate;
	}
	@Override
	public String toString() {
		return "UserVO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberAddr=" + memberAddr
				+ ", memberDate=" + memberDate + "]";
	}
	
	
}
