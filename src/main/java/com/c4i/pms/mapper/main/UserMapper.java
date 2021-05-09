package com.c4i.pms.mapper.main;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.main.user.vo.projStatusVO;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;

@Mapper
public interface UserMapper {
	
	/**
	 * 인원(사용자) 현황
	 * @return
	 */
	List<UserVO> PMSUserList1(ServerPageCommand command);
	/**
	 * 프로젝트 상세정보 페이지의 사용자 정보 팝업창 
	 * @return
	 */
	List<UserVO> PMSUserList2();  
	List<UserVO> InputUserList(UserVO user);
	/**
	 * 인원 수 
	 * @return
	 */
	int PMSUserCount(ServerPageCommand command);
	/**
	 * 해당 사용자가 투입된 프로젝트 현황
	 * @param user
	 * @return
	 */
	projStatusVO inputUserStatusData(UserVO user);  
	/**
	 * 해당 사용자 상세정보
	 * @return
	 */
	UserVO PMSUserOne(UserVO user);
	UserVO PMSUserCount(UserVO user);
	int PMSUserOneCount(UserVO user);
	
	UserVO PMSInputUserOne(UserVO user);
	
	void insertUser(UserVO user);
	void updatePMSUser(UserVO user);
	void deletePMSUser(UserVO user);
	/**
	 * 메뉴URL 확인
	 * @param param
	 * @return
	 */
	Map<String, Object>  selectMenuCheck(Map<String, Object> param);

	
}
