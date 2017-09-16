package com.fooco.FoodTruc.member.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.member.vo.SellerVO;

public interface SellerDAO {
	
	//ȸ������
	public int insertSeller(SellerVO sellerVo);
	// login �߻�ȭ
	public List<SellerVO> sellerLogin(Map<String, Object> parameterMap);
	//seller_member���̺� ��������
	public List<SellerVO> selectDetailMember(String sellerId);

	//========================	
	//ȸ������ ����
	public int updateInfoSeller(SellerVO sellerVo);
	
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public String selectFindIdByPhone(Map<String, Object> map);//����ó������
	public String selectFindIdByEmail(Map<String, Object> map);//�̸��Ͽ�����
	
	/////////////////////////////////////////////////////////////////////////////////////


	///////////////////////////// ��й�ȣ ã�� 9/3 //////////////////////////////////
	public String selectFindPw(Map<String, Object> map);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	//=========��й�ȣ �缳�� 9/3====================	
	
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//=========��й�ȣ �缳�� 9/3 ��====================	



}
