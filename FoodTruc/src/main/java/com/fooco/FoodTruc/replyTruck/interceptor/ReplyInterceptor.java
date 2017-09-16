package com.fooco.FoodTruc.replyTruck.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;

public class ReplyInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		Object memberObj = session.getAttribute("memberInfo");
		
		if(memberObj instanceof UserVO) {
			return true;
		}
		else if(memberObj instanceof SellerVO){
			res.sendRedirect("/user/memberLogin.do");	//�α��� �������� �̵���Ŵ
			return false;
		}
		else{	//���ǿ� UserVO�� SellerVO ������ ������
			res.sendRedirect("/user/memberLogin.do");	//�α��� �������� �̵���Ŵ
			return false;
		}
	}
}
