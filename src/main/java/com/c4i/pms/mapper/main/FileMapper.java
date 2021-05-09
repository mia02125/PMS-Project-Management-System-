package com.c4i.pms.mapper.main;

import org.apache.ibatis.annotations.Mapper;

import com.c4i.pms.main.file.vo.FileVO;
import com.c4i.pms.main.user.vo.UserVO;

@Mapper
public interface FileMapper {
	
	
	/**
	 * 파일 - 신규
	 * @param param
	 * @return
	 */
	int insertFile(FileVO file);
	
	/**
	 * 파일 메타데이터 가져오기
	 * @param param
	 * @return
	 */
	FileVO selectImgOne(UserVO user);
	/**
	 * 이력서 존재 여부 
	 * @param file
	 * @return
	 */
	FileVO findCareerOne(FileVO file);
	/**
	 * 해당 사용자의 이력서 존재유무 
	 * @param user
	 * @return
	 */
	FileVO selectCareerOne(UserVO user);
	/**
	 * 파일 존재 여부 
	 * @param user
	 * @return
	 */
	int imgFileCheck(UserVO user);
	int careerFileCheck(UserVO user);
	/**
	 * 파일 수정 
	 * @param file
	 */
	void updateFile(FileVO file);
	
}
