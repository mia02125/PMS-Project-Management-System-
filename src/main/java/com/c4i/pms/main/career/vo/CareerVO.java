                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               package com.c4i.pms.main.career.vo;

import lombok.Data;
/**
 * 2020-12-29 리팩토링 : CareerVO 내 certVOs, compVOs, projVOs 선언 
 * @author com4in
 *
 */
@Data
public class CareerVO {
	private String resCode = ""; // PK
	private int userCode = 0;
	private String resSsn1 = "";
	private String resSsn2 = "";
	private String resCareerDays = "";
	private String resAddr = "";
	private String resEduName = "";
	private String resEduStDate = "";
	private String resEduFiDate = "";
	private String resEduMajor = "";

	private CareerCertVO certVOs;
	private CareerCompVO compVOs;
	private CareerProjVO projVOs;
	
}
