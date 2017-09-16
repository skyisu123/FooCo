package com.fooco.FoodTruc.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.vo.SellerVO;

public interface SellerService {
	
	//판매자 회원가입
	public ModelAndView insertSeller(SellerVO sellerVo,  ModelAndView mav);
	// 로그인 (푸드트럭 판매자)
	public List<SellerVO> sellerLogin(String seller_id, String seller_pw);
	//Seller_member테이블 가져오기
	public List<SellerVO> selectDetailMember(String sellerId);
	
	//=========================	
		//회원정보 수정 
		public ModelAndView updateInfoSeller(SellerVO sellerVo,  ModelAndView mav, HttpSession session);
	//=========================	
		
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone);//연락처에의해
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail);//이메일에의해
	/////////////////////////////////////////////////////////////////////////////////////
	
		
	///////////////////////////// 비밀번호 찾기 9/3 //////////////////////////////////
	public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail );
	/////////////////////////////////////////////////////////////////////////////////////
	
	//========================비밀번호 재설정9/3====================
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//========================비밀번호 재설정9/3 끝====================

}
