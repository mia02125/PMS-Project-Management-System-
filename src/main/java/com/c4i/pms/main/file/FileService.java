package com.c4i.pms.main.file;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.c4i.pms.main.file.vo.FileVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.mapper.main.FileMapper;


@Service("FileService")
@Transactional
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);
    
    private FileMapper fileMapper;
    
	/**
	 * 예산 - 신규
	 * @param param
	 * @return
	 */
	public int insertFile(FileVO file) {
		return fileMapper.insertFile(file);
	}
	
	/**
	 * 파일 - 해당 사용자의 파일  메타데이터  가져오기 
	 * @param param
	 * @return
	 */
	public FileVO selectImgOne(UserVO user) {
		FileVO file = fileMapper.selectImgOne(user);
		if(file != null) {
			String fileName = file.getFileName();
			String[] spFileNM = fileName.split("_");
			file.setFileName(spFileNM[spFileNM.length-1]);	
		} 
		return file;
	}
    // 이미지 존재 여부 체크
	public int imgFileCheck(UserVO user) {
    	return fileMapper.imgFileCheck(user);
	}	
	public void updateFile(FileVO file) { 
		fileMapper.updateFile(file);
	}
	public FileVO downloadFile(FileVO file) {
		String[] fileNM = file.getFileName().split("-");
		file.setFileName(fileNM[1]);
		return fileMapper.findCareerOne(file);
	}
	
	//파일 업로드
	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath) throws Exception {
		UUID uid = UUID.randomUUID();

		String newFileName = uid + "_" + fileName;
		String imgPath = uploadPath + ymdPath;

		File target = new File(imgPath, newFileName);
		FileCopyUtils.copy(fileData, target);
		String thumbFileName = "s_" + newFileName;
		File image = new File(imgPath + "/" + newFileName); //해당  pathname에 대한 파일 객체 생성 
		File thumbnail = new File(imgPath + "/" + "s" + "/" + thumbFileName);

		if (image.exists()) {

			try {
				thumbnail.getParentFile().mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}    
		}
		return newFileName;
	}

	// 디텍토리 날짜 설정
	public static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = "/" + cal.get(Calendar.YEAR);
		String monthPath = yearPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
	return datePath;
	}
	//디렉토리 생성
	private static void makeDir(String uploadPath, String... paths) {
	    if (new File(paths[paths.length - 1]).exists()) {
	        return;
	    }
	    for (String path : paths) {
	        File dirPath = new File(uploadPath + path);
	        if (!dirPath.exists()) {
	            dirPath.mkdir();
	        }
	    }
	}

	@Autowired
	public void setFileMapper(FileMapper mapper) {
		this.fileMapper = mapper;
	}

}
