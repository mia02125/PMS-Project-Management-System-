package com.c4i.pms.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.c4i.pms.main.proj.vo.CountVO;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;

@Mapper
public interface ProjMapper {

	List<ProjVO> projList(ServerPageCommand command);

	int ProjCount(ServerPageCommand command); 
	// 프로젝트 상태별 현황
    CountVO detailProjCount(ServerPageCommand command);
    // 총 에산 
    ProjVO totalBudget(ServerPageCommand command);
    // 해당 프로젝트 상세정보 
    ProjVO oneProj(ProjVO proj);

    int insertProj(ProjVO proj);
    
    int updateProj(ProjVO proj);
    
    int deleteProj(ProjVO proj);
    
    
    
    


}
