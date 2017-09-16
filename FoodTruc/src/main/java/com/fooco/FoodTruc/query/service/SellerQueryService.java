package com.fooco.FoodTruc.query.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.vo.ReplyQueryVO;
import com.fooco.FoodTruc.query.vo.SellerQueryVO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

public interface SellerQueryService {

	public void querySend(HttpServletRequest req);

	public List<SellerQueryVO> showList(String sellerId, int offset);

	public List<UserQueryVO> showReceiveList(String sellerId, int offset, String read);

	public UserQueryVO showReceiveView(HttpServletRequest req);
	
	public SellerQueryVO showView(HttpServletRequest req);
	
	public void deleteSellerQuery(HttpServletRequest req);
	public void deleteUserQuery(HttpServletRequest req);
	
	public void answerAction(Model model);
	
	public void editSellerAction(Model model);
	public void editUserAction(Model model);
	
	public List<ReplyQueryVO> showReplyQuery(String sellerId, int offset);
	
	public ReplyQueryVO replyList(String queryCode);
	
	public int getReplyTotalCount(String sellerId);
	
	public int getReceviceTotalCount(String sellerId, String read);
	
	public int getTotalCount(String sellerId);

}
