package com.fooco.FoodTruc.replyTruck.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

public interface ReplyTruckDAO {
	///////////////////////////////////// By Young Min ////////////////////////////////////
	public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck테이블 가져오기
	public int selectReplyCount(int truckNum);
	public List<ReplyTruckVO> selectReplyTruck(Map<String, Object> map); // 댓글 페이징 처리 리스트 가져오기
	public int insertReview(Map<String, Object> map); // 리뷰 등록
	//////////////////////////////////////////////////////////////////////////////////////

	//===============================마이페이지 수정 시작 =================
		//사용자 한줄평
			public int CountContentUser(String id);
		
		//사용자 한줄평 불러오기
			public List<ReplyTruckVO> CountContentUserList(String id);
			
			
		//판매자 한줄평 수량 받아오기
			public int countContentSeller(String sellerId);
		
	//===============================마이페이지 수정 끝 =================
	
}
