package common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;

public class CommonSessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// 컨트롤러가 호출되기 전에 세션을 검사함
		HttpSession session = req.getSession();
		Object memberObj = session.getAttribute("memberInfo");
		
		if(memberObj instanceof UserVO) {
			return true;
			
		}
		else if(memberObj instanceof SellerVO){
			return true;
		}
		else{	//세션에 UserVO나 SellerVO 정보가 없으면
			res.sendRedirect("/FoodTruc/user/memberLogin.do");	//로그인 페이지로 이동시킴
			return false;
		}
	}

}
















