package com.fooco.FoodTruc.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.member.dao.impl.UserDaoImpl;
import com.fooco.FoodTruc.member.service.UserService;
import com.fooco.FoodTruc.member.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDaoImpl userDao; 
	
	@Override
	public int insert(UserVO userVO) {
		int result = userDao.insertData(userVO);
		
		return result;
	}
	
	// 회원 아이디 중복검사 (이용자)
	@Override
	public int checkId(String memberId) {
		//int result = userDao.checkId(memberId);
		return userDao.checkId(memberId);
	}
	
	// 멤버 로그인 (이용자)
	@Override
	public List<UserVO> userLogin(String member_id, String member_pw) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("member_id", member_id);
		parameterMap.put("member_pw", member_pw);
		List<UserVO> userVo = userDao.userLogin(parameterMap);
		/*for(UserVO user : userVo){
			System.out.println(user.getmemberId());
			System.out.println(user.getmemberPw());
			System.out.println(user.getmemberName());
			System.out.println(user.getmemberEmail());
			System.out.println(user.getmemberPhone());
			System.out.println(user.getmemberAddr());
			System.out.println(user.getmemberDate());
		}*/
		return userVo;
	}
	
	//===========================마이페이지 수정 ==================================================
		//사용자 회원정보 수정
		@Override
		public ModelAndView updateInfoUser(Map<String, Object> map, ModelAndView mav, HttpSession session) {

			int result = userDao.updateInfoUser(map);
			String id = (String)map.get("memberId");
			String pw = (String)map.get("memberPw");
			System.out.println("받은값 : " + id);
			System.out.println("받은값 : " + pw);
			//session.invalidate(); //session 초기화
			List<UserVO> list = userLogin(id,pw);//다시 수정된 session 넣기
			
			if(!list.isEmpty() || list.size() != 0 && result > 0){
				// 로그인 정보가 일치하는 데이터가 있으면
				UserVO user = list.get(0);
				session.setAttribute("memberInfo", user);	// 로그인 정보가 맞으면 세션에 해당 일반회원의 로그인 정보를 저장.
				mav.addObject("updateMsgS", "회원정보가 수정되었습니다");
				mav.setViewName("redirect:/user/memberMyPage.do");
			}
			else if(list.size() == 0 || list.isEmpty() && result <= 0){
				UserVO user = list.get(0);
				session.setAttribute("memberInfo", user);	// 로그인 정보가 맞으면 세션에 해당 판매자회원의 로그인 정보를 저장.
				mav.addObject("updateMsgS", "회원정보가 수정되지 않았습니다");
				mav.setViewName("user/memberMyInfo");
				}
			return mav;
		}
		
		
		//좋아요 숫자 카운팅
		@Override
		public ModelAndView likeCountUser( ModelAndView mav, HttpSession session) {
			UserVO idx =(UserVO)session.getAttribute("memberInfo");
			String id = idx.getmemberId();
			int likeCountUser = userDao.likeCountUser(id);
			if(likeCountUser>0) {
				mav.addObject("likeCountUser", likeCountUser);
			}
			else {
				mav.addObject("likeCountUser", "0");
			}
			return mav;
		}

		
	//===========================마이페이지 수정  끝==================================================
	
		
	///////////////////////////////// Find Id By Young Min //////////////////////////////////////
		
	@Override
	public ModelAndView selectFindIdByPhone(String memberName, String memberPhone) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberPhone", memberPhone);
		
		String memberId = userDao.selectFindIdByPhone(map);
		
		if(memberId == null){
			mav = null;
		}
		else{// 찾았으면
			mav.addObject("memberId", memberId);
			mav.setViewName("user/ajax/findId_resultPro");
		}
		
		return mav;
	}

	@Override
	public ModelAndView selectFindIdByEmail(String memberName, String memberEmail) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberName", memberName);
		map.put("memberEmail", memberEmail);
		
		String memberId = userDao.selectFindIdByEmail(map);
		
		if(memberId == null){
			mav = null;
		}
		else{// 찾았으면
			mav.addObject("memberId", memberId);
			mav.setViewName("user/ajax/findId_resultPro");
		}
		
		return mav;
	}
	
		
	/////////////////////////////////////////////////////////////////////////////////////////////

	
	//=============================PW 찾기 9/3=============================	
		
		@Override
		public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail) {
			ModelAndView mav = new ModelAndView();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", memberId);
			map.put("memberPhone", memberPhone);
			map.put("memberEmail", memberEmail);
			
			String memberPw = userDao.selectFindPw(map);

			if(memberPw == null){
				mav = null;
			}
			else{// 찾았으면
				mav.addObject("memberPw", memberPw);
				mav.setViewName("user/ajax/findPw_resultPro");
			}
			
			return mav;
		}


		
		//===========================사용자 회원가입 시작==================================================
		@Override
		public ModelAndView userJoinUs(UserVO uservo, ModelAndView mav, HttpSession session) {
			System.out.println("아이디"+uservo.getmemberId());
			int result = userDao.userJoinUs(uservo);
			if(result==1) {
				mav.addObject("userjoinMsg", "가입되었습니다.");
				mav.setViewName("forward:/user/memberLogin.do"); // 수정사항 9-8
			} else {
				mav.addObject("userjoinMsg", "일시적 오류로 가입이 안되었습니다.");
				mav.setViewName("forward:/truck/trucFoodTrucList.do");
			}
			
			return mav;
		}
		//===========================사용자 회원가입 끝==================================================
		
		
		//=========비밀번호 재설정 9/3====================
		@Override	
		public int selectCheckPw(String memberId, String memberPw) {
			System.out.println("서비스"+memberId);
			System.out.println("서비스"+memberPw);
			int result= userDao.selectCheckPw(memberId,memberPw);
			return result;
		}
		
		@Override
		public int UpdateNewPw(String memberId, String newMemberPw) {
			System.out.println("서비스"+memberId);
			System.out.println("서비스"+newMemberPw);
			int result = userDao.UpdateNewPw(memberId,newMemberPw);
			return result;
		}
		//=========비밀번호 재설정 끝====================
	
	
	
	
	
	
	
	
}














