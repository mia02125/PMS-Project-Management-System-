package com.c4i.pms.mapper.main;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.c4i.pms.main.proj.vo.ProjUserVO;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;

@Mapper
public interface ProjUserMapper {
	// 투입인원의 프로젝트 투입 목록
	List<ProjVO> inputUserProjList(UserVO user);
	// 전체 투입인원 현황
    List<UserVO> inputUserList(ServerPageCommand command);
    // 해당 담당자 현황
    UserVO oneManager(ProjVO proj);
    // 해당 담당자 현황 sub
    String[] oneManagerSub(ProjVO proj);
    //  클릭 시 투입인원(사용자) 데이터 가져오기
    UserVO clickInputUser(UserVO user);
    //  투입인원 추가 팝업에서 사용자 현황 show
    UserVO inputUserPopUpList();
    // 해당 프로젝트 투입인원 리스트
    UserVO oneInputUser();
    // 해당 투입인원 메타데이터(수정팝업)
    UserVO InputUserOne(ProjUserVO projUser);
    
    ProjUserVO InputProjUserOne(ProjUserVO projUser);
    
    void insertInputUser(ProjUserVO projUser);
    
    void updateInputUser(ProjUserVO projUser);
    
    void deleteInputUser(ProjUserVO projUser);
    
    int oneManagerCount(ProjUserVO projUser);
    
    int InputUserCheck(ProjUserVO projUserVO);
    // 투입인원 수
    int inputUserCount(ServerPageCommand command);
    
}