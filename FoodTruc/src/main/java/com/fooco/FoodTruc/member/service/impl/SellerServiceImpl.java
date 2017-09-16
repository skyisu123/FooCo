package com.fooco.FoodTruc.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.dao.impl.SellerDaoImpl;
import com.fooco.FoodTruc.member.service.SellerService;
import com.fooco.FoodTruc.member.vo.SellerVO;

@Service("sellerService")
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerDaoImpl sellerDao;
	
	// 판매자 회원가입
	@Override
	public ModelAndView insertSeller(SellerVO sellerVo, ModelAndView mav) {
		int result = sellerDao.insertSeller(sellerVo);
		if(result > 0){
			mav.addObject("isSellerJoin", "판매자 회원가입 성공");
			mav.setViewName("forward:/user/memberLogin.do");
		}
		else{
			mav.addObject("isSellerJoin", "판매자 회원가입 실패");
			mav.setViewName("/user/MemberJoin.do");
		}
		
		return mav;
	}

	//판매자 로그인 처리
	@Override
	public List<SellerVO> sellerLogin(String seller_id, String seller_pw) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("seller_id", seller_id);
		parameterMap.put("seller_pw", seller_pw);
		List<SellerVO> sellerVo = sellerDao.sellerLogin(parameterMap);
		/*for(SellerVO seller : sellerVo){
			System.out.println(seller.getSellerId());
			System.out.println(seller.getSellerPw());
			System.out.println(seller.getSellerName());
			System.out.println(seller.getSellerEmail());
			System.out.println(seller.getSellerPhone());
			System.out.println(seller.getSellerCode());
			System.out.println(seller.getCodeName());
			System.out.println(seller.getSellerLocation());
			System.out.println(seller.getSellerBlogaddr());
			System.out.println(seller.getSellerGrade());
			System.out.println(seller.getSellerDate());
		}*/
		return sellerVo;
	}
	
	@Override
	public List<SellerVO> selectDetailMember(String sellerId) {
		List<SellerVO> list = sellerDao.selectDetailMember(sellerId);
		if(!list.isEmpty()){
			return list;
		}
		else return null;
	}
	
	//==========마이페이지 수정 시작======================	
		//판매자 회원정보 수정 
		@Override
		public ModelAndView updateInfoSeller(SellerVO sellerVo,  ModelAndView mav, HttpSession session) {
			int result = sellerDao.updateInfoSeller(sellerVo);
			String id = sellerVo.getSellerId();
			String pw = sellerVo.getSellerPw();
			System.out.println("받은값 : " + id);
			System.out.println("받은값 : " + pw);
			//session.invalidate(); //session 초기화
			List<SellerVO> list = sellerLogin(id,pw);//다시 수정된 session 넣기
			
			if(!list.isEmpty() || list.size() != 0 && result > 0){
				// 로그인 정보가 일치하는 데이터가 있으면
				SellerVO seller = list.get(0);
				session.setAttribute("memberInfo", seller);	// 로그인 정보가 맞으면 세션에 해당 일반회원의 로그인 정보를 저장.
				mav.addObject("updateMsgS", "회원정보가 수정되었습니다");
				mav.setViewName("redirect:/user/memberMyPage.do");
			}
			else if(list.size() == 0 || list.isEmpty() && result <= 0){
				SellerVO seller = list.get(0);
				session.setAttribute("memberInfo", seller);	// 로그인 정보가 맞으면 세션에 해당 판매자회원의 로그인 정보를 저장.
				mav.addObject("updateMsgS", "회원정보가 수정되지 않았습니다");
				mav.setViewName("user/memberMyInfo");
				}
			return mav;
		}
	//============================================

		
	///////////////////////////////// Find Id By Young Min //////////////////////////////////////
			
	@Override
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
		
		String memberId = sellerDao.selectFindIdByPhone(map);
		// 판매자까지 조회했으니 찾았건 못찾았건 무조건 분기 두지말고 리턴.
		mav.addObject("memberId", memberId);
		mav.setViewName("user/ajax/findId_resultPro");
		
		return mav;
	}
	
	@Override
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberEmail", memberEmail);
		
		String memberId = sellerDao.selectFindIdByEmail(map);
		
		mav.addObject("memberId", memberId);
		mav.setViewName("user/ajax/findId_resultPro");
		
		return mav;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////


	
	
		///////////////////////////////// 비밀번호 찾기 9/3 //////////////////////////////////////
		
		@Override
		public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberPhone", memberPhone);
		map.put("memberEmail", memberEmail);
		
		String memberPw = sellerDao.selectFindPw(map);
		System.out.println(memberPw);
		// 판매자까지 조회했으니 찾았건 못찾았건 무조건 분기 두지말고 리턴.
		mav.addObject("memberPw", memberPw);
		mav.setViewName("user/ajax/findPw_resultPro");
		
		return mav;
		}

		/////////////////////////////////////////////////////////////////////////////////////////////
		
		//=========비밀번호 재설정 9/3====================
		@Override	
		public int selectCheckPw(String memberId, String memberPw) {
		int result= sellerDao.selectCheckPw(memberId,memberPw);
		return result;
		}
		
		@Override
		public int UpdateNewPw(String memberId, String newMemberPw) {
		int result = sellerDao.UpdateNewPw(memberId,newMemberPw);
		return result;
		}

		//=========비밀번호 재설정 9/3 끝====================


}
