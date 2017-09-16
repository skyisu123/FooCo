package com.fooco.FoodTruc.query.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.vo.UserQueryVO;

public interface UserQueryService {

	public List<UserQueryVO> showList(String memberId, int offset, String read);

	public void querySend(HttpServletRequest req);
	
	public int getTotalCount(String memberId,String read);
	
	public int getQueryCount(String sellerId,String read);
}
