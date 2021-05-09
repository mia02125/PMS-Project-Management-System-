package com.c4i.pms.main.career.vo;
import java.util.List;

import lombok.Data;

@Data
public class CareerProjVO {
	// 기본 정보 
	private String detProjCode = ""; // PK
	private String resCode = ""; // FK
	private int userCode = 0; // 식별 관계 
	private String projCodes = "";
	private String projName = "";
	private String detProjOs = "";
	private String detProjLang = "";
	private String detProjDb =  "";
	private String detProjTool = "";
	private String detProjNet = "";
	private String detProjOther = "";
	// 입력 VO  
	private List<String> projCode;
	private List<String> detProjCodes;
	private List<String> projOs;
	private List<String> projLang;
	private List<String> projDb;
	private List<String> projTool;
	private List<String> projNet;
	private List<String> projOther;
	 
	// 부수정보 
	private String userInDate = ""; // 참여기간( 시작 ) 
	private String userOutDate = ""; // 참여기간( 종료 ) 
	private String userComp = ""; // 프로젝트 투입된 기업 
	private String userJob = ""; // 투입된 프로젝트 직책
	
}
