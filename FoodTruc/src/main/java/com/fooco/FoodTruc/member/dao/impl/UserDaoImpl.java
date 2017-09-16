package com.fooco.FoodTruc.member.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.member.dao.UserDAO;
import com.fooco.FoodTruc.member.vo.UserVO;

import common.CommonDao;

@Repository
public class UserDaoImpl extends CommonDao implements UserDAO {
	
	// 아이디 중복검사 (판매자, 이용자 모두)
	@Override
	public int checkId(String memberId) {
		//int result = getSqlSession().;
		return getSqlSession().getMapper(UserDAO.class).checkId(memberId);
	}
	// 이용자 로그인 처리
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
	
	
	//===========================마이페이지 수정 ==================================================
		//회원정보 수정
		@Override
		public int updateInfoUser(Map<String, Object> map) {
			return getSqlSession().getMapper(UserDAO.class).updateInfoUser(map);
		}

		//좋아요 카운팅
		@Override
		public int likeCountUser(String id) {
			return getSqlSession().getMapper(UserDAO.class).likeCountUser(id);
		}
	//===========================마이페이지 수정 끝 ==================================================	
		
		
		
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


	///////////////////////////////// 비번찾기  9/3//////////////////////////////////////
			
	@Override
	public String selectFindPw(Map<String, Object> map) {
		return getSqlSession().getMapper(UserDAO.class).selectFindPw(map);
	}
	

	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//===========================사용자 회원가입  ==================================================
	@Override
		public int userJoinUs(UserVO userVO) {
			return getSqlSession().getMapper(UserDAO.class).userJoinUs(userVO);
		}
	//===========================사용자 회원가입 끝 ==================================================	
	
	//=========비밀번호 재설정 9/3====================	
	@Override
		public int selectCheckPw(String memberId, String memberPw) {
			return getSqlSession().getMapper(UserDAO.class).selectCheckPw(memberId,memberPw);
	}
	
	@Override
	public int UpdateNewPw(String memberId, String newMemberPw) {
		return getSqlSession().getMapper(UserDAO.class).UpdateNewPw(memberId, newMemberPw);
	}
	//=========비밀번호 재설정 9/3 끝====================	
}














