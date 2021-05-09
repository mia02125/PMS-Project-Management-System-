package com.c4i.pms.main.user.vo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.c4i.pms.main.career.vo.CareerVO;
import com.c4i.pms.main.proj.vo.ProjUserVO;

import lombok.Data;


@Data
public class UserVO {
	
	private List<Integer> USERCODES;
	
	private int userCode = 0;
	private String userName = "";
	private String userPost = ""; 
	private String userBuseo = "";
	private String userLevel = "";
	private String userComp = "";
	private String userTel = "";
	private String userEmail = "";
	private String userDelete = "";
	private String userJob = "";
	private String userDivision = "";
	private String userModifyDate = "";
	private String userInDate = "";
	private String userOutDate = "";
	// 이미지 
	private MultipartFile uploadImg;
	private MultipartFile uploadCareer;
	
	// 이력서 
	private CareerVO career;
	
	
	
	
	
	
	@Autowired
	private ProjUserVO projUser;
	
	

}
