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
	
	// 한줄평 페이징 처리 리스트 가져오기
	@Override
	public List<ReplyTruckVO> selectReplyTruck(Map<String, Object> map) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).selectReplyTruck(map);
	}
	
	// 한줄평 등록하기
	@Override
	public int insertReview(Map<String, Object> map) {
		return getSqlSession().getMapper(ReplyTruckDAO.class).insertReview(map);
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	//===============================마이페이지 수정 시작 =================
		//사용자 한줄평 카운팅
			@Override
			public int CountContentUser(String id) {
				return getSqlSession().getMapper(ReplyTruckDAO.class).CountContentUser(id);
			}
		
		//사용자 한줄평 불러오기
			@Override
			public List<ReplyTruckVO> CountContentUserList(String id){
				return getSqlSession().getMapper(ReplyTruckDAO.class).CountContentUserList(id);
			}
			@Override
			//판매자 한줄평 수량 받아오기
				public int countContentSeller(String sellerId) {
					return getSqlSession().getMapper(ReplyTruckDAO.class).countContentSeller(sellerId);	
				}
			
	//===============================마이페이지 수정 끝 =================	
}
