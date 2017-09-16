package com.fooco.FoodTruc.replyTruck.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.foodTruck.dao.FoodTruckDAO;
import com.fooco.FoodTruc.replyTruck.dao.ReplyTruckDAO;
import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

import common.CommonDao;

@Repository
public class ReplyTruckDaoImpl extends CommonDao implements ReplyTruckDAO{
	
	///////////////////////////////////// By Young Min ////////////////////////////////
	@Override
	public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).selectDetailReplyTruck(truckNum);
	}
	
	@Override
	public int selectReplyCount(int truckNum) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).selectReplyCount(truckNum);
	}
	
	// ������ ����¡ ó�� ����Ʈ ��������
	@Override
	public List<ReplyTruckVO> selectReplyTruck(Map<String, Object> map) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).selectReplyTruck(map);
	}
	
	// ������ ����ϱ�
	@Override
	public int insertReview(Map<String, Object> map) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).insertReview(map);
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	//===============================���������� ���� ���� =================
		//����� ������ ī����
			@Override
			public int CountContentUser(String id) {
				return getSqlSession().getMapper(ReplyTruckDAO.class).CountContentUser(id);
			}
		
		//����� ������ �ҷ�����
			@Override
			public List<ReplyTruckVO> CountContentUserList(String id){
				return getSqlSession().getMapper(ReplyTruckDAO.class).CountContentUserList(id);
			}
			@Override
			//�Ǹ��� ������ ���� �޾ƿ���
				public int countContentSeller(String sellerId) {
					return getSqlSession().getMapper(ReplyTruckDAO.class).countContentSeller(sellerId);	
				}
			
	//===============================���������� ���� �� =================	
}
