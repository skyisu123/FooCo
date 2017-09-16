package com.fooco.FoodTruc.foodTruck.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.foodTruck.dao.FoodTruckDAO;
import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

import common.CommonDao;

@Repository
public class FoodTruckDaoImpl extends CommonDao implements FoodTruckDAO{
	
	// 판매 트럭 등록
	@Override
	public int insertTruck(Map<String, Object> map) {
		return getSqlSession().getMapper(FoodTruckDAO.class).insertTruck(map);
	}
	
	///////////////////////////////// 위치별 /////////////////////////////////////
	@Override
	public List<FoodTruckVO> selectLocationList(Map<String, Object> map) {
		return getSqlSession().getMapper(FoodTruckDAO.class).selectLocationList(map);
	}
	
	@Override
	public int selectLocationTotalCount(String locationParam) {
		return getSqlSession().getMapper(FoodTruckDAO.class).selectLocationTotalCount(locationParam);
	}
	
	
	////////////////////////////////////////////////////////////////////////////
	// 랭킹트럭 시작
	@Override
	public int rankingTotalCount() {
		return getSqlSession().getMapper(FoodTruckDAO.class).rankingTotalCount();
	}
	
	@Override
	public List<FoodTruckVO> trukRankList(Map<String, Object> map) {
	
		return getSqlSession().getMapper(FoodTruckDAO.class).trukRankList(map);
	}
	
	@Override
	public List<FoodTruckVO> selectRankingLike(Map<String, Object> map) {
	
		return getSqlSession().getMapper(FoodTruckDAO.class).selectRankingLike(map);
	}
	
	@Override
	public List<FoodTruckVO> selectRankingScore(Map<String, Object> map) {
	
	return getSqlSession().getMapper(FoodTruckDAO.class).selectRankingScore(map);
	}
	
	@Override
	public List<FoodTruckVO> selectRankingReply(Map<String, Object> map) {
		
	return getSqlSession().getMapper(FoodTruckDAO.class).selectRankingReply(map);
	}
	// 랭킹끝
	
	
	//종류별 푸드트럭 불러오기
	@Override
	public List<FoodTruckVO> typeSelect(Map<String, Object> map) {
		return getSqlSession().getMapper(FoodTruckDAO.class).typeSelect(map);
	}
	//종류별 페이징을위한 레코드수 구하기
	@Override
	public int getTotalCount(String truckType) {
		return getSqlSession().getMapper(FoodTruckDAO.class).getTotalCount(truckType);
	}
	
	//////////////////////S E O s t a r t /////////////////////////
	// Service openDate totalCount By SEO
	@Override
	public int selectDateTotalCount(String dateParam) {
	
	return getSqlSession().getMapper(FoodTruckDAO.class).selectDateTotalCount(dateParam);
	}
	
	// Service openDate FoodTruck List By SEO
	@Override
	public List<FoodTruckVO> selectOpenDateList(Map<String, Object> mapParam) {
	return getSqlSession().getMapper(FoodTruckDAO.class).selectOpenDateList(mapParam);
	}
	
	// get List all Food Truck By SEO
	@Override
	public List<FoodTruckVO> list(int startParam, int endParam) {
		
		return getSqlSession().getMapper(FoodTruckDAO.class).list(startParam, endParam);
	}
	
	// get searchLists By SEO
	@Override
	public List<FoodTruckVO> searchLists( Map<String, Object> map ) {
		return getSqlSession().getMapper(FoodTruckDAO.class).searchLists( map );
	}
	
	//get searchTotalCount By SEO
	@Override
	public int searchTotalCount( Map<String, Object> map ) {
		
		return getSqlSession().getMapper(FoodTruckDAO.class).searchTotalCount( map );
	}
	////////////////////// S E O e n d /////////////////////////


		
	////////////////////////////////////////////// 상세보기 ////////////////////////////////////
	@Override
	public List<FoodTruckVO> selectDetailTruck(int truckNum){
		return getSqlSession().getMapper(FoodTruckDAO.class).selectDetailTruck(truckNum);
	}
	// 조회수 업데이트
	@Override
	public void updateDetailTruck(int truckNum) {
		getSqlSession().getMapper(FoodTruckDAO.class).updateDetailTruck(truckNum);
	}
	// like 업데이트
	@Override
	public int updateTruckLike(int truckNum) {
		return getSqlSession().getMapper(FoodTruckDAO.class).updateTruckLike(truckNum);
	}
	
	@Override
	public int selectTruckLike(int truckNum) {
		return getSqlSession().getMapper(FoodTruckDAO.class).selectTruckLike(truckNum);
	}
	/////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////// My Page Edit By Hyoung Moo ///////////////////////////////////
	// mypage 좋아요 수 가져오기
	@Override
	public FoodTruckVO likeCountSeller(String id) {
		return getSqlSession().getMapper(FoodTruckDAO.class).likeCountSeller(id);	
	}

	//좋아요 불러오기
	@Override
	public List<FoodTruckVO>likeCountUserList(String id){
		return getSqlSession().getMapper(FoodTruckDAO.class).likeCountUserList(id);
	}
	
	// 나의 트럭 갯수 가져오기
	@Override
	public int selectMyTruckNumber(String sellerId) {
		return getSqlSession().getMapper(FoodTruckDAO.class).selectMyTruckNumber(sellerId);
	}
	
	// 내 트럭 리스트 가져오기
	@Override
	public List<FoodTruckVO> selectMyTruckList(String sellerId) {
		return getSqlSession().getMapper(FoodTruckDAO.class).selectMyTruckList(sellerId);
	}
	// 내 트럭 삭제하기
	@Override
	public void deleteTruck(int truckNum) {
		getSqlSession().getMapper(FoodTruckDAO.class).deleteTruck(truckNum);
	}
	
	// 내 트럭 수정처리
	@Override
	public int updateTruck(Map<String, Object> map) {
		return getSqlSession().getMapper(FoodTruckDAO.class).updateTruck(map);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////트럭 1단계 등록하다가 메뉴 등록할때 작성취소 누르면 //////////////////
	@Override
	public void deleteTruckByCancel(int truckNum) {
		getSqlSession().getMapper(FoodTruckDAO.class).deleteTruckByCancel(truckNum);
	}

	@Override
	public int minusLike(int truckNum) {
		return getSqlSession().getMapper(FoodTruckDAO.class).minusLike(truckNum);
	}
	
	//////////////////////////////////////////////////////////////////////////////
}
















