package com.fooco.FoodTruc.foodTruck.vo;

import java.sql.Date;

public class FoodTruckVO {
	private int RN;	//rownum as RN;
    private int truckNum; //  트럭별 식별번호
    private String sellerId;     //  판매자 아이디
    private String businessName;    //  업체명
    private String truckName;    //  트럭이름
    private String truckType;    //  푸드종류 (테마별에 쓰일것)
    private String truckStime;    //  트럭 시작 영업시간
    private String truckEtime;    //  트럭 종료 영업시간
    private String truckReservation;    //  예약가능여부
    private String truckInfo;    //  푸드트럭 소개글
    private String truckImage;	// 트럭 대표 홍보사진
    private int truckLike;    //  좋아요 수
    private String addreq;    //  등록요청 Y / N (N은 승인, Y는 요청상태)
    private Date truckDate;    //  판매등록 날짜
    private int truckVisit;		// 조회수
    private String truckLocation;
    public FoodTruckVO() {}

    public int getRN() {
		return RN;
	}

	public void setRN(int RN) {
		this.RN = RN;
	}
    
	public int getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(int truckNum) {
		this.truckNum = truckNum;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getTruckName() {
		return truckName;
	}
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public String getTruckStime() {
		return truckStime;
	}
	public void setTruckStime(String truckStime) {
		this.truckStime = truckStime;
	}
	public String getTruckEtime() {
		return truckEtime;
	}
	public void setTruckEtime(String truckEtime) {
		this.truckEtime = truckEtime;
	}
	public String getTruckReservation() {
		return truckReservation;
	}
	public void setTruckReservation(String truckReservation) {
		this.truckReservation = truckReservation;
	}
	public String getTruckInfo() {
		return truckInfo;
	}
	public void setTruckInfo(String truckInfo) {
		this.truckInfo = truckInfo;
	}
	public int getTruckLike() {
		return truckLike;
	}
	public void setTruckLike(int truckLike) {
		this.truckLike = truckLike;
	}
	public String getAddreq() {
		return addreq;
	}
	public void setAddreq(String addreq) {
		this.addreq = addreq;
	}
	public Date getTruckDate() {
		return truckDate;
	}
	public void setTruckDate(Date truckDate) {
		this.truckDate = truckDate;
	}
	public String getTruckImage() {
		return truckImage;
	}


	public void setTruckImage(String truckImage) {
		this.truckImage = truckImage;
	}

	public int getTruckVisit() {
		return truckVisit;
	}

	public void setTruckVisit(int truckVisit) {
		this.truckVisit = truckVisit;
	}

	public String getTruckLocation() {
		return truckLocation;
	}

	public void setTruckLocation(String truckLocation) {
		this.truckLocation = truckLocation;
	}

}
