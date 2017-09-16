package com.fooco.FoodTruc.member.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.member.vo.UserVO;

public interface UserDAO {
	/* 
	 * UserVO객체 (id,pw,name, ..)을 USER_MEMBER 테이블에 insert 추상화
	 * 반환값을 int로 하여 성공유무 판단 
	 */
	public int insertData(UserVO userVO);
	
	// 아이디 중복검사
	public int checkId(String memberId);
	
	// select 추상화
	
	// update 추상화
	
	// delete 추상화
	
	// login 추상화
	public List<UserVO> userLogin(Map<String, Object> parameterMap);
	
	
	//===========================마이페이지 수정 ==================================================
		//회원정보 수정
		public int updateInfoUser(Map<String, Object> map);
		//사용자 좋아요
		public int likeCountUser(String id);		
	//===========================마이페이지 수정 끝 ==================================================

		
		
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public String selectFindIdByPhone(Map<String, Object> map);//연락처에의해
	public String selectFindIdByEmail(Map<String, Object> map);//이메일에의해
	
	/////////////////////////////////////////////////////////////////////////////////////

	
	///////////////////////////// 비번찾기 9/3 //////////////////////////////////
	public String selectFindPw(Map<String, Object> map);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//===========================사용자 회원가입 시작==================================================
	public int userJoinUs(UserVO userVO);
	//===========================사용자 회원가입 끝==================================================
	
	
	//=========비밀번호 재설정 9/3====================	
	
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//=========비밀번호 재설정 9/3 끝====================	






}
