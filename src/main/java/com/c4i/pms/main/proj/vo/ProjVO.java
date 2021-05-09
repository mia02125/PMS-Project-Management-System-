package com.c4i.pms.main.proj.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjVO {
	
	private List<Integer> projCodes;
	
	// 카멜 케이스 형식으로 컬럼명 정의 
	private int projCode = 0;
	private String projName = "";
	private String projType = "";
	private String projStatus = "";
	private String projLevel = "";
	private String projBudget = "";
	private String projModifyDate = "";
	private String projManagerComp = "";
	private String projManagerPost = "-";
	private String projManagerName = "-";
	private String projInDate = "";
	private String projOutDate = "";
	private int projInputCount = 0;
	private long projDate = 0;
	private long projLeftDate = 0;
	private String projProgress = "";
}
