package com.fooco.FoodTruc.replyTruck.vo;

import java.sql.Date;

public class ReplyTruckVO {
	//  ������ �ĺ��ڵ� seq_reply_truck
    private int replyNum;
    //  ���� �� �ۼ��� ���̵�
    private String memberId;
    //  Ʈ���� �ĺ���ȣ( �ش� Ʈ���� �� )
    private int truckNum;
    //  ���� �� ����
    private String replyContent;
    //  ����
    private String score;
    //  �� �ۼ� ��¥
    private Date replyDate;
    //  �˸� Ȯ�� ���� Y / N (  N ����, Y�� ������)
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
