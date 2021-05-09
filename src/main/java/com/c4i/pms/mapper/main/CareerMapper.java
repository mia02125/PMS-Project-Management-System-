package com.c4i.pms.mapper.main;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.c4i.pms.main.career.vo.CareerCertVO;
import com.c4i.pms.main.career.vo.CareerCompVO;
import com.c4i.pms.main.career.vo.CareerProjVO;
import com.c4i.pms.main.career.vo.CareerVO;
import com.c4i.pms.main.user.vo.UserVO;

@Mapper
public interface CareerMapper {
	
	// 이력서 정보 # 1 
	UserVO selectResInfo(UserVO user);
    // 이력서 정보 # 2     
	List<CareerVO> selectResCareer(UserVO user);
    // 이력서 정보 # 3 
	List<CareerVO> selectResCert(UserVO user);
    // 이력서 정보 # 4 
	List<CareerVO> selectResProj(UserVO user);

	List<CareerVO> selectCareerDetail(UserVO user);
    
    CareerVO selectResumeOne(int userCode);
    
    int careerCount(UserVO user);
    // 신상정보 
    void insertResInfo(CareerVO career);
    // 자격증 
    void insertResCert(CareerCertVO cert);
    // 프로젝트 경력 
    void insertResProj(CareerProjVO proj);
    // 근무 경력 
    void insertResComp(CareerCompVO comp);

    void updateResProj(CareerProjVO proj);
    
    void updateRes(CareerVO career);
    
    void updateComp(CareerCompVO comp);
    
    void updateCert(CareerCertVO cert);
    
    void deleteRes(CareerVO career);
    
    void deleteProj(CareerVO career);
    void deleteProjRow(CareerProjVO career);
    
    void deleteComp(CareerVO career);
    void deleteCompRow(CareerCompVO career);
    
    void deleteCert(CareerVO career);
    void deleteCertRow(CareerCertVO career);	
    
    
    int CheckCertCode(String target);
    int CheckcompCode(String target);
    int CheckprojCode(String target);
}
