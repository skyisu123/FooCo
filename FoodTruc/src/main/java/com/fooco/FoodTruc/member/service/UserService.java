package com.fooco.FoodTruc.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.vo.UserVO;

public interface UserService {
	// �α��� (�Ϲ� �̿���)
	public List<UserVO> userLogin(String member_id, String member_pw);
	// �Ϲ� ȸ�� ���
	public int insert(UserVO userVO);
	// ȸ�����̵� �ߺ� �˻� (�Ǹ���, �̿��� ��� ���)
	public int checkId(String memberId);
	
	
	//==========���������� ���� ����======================	
	// ȸ������ ����
	public ModelAndView updateInfoUser(Map<String, Object> map, ModelAndView mav, HttpSession session); 
	
	//���ƿ� ���� ī����
	public ModelAndView likeCountUser( ModelAndView mav,HttpSession session);
	
	//==========���������� ���� ��======================

	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone);//����ó������
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail);//�̸��Ͽ�����
		
	/////////////////////////////////////////////////////////////////////////////////////

	
	
	///////////////////////////// ��й�ȣ ã�� 9/3 //////////////////////////////////
	public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//==========����� ȸ����� ����======================
	public ModelAndView userJoinUs(UserVO uservo,  ModelAndView mav,HttpSession session);
	//==========����� ȸ����� ��======================	
	
	//=========��й�ȣ �缳�� 9/3====================
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);



}
