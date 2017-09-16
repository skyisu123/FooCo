package com.fooco.FoodTruc.query.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.dao.SellerQueryDAO;
import com.fooco.FoodTruc.query.dao.impl.SellerQueryDAOImpl;
import com.fooco.FoodTruc.query.service.SellerQueryService;
import com.fooco.FoodTruc.query.vo.ReplyQueryVO;
import com.fooco.FoodTruc.query.vo.SellerQueryVO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

@Service("sellerQueryService")
public class SellerQueryServiceImpl implements SellerQueryService{

	@Autowired
	private SellerQueryDAOImpl sellerQueryDao;
	
	@Override
	public void querySend(HttpServletRequest req) {
		String sellerId = req.getParameter("sellerId");
		String queryType = req.getParameter("queryType");
		String queryTitle = req.getParameter("queryTitle");
		String queryContent = req.getParameter("queryContent");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("queryType", queryType);
		map.put("queryTitle", queryTitle);
		map.put("queryContent", queryContent);
		
		sellerQueryDao.querySend(map);
	}
	@Override
	public List<SellerQueryVO> showList(String sellerId, int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("offset", offset);
		if(offset == 0) map.put("limit", 5);
		else map.put("limit", offset*2);
		List<SellerQueryVO> list = sellerQueryDao.showList(map);

		return list;
	}
	
	
	@Override
	public List<UserQueryVO> showReceiveList(String sellerId, int offset, String read) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("offset", offset);
		map.put("read", read);
		if(offset == 0) map.put("limit", 5);
		else map.put("limit", offset*2);
		List<UserQueryVO> list = sellerQueryDao.showReceiveList(map);
		return list;
	}
	
	@Override
	public List<ReplyQueryVO> showReplyQuery(String sellerId, int offset) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("offset", offset);
		if(offset == 0) map.put("limit", 5);
		else map.put("limit", offset*2);
		List<ReplyQueryVO> list = sellerQueryDao.showReplyQuery(map);
		return list;
	}
	
	@Override
	public int getReplyTotalCount(String sellerId) {
		int totalRecordCount = sellerQueryDao.getReplyTotalCount(sellerId);
		return totalRecordCount;
	}
	
	@Override
	public int getTotalCount(String sellerId) {
		int totalRecordCount = sellerQueryDao.getTotalCount(sellerId);
		return totalRecordCount;
	}
	
	@Override
	public int getReceviceTotalCount(String sellerId, String read) {
		int totalRecordCount = sellerQueryDao.getReceviceTotalCount(sellerId,read);
		return totalRecordCount;
	}
	
	 
	@Override
	public UserQueryVO showReceiveView(HttpServletRequest req) {
		String queryNum = req.getParameter("queryNum");
		UserQueryVO result = sellerQueryDao.showReceiveView(queryNum);
		return result;
	}
	
	@Override
	public SellerQueryVO showView(HttpServletRequest req) {
		String queryNum = req.getParameter("queryNum");
		SellerQueryVO result = sellerQueryDao.showView(queryNum);
		return result;
	}
	
	
	@Override
	public void deleteSellerQuery(HttpServletRequest req) {
		String queryNum = req.getParameter("queryNum");
		sellerQueryDao.deleteSellerQuery(queryNum);
	}
	
	@Override
	public void deleteUserQuery(HttpServletRequest req) {
		String queryNum = req.getParameter("queryNum");
		sellerQueryDao.deleteUserQuery(queryNum);
	}
	
	
	@Override
	public void answerAction(Model model) {
		Map<String, Object> paramMap = model.asMap();
		sellerQueryDao.answerAction(paramMap);
	}
	
	@Override
	public void editSellerAction(Model model) {
		Map<String, Object> paramMap = model.asMap();
		sellerQueryDao.editSellerAction(paramMap);
	}
	
	@Override
	public void editUserAction(Model model)	{
		Map<String, Object> paramMap = model.asMap();
		sellerQueryDao.editUserAction(paramMap);
	}
	
	@Override
	public ReplyQueryVO replyList(String queryCode) {
		ReplyQueryVO list = sellerQueryDao.replyList(queryCode);
		return list;
	}
}

















