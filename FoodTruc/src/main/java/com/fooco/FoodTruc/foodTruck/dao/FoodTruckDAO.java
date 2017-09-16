package com.fooco.FoodTruc.foodTruck.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

public interface FoodTruckDAO {
	// 판매 트럭 등록
	public int insertTruck(Map<String, Object> map);
	
	////////////////////////////// 위치별 ///////////////////////////////
	// 위치별 트럭 리스트 가져오기
	public List<FoodTruckVO> selectLocationList(Map<String, Object> map);

	// 레코드 총 개수 가져오기
	public int selectLocationTotalCount(String locationParam);
	///////////////////////////////////////////////////////////////////

	//종류별 푸드트럭 가져오기
	public List<FoodTruckVO> typeSelect(Map<String, Object> map);
	//종류별 레코드수 가져오기
	public int getTotalCount(String truckType);
	
	////////////////
	//링킹트럭 시작
	public int rankingTotalCount();
	public List<FoodTruckVO> trukRankList(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingLike(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingScore(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingReply(Map<String, Object> map);
	//랭킹트럭 끝
	////////////
	
	/////////////////////    S  E  O    s t a r t    ///////////////////
	//openDate totalCount By SEO
	public int selectDateTotalCount(String dateParam);
	
	//get List openService FoodTruck By SEO
	public List<FoodTruckVO> selectOpenDateList(Map<String, Object> map);
	
	//get List all Food Truck By SEO
	public List<FoodTruckVO> list(int startParam, int endParam);
	
	//get searchLists By SEO
	public List<FoodTruckVO> searchLists( Map<String, Object> map );
	
	//get searchTotal Count By SEO
	public int searchTotalCount( Map<String, Object> map );
	/////////////////////    S  E  O    e n d    ///////////////////

	
	///////////////////////////////////// 상세보기 /////////////////////////////////////////////
	public List<FoodTruckVO> selectDetailTruck(int truckNum);	// foodtruck테이블 가져오기
	//public List<MenuVO> selectDetailMenu(int truckNum);		// Menu테이블 가져오기
	//public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage테이블 가져오기
	//public List<SellerVO> selectDetailMember(String sellerId);	//seller_member테이블 가져오기
	//public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck테이블 가져오기
	public void updateDetailTruck(int truckNum);	// 조회수 업데이트
	public int updateTruckLike(int truckNum);		// like 업데이트
	public int selectTruckLike(int truckNum);		// like 수 가져오기 (ajax통신에 쓰임)
	/////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////My Page Edit By Hyoung Moo ///////////////////////////////////
	// mypage 좋아요 수 가져오기
	public  FoodTruckVO likeCountSeller(String id);

	//사용자 좋아요 불러오기
	public List<FoodTruckVO>likeCountUserList(String id);
	
	// 나의 트럭 갯수 가져오기
	public int selectMyTruckNumber(String sellerId);
	
	// 내 트럭 리스트 가져오기
	public List<FoodTruckVO> selectMyTruckList(String sellerId);
	// 내 트럭 삭제하기
	public void deleteTruck(int truckNum);
	// 내 트럭 수정
	public int updateTruck(Map<String, Object> map);
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////트럭 1단계 등록하다가 메뉴 등록할때 작성취소 누르면 //////////////////
	// 1단계 정보 삭제 (foodtruck 테이블 방금입력됬던거 삭제)
	public void deleteTruckByCancel(int truckNum);
	
	//////////////////////////////////////////////////////////////////////////////
	
	// Like 취소
	public int minusLike(int truckNum);
}



















