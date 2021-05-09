package com.c4i.pms.main.career.vo;

import java.util.List;

import lombok.Data;

@Data
public class CareerCertVO {
	private String resCode = ""; // FK 
	private int userCode = 0;
	private List<String> resCertCode; // PK
	private List<String> resCertName;
	private List<String> resCertDate;
	private List<String> resCertAgency;
	
	private String certCode = "";
	private String certName = "";
	private String certDate = "";
	private String certAgency = "";
	
}
