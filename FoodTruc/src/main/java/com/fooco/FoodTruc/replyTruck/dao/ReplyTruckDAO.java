package com.fooco.FoodTruc.replyTruck.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

public interface ReplyTruckDAO {
	///////////////////////////////////// By Young Min ////////////////////////////////////
	public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck���̺� ��������
	public int selectReplyCount(int truckNum);
	public List<ReplyTruckVO> selectReplyTruck(Map<String, Object> map); // ��� ����¡ ó�� ����Ʈ ��������
	public int insertReview(Map<String, Object> map); // ���� ���
	//////////////////////////////////////////////////////////////////////////////////////

	//===============================���������� ���� ���� =================
		//����� ������
			public int CountContentUser(String id);
		
		//����� ������ �ҷ�����
			public List<ReplyTruckVO> CountContentUserList(String id);
			
			
		//�Ǹ��� ������ ���� �޾ƿ���
			public int countContentSeller(String sellerId);
		
	//===============================���������� ���� �� =================
	
}
