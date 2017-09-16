package com.fooco.FoodTruc.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.vo.UserVO;

public interface UserService {
	// 로그인 (일반 이용자)
	public List<UserVO> userLogin(String member_id, String member_pw);
	// 일반 회원 등록
	public int insert(UserVO userVO);
	// 회원아이디 중복 검사 (판매자, 이용자 모두 사용)
	public int checkId(String memberId);
	
	
	//==========마이페이지 수정 시작======================	
	// 회원정보 수정
	public ModelAndView updateInfoUser(Map<String, Object> map, ModelAndView mav, HttpSession session); 
	
	//좋아요 숫자 카운팅
	public ModelAndView likeCountUser( ModelAndView mav,HttpSession session);
	
	//==========마이페이지 수정 끝======================

	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone);//연락처에의해
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail);//이메일에의해
		
	/////////////////////////////////////////////////////////////////////////////////////

	
	
	///////////////////////////// 비밀번호 찾기 9/3 //////////////////////////////////
	public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//==========사용자 회원등록 시작======================
	public ModelAndView userJoinUs(UserVO uservo,  ModelAndView mav,HttpSession session);
	//==========사용자 회원등록 끝======================	
	
	//=========비밀번호 재설정 9/3====================
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);



}
