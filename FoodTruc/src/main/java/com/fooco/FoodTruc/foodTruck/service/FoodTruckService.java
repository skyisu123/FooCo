package com.fooco.FoodTruc.foodTruck.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

public interface FoodTruckService {
	// 판매트럭 등록
	public int insertTruck(MultipartHttpServletRequest multi, Map<String, Object> map, HttpSession session) throws Exception;

	///////////////////////////////// 위치별 ////////////////////////////
	// 위치별 리스트
	public List<FoodTruckVO> selectLocationList(String locationParam, int offsetParam);
	
	// 레코드 총 개수 구하기
	public int selectLocationTotalCount(String locationParam);
	
	///////////////////////////////////////////////////////////////////
	
	// 음식 종류별 트럭 불러오기
	public List<FoodTruckVO> typeSelect(String truckType, int offsetParam);
	//종류별 레코드수 가져오기
	public int getTotalCount(String truckType);
	
	////////////////////////////////
	
	//랭킹 트럭 등록 시작 By 형무 (동섭)
	public List<FoodTruckVO> truckRankList(int startParam, String typeParam); 
	public List<FoodTruckVO> truckRankList(int startParam, int endParam); //index페이지에 보여질 Top10
	public int rankingTotalCount();
	//랭킹 트럭 등록 완료

	///////////////////////////////상세보기//////////////////////////////////////
	public FoodTruckVO selectDetailTruck(int truckNum);	//FoodTruck테이블 가져오기
	//public List<MenuVO> selectDetailMenu(int truckNum);	//Menu테이블 가져오기
	//public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage테이블 가져오기
	//public List<SellerVO> selectDetailMember(String sellerId);	//Seller_member테이블 가져오기
	//public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck테이블 가져오기
	public void updateDetailTruck(int truckNum);	// 조회수 업데이트
	public int updateTruckLike(int truckNum);		// like 업데이트
	///////////////     S     E    O    s t a r t   /////////////////////
		
	//get openDate totalCount By SEO
	public int selectDateTotalCount(String dateParam);
	
	//get openDate List By SEO
	public List<FoodTruckVO> selectOpenDateList(String setOpenDateParam, int startParam);
	
	//get openDate List (FoodTruckList Style) By SEO
	public List<FoodTruckVO> selectOpenDateList(String setOpenDateParam, int startParam, int endParam);
	
	//get all record list foodtruck By SEO 
	public List<FoodTruckVO> list(int startParam, int endParam);
	
	//get foodAssortativeList By SEO
	public List<FoodTruckVO> foodAssortativeList(String truckTypeParam, int startParam, int endParam);
	
	//get locationList (FoodTruckList Style) By SEO
	public List<FoodTruckVO> selectLocationList(String locationParam, int startParam, int endParam);
	
	//get searchList By SEO
	public List<FoodTruckVO> searchLists( String searchOptionParam, String searchTextParam, int startParam);
	
	//get searchTotalCount By SEO
	public int searchTotalCount( String searchOptionParam, String searchTextParam );
	///////////////     S     E    O    e n d     /////////////////////
		
	
	//////////////////////////////////////My Page Edit By Hyoung Moo ///////////////////////////////////
	
	//사용자 한줄평 숫자 카운팅
	public ModelAndView CountContentUser( ModelAndView mav,HttpSession session);
	//사용자 한줄평 불러오기
	public ModelAndView CountContentUserList( ModelAndView mav,HttpSession session);
	//좋아요 불러오기
	public  ModelAndView likeCountUserList (ModelAndView mav,HttpSession session);
	//판매자 좋아요 
	public ModelAndView likeCountSeller( ModelAndView mav, HttpSession session);
	//판매자 평점
	public ModelAndView CountContentSeller( ModelAndView mav, HttpSession session);
	
	// 내 트럭 리스트 불러오기
	public ModelAndView selectMyTruckList(ModelAndView mav, HttpSession session);
	// 내 트럭 삭제하기
	public void deleteTruck(int truckNum);
	
	// 내 트럭 수정하기 위해 테이블에서 정보가져오기
	public ModelAndView truckModify(int truckNum, ModelAndView mav);
	// 내 트럭 수정
	public ModelAndView updateTruck(MultipartHttpServletRequest multi, Map<String, Object> map, ModelAndView mav,HttpSession session) throws Exception;
	/*// 내 트럭 메뉴 리스트 정보가져오기
	public ModelAndView selectTruckMenu(int truckNum, ModelAndView mav);
	// 내 트럭 메뉴 이미지 리스트 정보가져오기
	public ModelAndView selectTruckMenuImage(int menuCode, ModelAndView mav);*/
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////트럭 1단계 등록하다가 메뉴 등록할때 작성취소 누르면 //////////////////
	// 1단계 정보 삭제 (foodtruck 테이블 방금입력됬던거 삭제)
	public void deleteTruckByCancel(int truckNum);
	//////////////////////////////////////////////////////////////////////////////
	
	// Like 취소
	public int minusLike(int truckNum);
}









