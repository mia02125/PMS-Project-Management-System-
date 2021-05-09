package com.c4i.pms.main.career.vo;

import java.util.List;

import lombok.Data;

@Data
public class CareerCompVO {
	private List<String> resCareerCode; // PK
	private String resCode= ""; // FK 
	private List<String> resCareerComp;
	private List<String> resCareerStDate;
	private List<String> resCareerFiDate;
	private List<String> resCareerPost;
	private List<String> resCareerContent;
	
	// View에 데이터 출력 
	private String careerCode = "";
	private String careerComp= "";
	private String careerStDate = "";
	private String careerFiDate = "";
	private String careerPost = "";
	private String careerContent = "";
	
}