package com.fooco.FoodTruc.replyTruck.vo;

import java.sql.Date;

public class ReplyTruckVO {
	//  한줄평 식별코드 seq_reply_truck
    private int replyNum;
    //  한줄 평 작성자 아이디
    private String memberId;
    //  트럭별 식별번호( 해당 트럭에 평 )
    private int truckNum;
    //  한줄 평 내용
    private String replyContent;
    //  평점
    private String score;
    //  평 작성 날짜
    private Date replyDate;
    //  알림 확인 유무 Y / N (  N 읽음, Y은 안읽음)
    private String replyRead;
    
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(int truckNum) {
		this.truckNum = truckNum;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyRead() {
		return replyRead;
	}
	public void setReplyRead(String replyRead) {
		this.replyRead = replyRead;
	}
    
    
}
