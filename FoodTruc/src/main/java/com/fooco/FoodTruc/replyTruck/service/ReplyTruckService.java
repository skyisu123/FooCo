package com.fooco.FoodTruc.replyTruck.service;

import java.util.List;

import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

public interface ReplyTruckService {
	//////////////////////////////// By Young Min //////////////////////////
	public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck���̺� ��������
	public int selectReplyCount(int truckNum);
	public List<ReplyTruckVO> selectReplyTruck(int truckNum, int start);	//����¡ ����Ʈ��������
	public int insertReview(String replyContent, String score, int truckNum, String memberId); // ���� ���
	/////////////////////////////////////////////////////////////////////////
}
