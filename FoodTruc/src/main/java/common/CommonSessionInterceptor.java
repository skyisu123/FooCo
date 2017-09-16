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
		// ��Ʈ�ѷ��� ȣ��Ǳ� ���� ������ �˻���
		HttpSession session = req.getSession();
		Object memberObj = session.getAttribute("memberInfo");
		
		if(memberObj instanceof UserVO) {
			return true;
			
		}
		else if(memberObj instanceof SellerVO){
			return true;
		}
		else{	//���ǿ� UserVO�� SellerVO ������ ������
			res.sendRedirect("/FoodTruc/user/memberLogin.do");	//�α��� �������� �̵���Ŵ
			return false;
		}
	}

}
















