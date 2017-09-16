package com.fooco.FoodTruc.foodTruck.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

public interface FoodTruckDAO {
	// �Ǹ� Ʈ�� ���
	public int insertTruck(Map<String, Object> map);
	
	////////////////////////////// ��ġ�� ///////////////////////////////
	// ��ġ�� Ʈ�� ����Ʈ ��������
	public List<FoodTruckVO> selectLocationList(Map<String, Object> map);

	// ���ڵ� �� ���� ��������
	public int selectLocationTotalCount(String locationParam);
	///////////////////////////////////////////////////////////////////

	//������ Ǫ��Ʈ�� ��������
	public List<FoodTruckVO> typeSelect(Map<String, Object> map);
	//������ ���ڵ�� ��������
	public int getTotalCount(String truckType);
	
	////////////////
	//��ŷƮ�� ����
	public int rankingTotalCount();
	public List<FoodTruckVO> trukRankList(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingLike(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingScore(Map<String, Object> map);
	public List<FoodTruckVO> selectRankingReply(Map<String, Object> map);
	//��ŷƮ�� ��
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

	
	///////////////////////////////////// �󼼺��� /////////////////////////////////////////////
	public List<FoodTruckVO> selectDetailTruck(int truckNum);	// foodtruck���̺� ��������
	//public List<MenuVO> selectDetailMenu(int truckNum);		// Menu���̺� ��������
	//public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage���̺� ��������
	//public List<SellerVO> selectDetailMember(String sellerId);	//seller_member���̺� ��������
	//public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum);	//reply_truck���̺� ��������
	public void updateDetailTruck(int truckNum);	// ��ȸ�� ������Ʈ
	public int updateTruckLike(int truckNum);		// like ������Ʈ
	public int selectTruckLike(int truckNum);		// like �� �������� (ajax��ſ� ����)
	/////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////My Page Edit By Hyoung Moo ///////////////////////////////////
	// mypage ���ƿ� �� ��������
	public  FoodTruckVO likeCountSeller(String id);

	//����� ���ƿ� �ҷ�����
	public List<FoodTruckVO>likeCountUserList(String id);
	
	// ���� Ʈ�� ���� ��������
	public int selectMyTruckNumber(String sellerId);
	
	// �� Ʈ�� ����Ʈ ��������
	public List<FoodTruckVO> selectMyTruckList(String sellerId);
	// �� Ʈ�� �����ϱ�
	public void deleteTruck(int truckNum);
	// �� Ʈ�� ����
	public int updateTruck(Map<String, Object> map);
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////Ʈ�� 1�ܰ� ����ϴٰ� �޴� ����Ҷ� �ۼ���� ������ //////////////////
	// 1�ܰ� ���� ���� (foodtruck ���̺� ����Է���� ����)
	public void deleteTruckByCancel(int truckNum);
	
	//////////////////////////////////////////////////////////////////////////////
	
	// Like ���
	public int minusLike(int truckNum);
}



















