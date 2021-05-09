package com.c4i.pms.main.proj.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ProjUserVO {
	
	private List<Integer> userCodes;
	
	private int projCode;
	private int userCode;
	private String userComp;
	private String userDivision;
	private String userInDate;
	private String userOutDate;
	private String userModifyDate;
	private String userJob;
	
	public void setUserModifyDate(String userModifyDate) {
		String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String reCurrentDate = currentDate.replace("-",".");
		userModifyDate = reCurrentDate;
		this.userModifyDate = userModifyDate;
	}
}
