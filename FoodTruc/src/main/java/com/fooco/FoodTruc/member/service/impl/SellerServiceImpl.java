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
	
	// �Ǹ��� ȸ������
	@Override
	public ModelAndView insertSeller(SellerVO sellerVo, ModelAndView mav) {
		int result = sellerDao.insertSeller(sellerVo);
		if(result > 0){
			mav.addObject("isSellerJoin", "�Ǹ��� ȸ������ ����");
			mav.setViewName("forward:/user/memberLogin.do");
		}
		else{
			mav.addObject("isSellerJoin", "�Ǹ��� ȸ������ ����");
			mav.setViewName("/user/MemberJoin.do");
		}
		
		return mav;
	}

	//�Ǹ��� �α��� ó��
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
	
	//==========���������� ���� ����======================	
		//�Ǹ��� ȸ������ ���� 
		@Override
		public ModelAndView updateInfoSeller(SellerVO sellerVo,  ModelAndView mav, HttpSession session) {
			int result = sellerDao.updateInfoSeller(sellerVo);
			String id = sellerVo.getSellerId();
			String pw = sellerVo.getSellerPw();
			System.out.println("������ : " + id);
			System.out.println("������ : " + pw);
			//session.invalidate(); //session �ʱ�ȭ
			List<SellerVO> list = sellerLogin(id,pw);//�ٽ� ������ session �ֱ�
			
			if(!list.isEmpty() || list.size() != 0 && result > 0){
				// �α��� ������ ��ġ�ϴ� �����Ͱ� ������
				SellerVO seller = list.get(0);
				session.setAttribute("memberInfo", seller);	// �α��� ������ ������ ���ǿ� �ش� �Ϲ�ȸ���� �α��� ������ ����.
				mav.addObject("updateMsgS", "ȸ�������� �����Ǿ����ϴ�");
				mav.setViewName("redirect:/user/memberMyPage.do");
			}
			else if(list.size() == 0 || list.isEmpty() && result <= 0){
				SellerVO seller = list.get(0);
				session.setAttribute("memberInfo", seller);	// �α��� ������ ������ ���ǿ� �ش� �Ǹ���ȸ���� �α��� ������ ����.
				mav.addObject("updateMsgS", "ȸ�������� �������� �ʾҽ��ϴ�");
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
		// �Ǹ��ڱ��� ��ȸ������ ã�Ұ� ��ã�Ұ� ������ �б� �������� ����.
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


	
	
		///////////////////////////////// ��й�ȣ ã�� 9/3 //////////////////////////////////////
		
		@Override
		public ModelAndView selectFindPw(String memberId, String memberPhone, String memberEmail) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("memberPhone", memberPhone);
		map.put("memberEmail", memberEmail);
		
		String memberPw = sellerDao.selectFindPw(map);
		System.out.println(memberPw);
		// �Ǹ��ڱ��� ��ȸ������ ã�Ұ� ��ã�Ұ� ������ �б� �������� ����.
		mav.addObject("memberPw", memberPw);
		mav.setViewName("user/ajax/findPw_resultPro");
		
		return mav;
		}

		/////////////////////////////////////////////////////////////////////////////////////////////
		
		//=========��й�ȣ �缳�� 9/3====================
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

		//=========��й�ȣ �缳�� 9/3 ��====================


}
