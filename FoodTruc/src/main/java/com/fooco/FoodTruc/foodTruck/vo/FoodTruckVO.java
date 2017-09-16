package com.fooco.FoodTruc.foodTruck.vo;

import java.sql.Date;

public class FoodTruckVO {
	private int RN;	//rownum as RN;
    private int truckNum; //  Ʈ���� �ĺ���ȣ
    private String sellerId;     //  �Ǹ��� ���̵�
    private String businessName;    //  ��ü��
    private String truckName;    //  Ʈ���̸�
    private String truckType;    //  Ǫ������ (�׸����� ���ϰ�)
    private String truckStime;    //  Ʈ�� ���� �����ð�
    private String truckEtime;    //  Ʈ�� ���� �����ð�
    private String truckReservation;    //  ���డ�ɿ���
    private String truckInfo;    //  Ǫ��Ʈ�� �Ұ���
    private String truckImage;	// Ʈ�� ��ǥ ȫ������
    private int truckLike;    //  ���ƿ� ��
    private String addreq;    //  ��Ͽ�û Y / N (N�� ����, Y�� ��û����)
    private Date truckDate;    //  �Ǹŵ�� ��¥
    private int truckVisit;		// ��ȸ��
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
