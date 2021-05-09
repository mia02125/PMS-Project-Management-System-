package com.c4i.pms.main.file.vo;

import lombok.Data;

@Data
public class FileVO {
	private int fileCode = 0;
	private int userCode = 0;
	private String fileName = "";
	private String fileDivision = "";
	private long fileSize = 0;
	private String filePath = "";
	private String fileDate = "";
}
