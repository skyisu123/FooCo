package com.fooco.FoodTruc.query.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.vo.ReplyQueryVO;
import com.fooco.FoodTruc.query.vo.SellerQueryVO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

public interface SellerQueryDAO {
	public void querySend(Map<String, Object> map);
	
	public int getTotalCount(String sellerId);
	
	//페이징처리를 위한 메소드
	public String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page);
	
	public List<SellerQueryVO> showList(Map<String, Object> paramMap);
	
	public List<ReplyQueryVO> showReplyQuery(Map<String, Object> paramMap);
	
	public int getReplyTotalCount(String sellerId);
	
	public int getReceviceTotalCount(String sellerId, String read);
	
	public List<UserQueryVO> showReceiveList(Map<String, Object> paramMap);
	
	public UserQueryVO showReceiveView(String queryNum);
	
	public SellerQueryVO showView(String queryNum);

	
	public void deleteSellerQuery(String queryNum);
	
	public void deleteUserQuery(String queryNum);
	
	public void answerAction(Map<String, Object> paramMap);
	
	public void editSellerAction(Map<String, Object> paramMap);
	
	public void editUserAction(Map<String, Object> paramMap);
	
	public ReplyQueryVO replyList(String queryCode);
	
	
}
