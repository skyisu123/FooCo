package com.fooco.FoodTruc.member.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.member.vo.UserVO;

public interface UserDAO {
	/* 
	 * UserVO��ü (id,pw,name, ..)�� USER_MEMBER ���̺� insert �߻�ȭ
	 * ��ȯ���� int�� �Ͽ� �������� �Ǵ� 
	 */
	public int insertData(UserVO userVO);
	
	// ���̵� �ߺ��˻�
	public int checkId(String memberId);
	
	// select �߻�ȭ
	
	// update �߻�ȭ
	
	// delete �߻�ȭ
	
	// login �߻�ȭ
	public List<UserVO> userLogin(Map<String, Object> parameterMap);
	
	
	//===========================���������� ���� ==================================================
		//ȸ������ ����
		public int updateInfoUser(Map<String, Object> map);
		//����� ���ƿ�
		public int likeCountUser(String id);		
	//===========================���������� ���� �� ==================================================

		
		
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public String selectFindIdByPhone(Map<String, Object> map);//����ó������
	public String selectFindIdByEmail(Map<String, Object> map);//�̸��Ͽ�����
	
	/////////////////////////////////////////////////////////////////////////////////////

	
	///////////////////////////// ���ã�� 9/3 //////////////////////////////////
	public String selectFindPw(Map<String, Object> map);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//===========================����� ȸ������ ����==================================================
	public int userJoinUs(UserVO userVO);
	//===========================����� ȸ������ ��==================================================
	
	
	//=========��й�ȣ �缳�� 9/3====================	
	
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//=========��й�ȣ �缳�� 9/3 ��====================	






}
