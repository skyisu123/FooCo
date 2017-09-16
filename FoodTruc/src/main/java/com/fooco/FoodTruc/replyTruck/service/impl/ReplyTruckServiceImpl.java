package com.fooco.FoodTruc.replyTruck.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooco.FoodTruc.replyTruck.dao.impl.ReplyTruckDaoImpl;
import com.fooco.FoodTruc.replyTruck.service.ReplyTruckService;
import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

@Service("replyTruckService")
public class ReplyTruckServiceImpl implements ReplyTruckService{
	
	@Autowired
	private ReplyTruckDaoImpl replytruckDao;
	
	//////////////////////////////////// By Young Min ///////////////////////////
	@Override
	public List<ReplyTruckVO> selectDetailReplyTruck(int truckNum) {
		List<ReplyTruckVO> list = replytruckDao.selectDetailReplyTruck(truckNum);
		if(!list.isEmpty()){
			return list;
		}
		else return null;
	}
	
	@Override
	public int selectReplyCount(int truckNum) {
		return replytruckDao.selectReplyCount(truckNum);
	}
	
	// ����¡ ������ ����Ʈ ��������
	@Override
	public List<ReplyTruckVO> selectReplyTruck(int truckNum, int start) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckNum", truckNum);
		map.put("start", start);
		if(start == 1) map.put("end", 5);
		else map.put("end", start+4);
		return replytruckDao.selectReplyTruck(map);
	}
	
	// ������ ����ϱ�
	@Override
	public int insertReview(String replyContent, String score, int truckNum, String memberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(replyContent + " " + score + " " + truckNum + " " + memberId);
		map.put("replyContent", replyContent);
		map.put("score", score);
		map.put("truckNum", truckNum);
		map.put("memberId", memberId);
		
		return replytruckDao.insertReview(map);
	}
	
	///////////////////////////////////////////////////////////////////////////////
}
