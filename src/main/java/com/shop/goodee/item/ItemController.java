package com.shop.goodee.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item/*")
public class ItemController {

	@GetMapping("add")
	public void setAdd() {
		
	}
	
	@GetMapping("detail")
	public void getItems() {
		
	}
}
