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
	
	// ȸ�� ���̵� �ߺ��˻� (�̿���)
	@Override
	public int checkId(String memberId) {
		//int result = userDao.checkId(memberId);
		return userDao.checkId(memberId);
	}
	
	// ��� �α��� (�̿���)
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
	
	//===========================���������� ���� ==================================================
		//����� ȸ������ ����
		@Override
		public ModelAndView updateInfoUser(Map<String, Object> map, ModelAndView mav, HttpSession session) {

			int result = userDao.updateInfoUser(map);
			String id = (String)map.get("memberId");
			String pw = (String)map.get("memberPw");
			System.out.println("������ : " + id);
			System.out.println("������ : " + pw);
			//session.invalidate(); //session �ʱ�ȭ
			List<UserVO> list = userLogin(id,pw);//�ٽ� ������ session �ֱ�
			
			if(!list.isEmpty() || list.size() != 0 && result > 0){
				// �α��� ������ ��ġ�ϴ� �����Ͱ� ������
				UserVO user = list.get(0);
				session.setAttribute("memberInfo", user);	// �α��� ������ ������ ���ǿ� �ش� �Ϲ�ȸ���� �α��� ������ ����.
				mav.addObject("updateMsgS", "ȸ�������� �����Ǿ����ϴ�");
				mav.setViewName("redirect:/user/memberMyPage.do");
			}
			else if(list.size() == 0 || list.isEmpty() && result <= 0){
				UserVO user = list.get(0);
				session.setAttribute("memberInfo", user);	// �α��� ������ ������ ���ǿ� �ش� �Ǹ���ȸ���� �α��� ������ ����.
				mav.addObject("updateMsgS", "ȸ�������� �������� �ʾҽ��ϴ�");
				mav.setViewName("user/memberMyInfo");
				}
			return mav;
		}
		
		
		//���ƿ� ���� ī����
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

		
	//===========================���������� ����  ��==================================================
	
		
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
		else{// ã������
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
		else{// ã������
			mav.addObject("memberId", memberId);
			mav.setViewName("user/ajax/findId_resultPro");
		}
		
		return mav;
	}
	
		
	/////////////////////////////////////////////////////////////////////////////////////////////

	
	//=============================PW ã�� 9/3=============================	
		
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
			else{// ã������
				mav.addObject("memberPw", memberPw);
				mav.setViewName("user/ajax/findPw_resultPro");
			}
			
			return mav;
		}


		
		//===========================����� ȸ������ ����==================================================
		@Override
		public ModelAndView userJoinUs(UserVO uservo, ModelAndView mav, HttpSession session) {
			System.out.println("���̵�"+uservo.getmemberId());
			int result = userDao.userJoinUs(uservo);
			if(result==1) {
				mav.addObject("userjoinMsg", "���ԵǾ����ϴ�.");
				mav.setViewName("forward:/user/memberLogin.do"); // �������� 9-8
			} else {
				mav.addObject("userjoinMsg", "�Ͻ��� ������ ������ �ȵǾ����ϴ�.");
				mav.setViewName("forward:/truck/trucFoodTrucList.do");
			}
			
			return mav;
		}
		//===========================����� ȸ������ ��==================================================
		
		
		//=========��й�ȣ �缳�� 9/3====================
		@Override	
		public int selectCheckPw(String memberId, String memberPw) {
			System.out.println("����"+memberId);
			System.out.println("����"+memberPw);
			int result= userDao.selectCheckPw(memberId,memberPw);
			return result;
		}
		
		@Override
		public int UpdateNewPw(String memberId, String newMemberPw) {
			System.out.println("����"+memberId);
			System.out.println("����"+newMemberPw);
			int result = userDao.UpdateNewPw(memberId,newMemberPw);
			return result;
		}
		//=========��й�ȣ �缳�� ��====================
	
	
	
	
	
	
	
	
}














