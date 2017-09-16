package com.fooco.FoodTruc.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.service.FoodTruckService;
import com.fooco.FoodTruc.member.service.SellerService;
import com.fooco.FoodTruc.member.service.UserService;
import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;

/*
User (푸드트럭을 이용하는 이용자) 컨트롤러
로그인 or 회원가입 or 수정 or 삭제 등의 모델로 이동하거나 뷰페이지로 이동
*/
@Controller
@RequestMapping("/user")
public class MemberController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private FoodTruckService foodtruckService;
	
	
	////////////////////////////////////////By Young Min //////////////////////////////////
	
	//푸드트럭 운영자 회원가입 페이지이동
	@RequestMapping(value="/sellerJoin.do", method=RequestMethod.GET)
	public String sellerJoin(){
		
		return "user/sellerJoin";
	}
	
	//푸드트럭 일반 이용자 회원가입 페이지이동
	@RequestMapping(value="/userJoin.do")
	public String userJoin(){
		
		return "user/userJoin";
	}
	
	// 이용자 , 판매자 회원가입 페이지로 이동
	@RequestMapping(value="/memberJoin.do", method=RequestMethod.GET)
	public String goJoin(){
		
		return "user/MemberJoin";
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value="/memberIdCheck.do", method=RequestMethod.POST)
	public void memberIdCheck(Model model, HttpServletRequest req, HttpServletResponse res) throws IOException{
		int result = userService.checkId(req.getParameter("id"));
		
		PrintWriter out = res.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	
	// 판매자 회원가입 처리
	@RequestMapping(value="/sellerJoin.do", method=RequestMethod.POST)
	public ModelAndView sellerJoinAction(@ModelAttribute SellerVO sellerVo){
		
		ModelAndView mav = new ModelAndView();
		
		mav = sellerService.insertSeller(sellerVo, mav);

		return mav;
	}
	//로그인 페이지 이동(이용자, 판매자 둘 다)
	@RequestMapping(value="/memberLogin.do")
	public void memberLogin(){}	//반환타입 void로 하면 매핑값으로 컨테이너가 찾아감.
	
	//로그인 처리 (이용자)
	@RequestMapping(value="/userLogin.do", method=RequestMethod.POST)
	public ModelAndView userLogin(HttpSession session, @RequestParam String member_id, @RequestParam String member_pw){
		//일치하는 정보 없으면 addObject로 로그인 오류 메시지 전달
		ModelAndView mav = new ModelAndView();
		//먼저 이용자 로그인 정보 검색
		List<UserVO> userVo = userService.userLogin(member_id, member_pw);
		
		if(!userVo.isEmpty() || userVo.size() != 0){
			
			if(userVo.get(0).getmemberId().equals("admin")){	// 관리자 아이디면
				session.setAttribute("admin", userVo.get(0));
				mav.setViewName("redirect:http://localhost:8080/FooCoAdmin/material-dashboard-html-v1.1.1/admin/index.jsp");
				return mav;
			}
			// 로그인 정보가 일치하는 데이터가 있으면
			UserVO user = userVo.get(0);
			session.setAttribute("memberInfo", user);	// 로그인 정보가 맞으면 세션에 해당 일반회원의 로그인 정보를 저장.
			mav.setViewName("redirect:/truck/trucFoodTrucList.do");
		}
		else if(userVo.size() == 0 || userVo.isEmpty()){
			// 이용자 정보 없으면 판매자 정보 데이터 확인
			List<SellerVO> sellerVo = sellerService.sellerLogin(member_id, member_pw);
			if(!sellerVo.isEmpty() || sellerVo.size() != 0){
				SellerVO seller = sellerVo.get(0);
				session.setAttribute("memberInfo", seller);	// 로그인 정보가 맞으면 세션에 해당 판매자회원의 로그인 정보를 저장.
				mav.setViewName("redirect:/truck/trucFoodTrucList.do");
			}
			else{	//판매자 회원 정보도 없으면
				mav.addObject("loginMsg","입력하신 정보가 올바르지 않습니다. 정확히 다시입력해주세요.");	//로그인 실패 메시지
				mav.setViewName("forward:/user/memberLogin.do");	//로그인 페이지로 이동해야함.
			}
		}
		
		return mav;
	}
	
	////////////////////////////////////////////////////////////
	// 로그아웃 처리 (이용자, 판매자 둘 다)
	@RequestMapping(value="/memberLogout.do")
	public String memberLogout(HttpSession session){
		session.setAttribute("memberInfo", null);
		return "redirect:/truck/trucFoodTrucList.do";
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	// ID 찾기
	@RequestMapping(value="/memberFindId.do")
	public String memberFindId(){
		return "/user/memberSearchId";
	}
	
	// ID 찾기 ACTION
	@RequestMapping(value="/memberFindIdAction.do")
	public ModelAndView memberFindIdAction(
			@RequestParam(value="memberName", required=false, defaultValue="null") String memberName, 
			@RequestParam(value="memberPhoneOrEmail", required=false, defaultValue="null") String memberPorE){
		ModelAndView mav = new ModelAndView();
		
		if(memberPorE.contains("@")){	//이메일이면
			mav = userService.selectFindIdByEmail(memberName, memberPorE);
			if(mav != null){
				return mav;
			}
			else{	// 이메일로 사용자 찾지못하면 판매자로 탐색
				mav = sellerService.selectFindIdByEmail(memberName, memberPorE);
				return mav;
			}
		}
		else{	//연락처로 찾으면
			mav = userService.selectFindIdByPhone(memberName, memberPorE);
			if(mav != null){	//연락처로 찾았으면
				return mav;
			}
			else{	//찾지못했으면 판매자에서 연락처로 찾음
				mav = sellerService.selectFindIdByPhone(memberName, memberPorE);
				return mav;
			}
		}
	}
	
	/////////////////////////////////////// By Young Min End /////////////////////////////////////////////////
	
	
	//===========================마이페이지 수정  By Hyoung Moo ==================================================
	
		//==마이페이지 클릭시===
		@RequestMapping(value="/memberMyPage.do", method = {RequestMethod.POST,RequestMethod.GET})
			//좋아요 카운팅
		public ModelAndView likeCountUser(ModelAndView mav,HttpSession session){
			Object obj = session.getAttribute("memberInfo");
			mav = new ModelAndView();
			 	//이용자로 판명 =>좋아요 gogo =>한줄평 카운팅 이동=>mypage 이동
			if(obj instanceof UserVO) {
				System.out.println("MemberController 이용자 좋아요 이동~");
				mav = userService.likeCountUser(mav,session);
				mav.setViewName("forward:/user/CountContentUser.do");
			}
				//판매자로 판명 =>좋아요 gogo =>한줄평 카운팅 이동=>mypage 이동
			else { 
				System.out.println("MemberController 판매자 좋아요 이동~");
				mav = foodtruckService.likeCountSeller(mav,session);
				mav.setViewName("forward:/user/CountContentUser.do");
			}
			
			return mav;
		}
		
			//====한줄평 카운팅==
		@RequestMapping(value="/CountContentUser.do", method = {RequestMethod.POST,RequestMethod.GET})
		// 한줄평 카운팅
		public ModelAndView CountContentUser(ModelAndView mav,HttpSession session, 
				@RequestParam(value="menuAddResult", required=false, defaultValue="") String menuAddResult){
			Object obj = session.getAttribute("memberInfo");
			mav = new ModelAndView();
			//이용자로 판명 : 한줄평 카운팅 gogo=>mypage 이동
			if(obj instanceof UserVO) { 
				mav = foodtruckService.CountContentUser(mav,session);
				mav.setViewName("user/memberMyPage");
			//판매자 판명 : 한줄평 카운팅 gogo=>mypage 이동
			} else { 
				mav = foodtruckService.CountContentSeller(mav,session);
				if(!menuAddResult.equals("")) mav.addObject("menuAddResult", menuAddResult);
				mav.setViewName("user/memberMyPage");
				
			}
			return mav;
		}
		
		//==사용자 좋아요 리스트 출력 
			@RequestMapping(value="/likeCountUserList.do", method=RequestMethod.GET)
			//이용자 좋아요 출력
			public ModelAndView likeCountUserList(ModelAndView mav,HttpSession session){
				mav = new ModelAndView();
				mav = foodtruckService.likeCountUserList(mav,session);
				mav.setViewName("user/ajax/likeCountUserList");
				return mav;
			}
		
		
		//한줄평 클릭시 리스트 불러오기
		@RequestMapping(value="/CountContentUserList.do", method=RequestMethod.GET)
		//이용자 한줄평 출력
		public ModelAndView CountContentUserList(ModelAndView mav,HttpSession session) {
			mav = foodtruckService.CountContentUserList(mav,session);
			mav.setViewName("user/ajax/myCommentlist");
			return mav;
		}
			
		
		//마이페이지 중 회원 수정페이지로 이동
		@RequestMapping(value="/MyInfoPage.do", method=RequestMethod.GET)
			public String myInfoPageJoin(){
				return "user/memberMyInfo";
			}
		
		
		//user 수정 업데이트 처리
			@RequestMapping(value="/userUpdate.do", method=RequestMethod.POST)
			public ModelAndView userUpdateJoin(@RequestParam Map<String, Object> map, HttpSession session){
				
				ModelAndView mav = new ModelAndView();
				mav = userService.updateInfoUser(map,mav,session);
				return mav;
			}
			
		//Seller 수정 업데이트 처리
			@RequestMapping(value="/sellerUpdate.do", method=RequestMethod.POST)
			public ModelAndView sellerUpdateJoin(@ModelAttribute SellerVO sellerVo, HttpSession session){
				ModelAndView mav = new ModelAndView();
				mav = sellerService.updateInfoSeller(sellerVo, mav,session);
				return mav;
			}
			
		// 내 트럭 목록 페이지로 이동
		@RequestMapping(value="/myTruckListPage.do")
		public ModelAndView myTruckListPage(HttpSession session, HttpServletRequest req){
			ModelAndView mav = new ModelAndView();
			mav = foodtruckService.selectMyTruckList(mav, session);
			if(req.getAttribute("successMsg") != null) mav.addObject("successMsg", "성공");
			else mav.addObject("successMsg", "실패");
			
			return mav;
		}
		
		// 내 트럭 삭제하기
		@RequestMapping(value="/deleteTruck.do")
		public String deleteTruck(@RequestParam(value="truckNum") int truckNum){
			foodtruckService.deleteTruck(truckNum);
			return "redirect:/user/myTruckListPage.do";
		}
		
	//=============================== MyPage Update End =============================================	
		
		
//========================일반사용자 회원정보 (중복은 memberIdCheck 같이씀) by hyungmoo=============================================

//사용자 회원관리
@RequestMapping(value="/userEntry.do", method=RequestMethod.POST)
public ModelAndView userJoinUs(@ModelAttribute UserVO uservo, ModelAndView mav, HttpSession session) {
	mav= userService.userJoinUs(uservo, mav, session);
	
	return mav;
}		

//========================일반사용자 회원정보 end=============================================





//=============비밀번호 찾기 9/3=======================
// pw 찾기
	@RequestMapping(value="memberFindPw.do")
	public String memberFindPw(){
		return "/user/memberSearchPw";
	}
	
	// pw 찾기 ACTION
	@RequestMapping(value="/memberFindPwAction.do")
	public ModelAndView memberFindPwAction(
			@RequestParam(value="memberId", required=false, defaultValue="null") String memberId, 
			@RequestParam(value="memberPhone", required=false, defaultValue="null") String memberPhone,
			@RequestParam(value="memberEmail", required=false, defaultValue="null") String memberEmail)
	{
		ModelAndView mav = new ModelAndView();

			mav = userService.selectFindPw(memberId, memberPhone,memberEmail);
			if(mav != null){
				return mav;
			}
			else{
				mav = sellerService.selectFindPw(memberId, memberPhone,memberEmail);
				return mav;
			}
		
		}
	

//=============비밀번호 재설정 9/3=======================

	@RequestMapping(value="memberResetPw.do")
	public String memberResetPw(){
		return "/user/memberResetPass";
	}
	@RequestMapping(value="memberResetPwAction.do")
	@ResponseBody
	public String memberResetPwAction(
			@RequestParam(value="memberId", required=false, defaultValue="null") String memberId, 
			@RequestParam(value="memberPw", required=false, defaultValue="null") String memberPw,
			@RequestParam(value="newMemberPw", required=false, defaultValue="null") String newMemberPw)
	{
			int result=0;
			int userCheckPw=0;
			int sellerCheckPw=0;
			
			userCheckPw = userService.selectCheckPw(memberId, memberPw);
			
			sellerCheckPw = sellerService.selectCheckPw(memberId, memberPw);
			
			if(userCheckPw==1){
				result = userService.UpdateNewPw(memberId,newMemberPw);
				return String.valueOf(result);
			}
			else if(sellerCheckPw==1) {
				result = sellerService.UpdateNewPw(memberId,newMemberPw);
				return String.valueOf(result);
			}
			return String.valueOf(result);
		}		
			
}














