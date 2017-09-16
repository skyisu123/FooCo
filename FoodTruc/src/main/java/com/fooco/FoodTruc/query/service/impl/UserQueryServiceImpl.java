package com.fooco.FoodTruc.query.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.dao.UserQueryDAO;
import com.fooco.FoodTruc.query.dao.impl.UserQueryDAOImpl;
import com.fooco.FoodTruc.query.service.UserQueryService;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

@Service("userQueryService")
public class UserQueryServiceImpl implements UserQueryService{
	
	@Autowired
	private UserQueryDAOImpl userQueryDao;
	
	@Override
	public List<UserQueryVO> showList(String memberId, int offset, String read) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("offset", offset);
		map.put("read", read);
		if(offset == 0) map.put("limit", 5);
		else map.put("limit", offset*2);
		
		List<UserQueryVO> list = userQueryDao.showList(map);
		return list;
	}

	@Override
	public int getTotalCount(String memberId,String read) {
		int totalRecordCount = userQueryDao.getTotalCount(memberId,read);
		return totalRecordCount;
	}
	
	@Override
	public void querySend(HttpServletRequest req) {
		String sellerId = req.getParameter("sellerId");
		String memberId = req.getParameter("memberId");
		String queryType = req.getParameter("queryType");
		String queryTitle = req.getParameter("queryTitle");
		String queryContent = req.getParameter("queryContent");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("memberId", memberId);
		map.put("queryType", queryType);
		map.put("queryTitle", queryTitle);
		map.put("queryContent", queryContent);
		System.out.println(sellerId);
		System.out.println(memberId);
		System.out.println(queryType);
		System.out.println(queryTitle);
		System.out.println(queryContent);
		userQueryDao.querySend(map);
	}
		
	@Override	
	public int getQueryCount(String sellerId,String read) {
		int totalRecordCount = userQueryDao.getQueryCount(sellerId,read);
		return totalRecordCount;
	}
}
















