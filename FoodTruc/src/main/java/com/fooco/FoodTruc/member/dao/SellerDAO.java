package com.fooco.FoodTruc.member.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.member.vo.SellerVO;

public interface SellerDAO {
	
	//회원가입
	public int insertSeller(SellerVO sellerVo);
	// login 추상화
	public List<SellerVO> sellerLogin(Map<String, Object> parameterMap);
	//seller_member테이블 가져오기
	public List<SellerVO> selectDetailMember(String sellerId);

	//========================	
	//회원정보 수정
	public int updateInfoSeller(SellerVO sellerVo);
	
	///////////////////////////// Find Id By Young Min //////////////////////////////////
	public String selectFindIdByPhone(Map<String, Object> map);//연락처에의해
	public String selectFindIdByEmail(Map<String, Object> map);//이메일에의해
	
	/////////////////////////////////////////////////////////////////////////////////////


	///////////////////////////// 비밀번호 찾기 9/3 //////////////////////////////////
	public String selectFindPw(Map<String, Object> map);
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	//=========비밀번호 재설정 9/3====================	
	
	public int selectCheckPw(String memberId, String memberPw);
	public int UpdateNewPw(String memberId, String newMemberPw);
	//=========비밀번호 재설정 9/3 끝====================	



}
