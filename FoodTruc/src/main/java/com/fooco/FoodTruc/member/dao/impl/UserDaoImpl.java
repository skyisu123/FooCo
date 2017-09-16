package com.fooco.FoodTruc.member.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.member.dao.UserDAO;
import com.fooco.FoodTruc.member.vo.UserVO;

import common.CommonDao;

@Repository
public class UserDaoImpl extends CommonDao implements UserDAO {
	
	// ���̵� �ߺ��˻� (�Ǹ���, �̿��� ���)
	@Override
	public int checkId(String memberId) {
		//int result = getSqlSession().;
		return getSqlSession().getMapper(UserDAO.class).checkId(memberId);
	}
	// �̿��� �α��� ó��
	@Override
	public List<UserVO> userLogin(Map<String, Object> parameter) {
		/*System.out.println(parameter.get("member_id"));
		System.out.println(parameter.get("member_pw"));*/
		return getSqlSession().getMapper(UserDAO.class).userLogin(parameter);
	}
	@Override
	public int insertData(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//===========================���������� ���� ==================================================
		//ȸ������ ����
		@Override
		public int updateInfoUser(Map<String, Object> map) {
			return getSqlSession().getMapper(UserDAO.class).updateInfoUser(map);
		}

		//���ƿ� ī����
		@Override
		public int likeCountUser(String id) {
			return getSqlSession().getMapper(UserDAO.class).likeCountUser(id);
		}
	//===========================���������� ���� �� ==================================================	
		
		
		
	///////////////////////////////// Find Id By Young Min //////////////////////////////////////
			
	@Override
	public String selectFindIdByPhone(Map<String, Object> map) {
		return getSqlSession().getMapper(UserDAO.class).selectFindIdByPhone(map);
	}
	
	@Override
	public String selectFindIdByEmail(Map<String, Object> map) {
		return getSqlSession().getMapper(UserDAO.class).selectFindIdByEmail(map);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////


	///////////////////////////////// ���ã��  9/3//////////////////////////////////////
			
	@Override
	public String selectFindPw(Map<String, Object> map) {
		return getSqlSession().getMapper(UserDAO.class).selectFindPw(map);
	}
	

	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//===========================����� ȸ������  ==================================================
	@Override
		public int userJoinUs(UserVO userVO) {
			return getSqlSession().getMapper(UserDAO.class).userJoinUs(userVO);
		}
	//===========================����� ȸ������ �� ==================================================	
	
	//=========��й�ȣ �缳�� 9/3====================	
	@Override
		public int selectCheckPw(String memberId, String memberPw) {
			return getSqlSession().getMapper(UserDAO.class).selectCheckPw(memberId,memberPw);
	}
	
	@Override
	public int UpdateNewPw(String memberId, String newMemberPw) {
		return getSqlSession().getMapper(UserDAO.class).UpdateNewPw(memberId, newMemberPw);
	}
	//=========��й�ȣ �缳�� 9/3 ��====================	
}














