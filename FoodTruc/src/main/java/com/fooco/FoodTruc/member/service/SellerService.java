package com.fooco.FoodTruc.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.vo.SellerVO;

public interface SellerService {
	
	//�Ǹ��� ȸ������
	public ModelAndView insertSeller(SellerVO sellerVo,  ModelAndView mav);
	// �α��� (Ǫ��Ʈ�� �Ǹ���)
	public List<SellerVO> sellerLogin(String seller_id, String seller_pw);
	//Seller_member���̺� ��������
	public List<SellerVO> selectDetailMember(String sellerId);
	
	//=========================	
		//ȸ������ ���� 
		public ModelAndView updateInfoSeller(SellerVO sellerVo,  ModelAndView mav, HttpSession session);
	//=========================	
		
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone);//����ó������
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail);//�̸��Ͽ�����
	/////////////////////////////////////////////////////////////////////////////////////
	
		
	///////////////////////////// ��й�ȣ ã�� 9/3 //////////////////////////////////
	public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail );
	/////////////////////////////////////////////////////////////////////////////////////
	
	//========================��й�ȣ �缳��9/3====================
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//========================��й�ȣ �缳��9/3 ��====================

}
