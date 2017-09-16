package com.fooco.FoodTruc.likes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.fooco.FoodTruc.likes.service.impl.LikesServiceImpl;

@Controller
public class LikesController {
	
	@Autowired
	private LikesServiceImpl likesService;
	
	
}
