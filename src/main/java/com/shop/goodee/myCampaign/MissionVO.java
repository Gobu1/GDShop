package com.shop.goodee.myCampaign;

import java.sql.Date;
import java.util.List;

import com.shop.goodee.item.ItemFileVO;
import com.shop.goodee.item.ItemVO;

import lombok.Data;

@Data
public class MissionVO {

	private Integer missionNum;
	private Integer itemNum;
	private String id;
	private Date applyDate;
	private Date finish;
	private Integer status;
	private Integer myCam;
	
	private ItemVO itemVO;
	private List<ItemFileVO> itemFileVOs;
}