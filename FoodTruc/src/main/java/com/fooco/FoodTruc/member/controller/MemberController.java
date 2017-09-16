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
User (Ǫ��Ʈ���� �̿��ϴ� �̿���) ��Ʈ�ѷ�
�α��� or ȸ������ or ���� or ���� ���� �𵨷� �̵��ϰų� ���������� �̵�
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
	
	//Ǫ��Ʈ�� ��� ȸ������ �������̵�
	@RequestMapping(value="/sellerJoin.do", method=RequestMethod.GET)
	public String sellerJoin(){
		
		return "user/sellerJoin";
	}
	
	//Ǫ��Ʈ�� �Ϲ� �̿��� ȸ������ �������̵�
	@RequestMapping(value="/userJoin.do")
	public String userJoin(){
		
		return "user/userJoin";
	}
	
	// �̿��� , �Ǹ��� ȸ������ �������� �̵�
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
	
	
	// �Ǹ��� ȸ������ ó��
	@RequestMapping(value="/sellerJoin.do", method=RequestMethod.POST)
	public ModelAndView sellerJoinAction(@ModelAttribute SellerVO sellerVo){
		
		ModelAndView mav = new ModelAndView();
		
		mav = sellerService.insertSeller(sellerVo, mav);

		return mav;
	}
	//�α��� ������ �̵�(�̿���, �Ǹ��� �� ��)
	@RequestMapping(value="/memberLogin.do")
	public void memberLogin(){}	//��ȯŸ�� void�� �ϸ� ���ΰ����� �����̳ʰ� ã�ư�.
	
	//�α��� ó�� (�̿���)
	@RequestMapping(value="/userLogin.do", method=RequestMethod.POST)
	public ModelAndView userLogin(HttpSession session, @RequestParam String member_id, @RequestParam String member_pw){
		//��ġ�ϴ� ���� ������ addObject�� �α��� ���� �޽��� ����
		ModelAndView mav = new ModelAndView();
		//���� �̿��� �α��� ���� �˻�
		List<UserVO> userVo = userService.userLogin(member_id, member_pw);
		
		if(!userVo.isEmpty() || userVo.size() != 0){
			
			if(userVo.get(0).getmemberId().equals("admin")){	// ������ ���̵��
				session.setAttribute("admin", userVo.get(0));
				mav.setViewName("redirect:http://localhost:8080/FooCoAdmin/material-dashboard-html-v1.1.1/admin/index.jsp");
				return mav;
			}
			// �α��� ������ ��ġ�ϴ� �����Ͱ� ������
			UserVO user = userVo.get(0);
			session.setAttribute("memberInfo", user);	// �α��� ������ ������ ���ǿ� �ش� �Ϲ�ȸ���� �α��� ������ ����.
			mav.setViewName("redirect:/truck/trucFoodTrucList.do");
		}
		else if(userVo.size() == 0 || userVo.isEmpty()){
			// �̿��� ���� ������ �Ǹ��� ���� ������ Ȯ��
			List<SellerVO> sellerVo = sellerService.sellerLogin(member_id, member_pw);
			if(!sellerVo.isEmpty() || sellerVo.size() != 0){
				SellerVO seller = sellerVo.get(0);
				session.setAttribute("memberInfo", seller);	// �α��� ������ ������ ���ǿ� �ش� �Ǹ���ȸ���� �α��� ������ ����.
				mav.setViewName("redirect:/truck/trucFoodTrucList.do");
			}
			else{	//�Ǹ��� ȸ�� ������ ������
				mav.addObject("loginMsg","�Է��Ͻ� ������ �ùٸ��� �ʽ��ϴ�. ��Ȯ�� �ٽ��Է����ּ���.");	//�α��� ���� �޽���
				mav.setViewName("forward:/user/memberLogin.do");	//�α��� �������� �̵��ؾ���.
			}
		}
		
		return mav;
	}
	
	////////////////////////////////////////////////////////////
	// �α׾ƿ� ó�� (�̿���, �Ǹ��� �� ��)
	@RequestMapping(value="/memberLogout.do")
	public String memberLogout(HttpSession session){
		session.setAttribute("memberInfo", null);
		return "redirect:/truck/trucFoodTrucList.do";
	}
	/////////////////////////////////////////////////////////////////////////////////////
	
	// ID ã��
	@RequestMapping(value="/memberFindId.do")
	public String memberFindId(){
		return "/user/memberSearchId";
	}
	
	// ID ã�� ACTION
	@RequestMapping(value="/memberFindIdAction.do")
	public ModelAndView memberFindIdAction(
			@RequestParam(value="memberName", required=false, defaultValue="null") String memberName, 
			@RequestParam(value="memberPhoneOrEmail", required=false, defaultValue="null") String memberPorE){
		ModelAndView mav = new ModelAndView();
		
		if(memberPorE.contains("@")){	//�̸����̸�
			mav = userService.selectFindIdByEmail(memberName, memberPorE);
			if(mav != null){
				return mav;
			}
			else{	// �̸��Ϸ� ����� ã�����ϸ� �Ǹ��ڷ� Ž��
				mav = sellerService.selectFindIdByEmail(memberName, memberPorE);
				return mav;
			}
		}
		else{	//����ó�� ã����
			mav = userService.selectFindIdByPhone(memberName, memberPorE);
			if(mav != null){	//����ó�� ã������
				return mav;
			}
			else{	//ã���������� �Ǹ��ڿ��� ����ó�� ã��
				mav = sellerService.selectFindIdByPhone(memberName, memberPorE);
				return mav;
			}
		}
	}
	
	/////////////////////////////////////// By Young Min End /////////////////////////////////////////////////
	
	
	//===========================���������� ����  By Hyoung Moo ==================================================
	
		//==���������� Ŭ����===
		@RequestMapping(value="/memberMyPage.do", method = {RequestMethod.POST,RequestMethod.GET})
			//���ƿ� ī����
		public ModelAndView likeCountUser(ModelAndView mav,HttpSession session){
			Object obj = session.getAttribute("memberInfo");
			mav = new ModelAndView();
			 	//�̿��ڷ� �Ǹ� =>���ƿ� gogo =>������ ī���� �̵�=>mypage �̵�
			if(obj instanceof UserVO) {
				System.out.println("MemberController �̿��� ���ƿ� �̵�~");
				mav = userService.likeCountUser(mav,session);
				mav.setViewName("forward:/user/CountContentUser.do");
			}
				//�Ǹ��ڷ� �Ǹ� =>���ƿ� gogo =>������ ī���� �̵�=>mypage �̵�
			else { 
				System.out.println("MemberController �Ǹ��� ���ƿ� �̵�~");
				mav = foodtruckService.likeCountSeller(mav,session);
				mav.setViewName("forward:/user/CountContentUser.do");
			}
			
			return mav;
		}
		
			//====������ ī����==
		@RequestMapping(value="/CountContentUser.do", method = {RequestMethod.POST,RequestMethod.GET})
		// ������ ī����
		public ModelAndView CountContentUser(ModelAndView mav,HttpSession session, 
				@RequestParam(value="menuAddResult", required=false, defaultValue="") String menuAddResult){
			Object obj = session.getAttribute("memberInfo");
			mav = new ModelAndView();
			//�̿��ڷ� �Ǹ� : ������ ī���� gogo=>mypage �̵�
			if(obj instanceof UserVO) { 
				mav = foodtruckService.CountContentUser(mav,session);
				mav.setViewName("user/memberMyPage");
			//�Ǹ��� �Ǹ� : ������ ī���� gogo=>mypage �̵�
			} else { 
				mav = foodtruckService.CountContentSeller(mav,session);
				if(!menuAddResult.equals("")) mav.addObject("menuAddResult", menuAddResult);
				mav.setViewName("user/memberMyPage");
				
			}
			return mav;
		}
		
		//==����� ���ƿ� ����Ʈ ��� 
			@RequestMapping(value="/likeCountUserList.do", method=RequestMethod.GET)
			//�̿��� ���ƿ� ���
			public ModelAndView likeCountUserList(ModelAndView mav,HttpSession session){
				mav = new ModelAndView();
				mav = foodtruckService.likeCountUserList(mav,session);
				mav.setViewName("user/ajax/likeCountUserList");
				return mav;
			}
		
		
		//������ Ŭ���� ����Ʈ �ҷ�����
		@RequestMapping(value="/CountContentUserList.do", method=RequestMethod.GET)
		//�̿��� ������ ���
		public ModelAndView CountContentUserList(ModelAndView mav,HttpSession session) {
			mav = foodtruckService.CountContentUserList(mav,session);
			mav.setViewName("user/ajax/myCommentlist");
			return mav;
		}
			
		
		//���������� �� ȸ�� ������������ �̵�
		@RequestMapping(value="/MyInfoPage.do", method=RequestMethod.GET)
			public String myInfoPageJoin(){
				return "user/memberMyInfo";
			}
		
		
		//user ���� ������Ʈ ó��
			@RequestMapping(value="/userUpdate.do", method=RequestMethod.POST)
			public ModelAndView userUpdateJoin(@RequestParam Map<String, Object> map, HttpSession session){
				
				ModelAndView mav = new ModelAndView();
				mav = userService.updateInfoUser(map,mav,session);
				return mav;
			}
			
		//Seller ���� ������Ʈ ó��
			@RequestMapping(value="/sellerUpdate.do", method=RequestMethod.POST)
			public ModelAndView sellerUpdateJoin(@ModelAttribute SellerVO sellerVo, HttpSession session){
				ModelAndView mav = new ModelAndView();
				mav = sellerService.updateInfoSeller(sellerVo, mav,session);
				return mav;
			}
			
		// �� Ʈ�� ��� �������� �̵�
		@RequestMapping(value="/myTruckListPage.do")
		public ModelAndView myTruckListPage(HttpSession session, HttpServletRequest req){
			ModelAndView mav = new ModelAndView();
			mav = foodtruckService.selectMyTruckList(mav, session);
			if(req.getAttribute("successMsg") != null) mav.addObject("successMsg", "����");
			else mav.addObject("successMsg", "����");
			
			return mav;
		}
		
		// �� Ʈ�� �����ϱ�
		@RequestMapping(value="/deleteTruck.do")
		public String deleteTruck(@RequestParam(value="truckNum") int truckNum){
			foodtruckService.deleteTruck(truckNum);
			return "redirect:/user/myTruckListPage.do";
		}
		
	//=============================== MyPage Update End =============================================	
		
		
//========================�Ϲݻ���� ȸ������ (�ߺ��� memberIdCheck ���̾�) by hyungmoo=============================================

//����� ȸ������
@RequestMapping(value="/userEntry.do", method=RequestMethod.POST)
public ModelAndView userJoinUs(@ModelAttribute UserVO uservo, ModelAndView mav, HttpSession session) {
	mav= userService.userJoinUs(uservo, mav, session);
	
	return mav;
}		

//========================�Ϲݻ���� ȸ������ end=============================================





//=============��й�ȣ ã�� 9/3=======================
// pw ã��
	@RequestMapping(value="memberFindPw.do")
	public String memberFindPw(){
		return "/user/memberSearchPw";
	}
	
	// pw ã�� ACTION
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
	

//=============��й�ȣ �缳�� 9/3=======================

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














