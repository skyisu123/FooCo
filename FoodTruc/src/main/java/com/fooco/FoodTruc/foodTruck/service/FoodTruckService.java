package com.fooco.FoodTruc.foodTruck.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

public interface FoodTruckService {
	// �Ǹ�Ʈ�� ���
	public int insertTruck(MultipartHttpServletRequest multi, Map<String, Object> map, HttpSession session) throws Exception;

	///////////////////////////////// ��ġ�� ////////////////////////////
	// ��ġ�� ����Ʈ
	public List<FoodTruckVO> selectLocationList(String locationParam, int offsetParam);
	
	// ���ڵ� �� ���� ���ϱ�
	public int selectLocationTotalCount(String locationParam);
	
	///////////////////////////////////////////////////////////////////
	
	// ���� ������ Ʈ�� �ҷ�����
	public List<FoodTruckVO> typeSelect(String truckType, int offsetParam);
	//������ ���ڵ�� ��������
	public int getTotalCount(String truckType);
	
	////////////////////////////////
	
	//��ŷ Ʈ�� ��� ���� By ���� (����)
	public List<FoodTruckVO> truckRankList(int startParam, String typeParam); 
	public List<FoodTruckVO> truckRankList(int startParam, int endParam); //index�������� ������ Top10
	public int rankingTotalCount();
	//��ŷ Ʈ�� ��� �Ϸ�

	///////////////////////////////�󼼺���//////////////////////////////////////
	public FoodTruckVO selectDetailTruck(int truckNum);	//FoodTruck���̺� ��������
	//public List<MenuVO> selectDetailMenu(int truckNum);	//Menu���̺� ��������
	//public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage���̺� ��������
	//public List<SellerVO> selectDetailMember(String sellerId);	//Seller_member���̺� ��������
	//public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck���̺� ��������
	public void updateDetailTruck(int truckNum);	// ��ȸ�� ������Ʈ
	public int updateTruckLike(int truckNum);		// like ������Ʈ
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
	
	//����� ������ ���� ī����
	public ModelAndView CountContentUser( ModelAndView mav,HttpSession session);
	//����� ������ �ҷ�����
	public ModelAndView CountContentUserList( ModelAndView mav,HttpSession session);
	//���ƿ� �ҷ�����
	public  ModelAndView likeCountUserList (ModelAndView mav,HttpSession session);
	//�Ǹ��� ���ƿ� 
	public ModelAndView likeCountSeller( ModelAndView mav, HttpSession session);
	//�Ǹ��� ����
	public ModelAndView CountContentSeller( ModelAndView mav, HttpSession session);
	
	// �� Ʈ�� ����Ʈ �ҷ�����
	public ModelAndView selectMyTruckList(ModelAndView mav, HttpSession session);
	// �� Ʈ�� �����ϱ�
	public void deleteTruck(int truckNum);
	
	// �� Ʈ�� �����ϱ� ���� ���̺��� ������������
	public ModelAndView truckModify(int truckNum, ModelAndView mav);
	// �� Ʈ�� ����
	public ModelAndView updateTruck(MultipartHttpServletRequest multi, Map<String, Object> map, ModelAndView mav,HttpSession session) throws Exception;
	/*// �� Ʈ�� �޴� ����Ʈ ������������
	public ModelAndView selectTruckMenu(int truckNum, ModelAndView mav);
	// �� Ʈ�� �޴� �̹��� ����Ʈ ������������
	public ModelAndView selectTruckMenuImage(int menuCode, ModelAndView mav);*/
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////Ʈ�� 1�ܰ� ����ϴٰ� �޴� ����Ҷ� �ۼ���� ������ //////////////////
	// 1�ܰ� ���� ���� (foodtruck ���̺� ����Է���� ����)
	public void deleteTruckByCancel(int truckNum);
	//////////////////////////////////////////////////////////////////////////////
	
	// Like ���
	public int minusLike(int truckNum);
}









