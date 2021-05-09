package com.c4i.pms.main.career;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.c4i.pms.main.career.vo.CareerCertVO;
import com.c4i.pms.main.career.vo.CareerCompVO;
import com.c4i.pms.main.career.vo.CareerProjVO;
import com.c4i.pms.main.career.vo.CareerVO;
import com.c4i.pms.main.user.UserService;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.mapper.main.CareerMapper;


@Service("CareerService")
public class CareerService {
	
	private CareerMapper careerMapper;
	
	@Resource(name = "UserService")
	private UserService userService;
	
	/**
     * 이력서 존재 여부 확인 
     * @param user
     * @return
     */
    public Boolean checkCareer(UserVO user) {
    	int count = careerMapper.careerCount(user);
    	if(count > 0) {
    		return true; 
    	} else {
    		return false;    		
    	}
    }
	/**
	 * [프로젝트 상세정보]이력서 상세정보 가져오기
	 * 2021-12-30 리팩토링 : resultmap collection 활용
	 * @param mav
	 * @param model
	 * @param user
	 * @return
	 */
	public ModelAndView selectCareerDetail(ModelAndView mav, Model model, UserVO user) {
		// 해당 사용자 이력서 존재 유무
		int count = careerMapper.careerCount(user);
 		if(count > 0) {  
 			UserVO userVOs = careerMapper.selectResInfo(user);
 			mav.addObject("userInfo", userVOs);
 			List<CareerVO> compVOs = careerMapper.selectResCareer(user);
 			mav.addObject("compVOs", compVOs);
 			List<CareerVO> projVOs = careerMapper.selectResProj(user);
 	 		mav.addObject("projVOs", projVOs);
 	 		List<CareerVO> certVOs = careerMapper.selectResCert(user);
 	 		mav.addObject("certVOs", certVOs);
 		} else {  
 			UserVO userInfo = userService.UserOne(user);
 			mav.addObject("userInfo", userInfo); 	
 			List<CareerVO> projVOs = careerMapper.selectCareerDetail(user);
 			mav.addObject("projVOs", projVOs);
 		}
 		return mav;
 	}
    /**
     * [사용자 상세정보]이력서 사용자 정보 불러오기
     * 2021-12-30 리팩토링 : resultmap collection 활용 
     * @param user
     * @param projUser
     * @return
     */
 	public Model selectUserDetail(UserVO user, Model model) {
 		int count = careerMapper.careerCount(user);
 		// 이력서 존재 시
 		if(count > 0) {  
 			UserVO userVOs = careerMapper.selectResInfo(user);
 			model.addAttribute("userInfo", userVOs);
 			List<CareerVO> compVOs = careerMapper.selectResCareer(user);
 	 		model.addAttribute("compVOs", compVOs);
 	 		List<CareerVO> projVOs = careerMapper.selectResProj(user);
 	 		model.addAttribute("projVOs", projVOs);
 	 		List<CareerVO> certVOs = careerMapper.selectResCert(user);
 	 		model.addAttribute("certVOs", certVOs);
 		} else {  
 			// 이력서 미 존재 false
 			UserVO userInfo = userService.UserOne(user); 
 			model.addAttribute("userInfo", userInfo); 	
 			List<CareerVO> projVOs = careerMapper.selectCareerDetail(user);
 	 		model.addAttribute("projVOs", projVOs);
 		}
 		return model;
 	}
    /**
     * 특정 사용자의 이력서 정보 
     * @param userCode
     * @return
     */
 	public CareerVO selectResume(int userCode) {
 		return careerMapper.selectResumeOne(userCode);
 	}
 	/**
 	 * 이력서, 신상정보, 자격증, 근무경력, 프로젝트 경력 insert And Update
 	 * @param mav
 	 * @param ResCheck
 	 * @param career
 	 * @param cert
 	 * @param user
 	 * @param comp
 	 * @param proj
 	 * @return
 	 */
 	public ModelAndView insertAndUpdate(ModelAndView mav, Boolean ResCheck, CareerVO career, CareerCertVO cert, 
 								UserVO user, CareerCompVO comp, CareerProjVO proj) {
 		// 이력서 미등록 시 
    	if(ResCheck == false) {
    		// 사용자 신상정보 insert - 성공 시 success 반환
   			String str = this.insertInfo(career);  
    		if(str == "success") {
    			// 해당 이력서 존재 여부
    			CareerVO careerInfo = this.selectResume(career.getUserCode());  
    			if(careerInfo.getResCode() != null) {
    				cert.setResCode(careerInfo.getResCode());
    				comp.setResCode(careerInfo.getResCode());
    				proj.setResCode(careerInfo.getResCode());
    					if(cert.getResCertName().size() != 0 || cert.getResCertDate().size() != 0 || cert.getResCertAgency().size() != 0) {
    						this.insertCert(cert);
    						}
    					if(comp.getResCareerComp().size() != 0 || comp.getResCareerContent().size() != 0 || comp.getResCareerStDate().size() != 0 || comp.getResCareerFiDate().size() != 0) {
    						this.insertComp(comp);
    						}
    					this.insertProj(proj);
    				
    				mav.setViewName("redirect:/main/user/detail?userCode=" + career.getUserCode());
    			} 
    		}
   		// 이력서 등록 시
    	} else if(ResCheck == true) {
    		String str = this.updateInfo(career); // UPDATE 성공 시  "success" RETURN
    		if(str == "success") {
    			// 특정 사용자 
    			CareerVO careerInfo = this.selectResume(career.getUserCode());
    			cert.setResCode(careerInfo.getResCode());
    			comp.setResCode(careerInfo.getResCode());
    			proj.setResCode(careerInfo.getResCode());
     			//  자격증 insert 및 update
    			if(cert.getResCertName().size() != 0 || cert.getResCertDate().size() != 0 || cert.getResCertAgency().size() != 0) {  
    				// 해당 이력서에 자격증이  존재하는지 확인
    				int certCount = this.checkCert(cert.getResCode()); // 자격증 존재유무 확인	
					if(certCount > 0) {
						this.updateCert(cert);
					} else {
						this.insertCert(cert);
					}
    			}
     		    // 근무 경력 insert 및 update 
    			if(comp.getResCareerComp().size() != 0 || comp.getResCareerContent().size() != 0 || comp.getResCareerStDate().size() != 0 || comp.getResCareerFiDate().size() != 0) {
    				// 해당 이력서에 근무경력이  존재하는지 확인
    				int compCount = this.checkComp(comp.getResCode());
    				if(compCount > 0) {
    					this.updateComp(comp);
    				} else {
    					this.insertComp(comp);
    				}
    			}
    			// 프로젝트 insert 및 update 구현 완료
    			int projCount = 0;
    			if(proj.getDetProjCodes() != null) {
    				for(int i = 0; i < proj.getDetProjCodes().size(); i++) {
    					String projCode = proj.getDetProjCodes().get(i);
    					projCount = this.checkProj(projCode);
    					if(projCount > 0) {
    						this.updateProj(proj);
    					} else {
    						this.insertProj(proj);
    					}
    				}	
    			} else {
    				projCount = this.checkProj(proj.getDetProjCode());
    				if(projCount > 0) {
    					this.updateProj(proj);
    				} else {
    					this.insertProj(proj);
    				}	
    			}
    		}
    	}
    	mav.setViewName("redirect:/main/user/detail?userCode=" + career.getUserCode());
    	return mav;
    }
 	/**
 	 * 신상  정보 insert & update 
 	 * @return
 	 */
 	public String insertInfo(CareerVO career) {
 		careerMapper.insertResInfo(career);
 		return "success";
 	}
 	/**
 	 * 신상  정보 
 	 * @param career
 	 * @return
 	 */
  	public String updateInfo(CareerVO career) {
  		careerMapper.updateRes(career);
  		return "success";
  	}
	// 자격증 insert_구현완료  
 	public void insertCert(CareerCertVO cert) {
 		// 
		for(int i = 0; i < cert.getResCertName().size(); i++) {
			try {
				cert.setCertName(cert.getResCertName().get(i)); 
				cert.setCertDate(cert.getResCertDate().get(i)); 
				cert.setCertCode(cert.getResCertAgency().get(i)); 
			} catch (Exception e) {
			} finally {
				if(cert.getCertName() != "" || cert.getCertDate() != "" || cert.getCertAgency() != "") {
					careerMapper.insertResCert(cert);
				}
			}
		}
 	}
 	/**
 	 * 이력서 내 자격증 수정 
 	 * @param cert
 	 */
 	public void updateCert(CareerCertVO cert) {
 		if(cert.getResCertName().size() > cert.getResCertCode().size()) { // 새로 추가된 값이 기존 값보다 적을 때   
 			for(int i = cert.getResCertCode().size(); i < cert.getResCertName().size(); i++) { // 새로운 값 추가 
 				try {
 					cert.setCertName(cert.getResCertName().get(i));
 					cert.setCertDate(cert.getResCertDate().get(i));
 					cert.setCertCode(cert.getResCertAgency().get(i));
 				} catch (Exception e) {
 				} finally {
 					if(cert.getCertName() != "" || cert.getCertDate() != "" || cert.getCertAgency() != "") {
 						careerMapper.insertResCert(cert);
 					}
 				}
 			}
 			for(int i = 0; i < cert.getResCertCode().size(); i++) { // 기존값 없데이트 
 				try {
 					cert.setCertCode(cert.getResCertCode().get(i));
 					cert.setCertName(cert.getResCertName().get(i));
 					cert.setCertDate(cert.getResCertDate().get(i));
 					cert.setCertCode(cert.getResCertAgency().get(i));	
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateCert(cert);
 				}
 			}
 		} else if(cert.getResCertName().size() < cert.getResCertCode().size()) { // 새로 추가된 값이 기존 값보다  많을 때 
 			for(int i = cert.getResCertName().size(); i < (cert.getResCertName().size() + cert.getResCertCode().size()); i++) {  // 새로운 값 추가
 				try {
 					cert.setCertName(cert.getResCertName().get(i));  
 					cert.setCertDate(cert.getResCertDate().get(i));
 					cert.setCertCode(cert.getResCertAgency().get(i));	
 				} catch (Exception e) {
 				} finally {
 					if(cert.getCertName() != "" || cert.getCertDate() != "" || cert.getCertAgency() != "") {
 						careerMapper.insertResCert(cert);
 					}
 				}
 			}
 			for(int i = 0; i < cert.getResCertCode().size(); i++) { // 기존 값 업데이트 
 				try {
 					cert.setCertCode(cert.getResCertCode().get(i));
 					cert.setCertName(cert.getResCertName().get(i));
 					cert.setCertDate(cert.getResCertDate().get(i));
 					cert.setCertCode(cert.getResCertAgency().get(i));	
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateCert(cert);
 				}
 			}
 		} else if(cert.getResCertName().size() == cert.getResCertCode().size()) { // 기존 값 업데이트 시
 			for(int i = 0; i < cert.getResCertCode().size(); i++) {  // 새로운 값 추가 
 				try {
 					cert.setCertCode(cert.getResCertCode().get(i));
 					cert.setCertName(cert.getResCertName().get(i));
 					cert.setCertDate(cert.getResCertDate().get(i));
 					cert.setCertCode(cert.getResCertAgency().get(i));	
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateCert(cert);
 				}
 			}
 		}
 	}
  	/**
  	 * 이력서 내  근무경력 추가 
  	 * @param comp
  	 */
 	public void insertComp(CareerCompVO comp) {
 			for(int i = 0; i < comp.getResCareerComp().size(); i++) {
 				try {
 					comp.setCareerComp(comp.getResCareerComp().get(i));
 					comp.setCareerContent(comp.getResCareerContent().get(i));
 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i));	
 				} catch (Exception e) {
 				} finally {
 					if(comp.getCareerComp() != "" || comp.getCareerContent() != "" || comp.getCareerStDate() != "" || comp.getCareerFiDate() != "") {
 						careerMapper.insertResComp(comp);
 					}
 				}
 			}
 		}
 	/**
 	 * 이력서 내 근무경력 수정  
 	 * @param comp
 	 */
 	public void updateComp(CareerCompVO comp) {
 		if(comp.getResCareerComp().size() > comp.getResCareerCode().size()) { // 새로 추가된 값이 기존 값보다 적을 때   
 			for(int i = comp.getResCareerCode().size(); i < comp.getResCareerComp().size(); i++) { 
 				try {
 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
 					comp.setCareerComp(comp.getResCareerComp().get(i));
 					comp.setCareerContent(comp.getResCareerContent().get(i));
 					comp.setCareerPost(comp.getResCareerPost().get(i));
 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i));	
 				} catch (Exception e) {
 				} finally {
 					if(comp.getCareerComp() != "" || comp.getCareerContent() != "" || comp.getCareerStDate() != "" || comp.getCareerFiDate() != "") {
 						careerMapper.insertResComp(comp);
 					}
 				}
 			}
 			for(int i = 0; i < comp.getResCareerCode().size(); i++) {
 				try {
 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
 					comp.setCareerCode(comp.getResCareerCode().get(i));
 					comp.setCareerComp(comp.getResCareerComp().get(i));
 					comp.setCareerContent(comp.getResCareerContent().get(i));
 					comp.setCareerPost(comp.getResCareerPost().get(i));
 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i));
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateComp(comp);
 				}
 			}
 		} else if(comp.getResCareerComp().size() < comp.getResCareerCode().size()) { // 새로 추가된 값이 기존 값보다  많을 때 
 			for(int i = comp.getResCareerComp().size(); i < (comp.getResCareerComp().size() + comp.getResCareerCode().size()); i++) {
 				try {
 						// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
	 					comp.setCareerComp(comp.getResCareerComp().get(i));
	 					comp.setCareerContent(comp.getResCareerContent().get(i));
	 					comp.setCareerPost(comp.getResCareerPost().get(i));
	 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
	 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i)); 				
 				} catch (Exception e) {
 				} finally {
 					if(comp.getCareerComp() != "" || comp.getCareerContent() != "" || comp.getCareerStDate() != "" || comp.getCareerFiDate() != "") {
 						careerMapper.insertResComp(comp);
 					}
 				}
 			}
 			for(int i = 0; i < comp.getResCareerCode().size(); i++) {
 				try {
 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
 					comp.setCareerCode(comp.getResCareerCode().get(i));
 					comp.setCareerComp(comp.getResCareerComp().get(i));
 					comp.setCareerContent(comp.getResCareerContent().get(i));
 					comp.setCareerPost(comp.getResCareerPost().get(i));
 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i));
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateComp(comp);
 				}
 			}
 		} else if(comp.getResCareerComp().size() == comp.getResCareerCode().size()) { // 기존 값 업데이트 시
 			for(int i = 0; i < comp.getResCareerCode().size(); i++) {
 				try {
 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
 					comp.setCareerCode(comp.getResCareerCode().get(i));
 					comp.setCareerComp(comp.getResCareerComp().get(i));
 					comp.setCareerContent(comp.getResCareerContent().get(i));
 					comp.setCareerPost(comp.getResCareerPost().get(i));
 					comp.setCareerStDate(comp.getResCareerStDate().get(i));
 					comp.setCareerFiDate(comp.getResCareerFiDate().get(i));
 				} catch (Exception e) {
 				} finally {
 					careerMapper.updateComp(comp);
 				}
 			}
 		}
 	}
 	/**
 	 * 이력서 내  프로젝트 경력 추가 
 	 * @param proj
 	 */
 	public void insertProj(CareerProjVO proj) {
 		if(proj.getProjCode() != null) {
	 		if(proj.getProjCode().size() > 1) {
	 			for(int i = 0; i < proj.getProjCode().size(); i++) {
	 				try {
	 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
	 					proj.setProjCodes(proj.getProjCode().get(i));
	 					proj.setDetProjOs(proj.getProjOs().get(i));
	 					proj.setDetProjLang(proj.getProjLang().get(i));
	 					proj.setDetProjDb(proj.getProjDb().get(i));
	 					proj.setDetProjTool(proj.getProjTool().get(i));
	 					proj.setDetProjNet(proj.getProjNet().get(i));
	 					proj.setDetProjOther(proj.getProjOther().get(i));
	 				} catch (ArrayIndexOutOfBoundsException e) {
	 				} finally {
	 					careerMapper.insertResProj(proj);
	 				}
	 			} 
	 		}
 		}
 	}
 	/**
 	 * 이력서 내 프로젝트 경력 수정
 	 * @param proj
 	 */
  	public void updateProj(CareerProjVO proj) {
  		if(proj.getProjCode().size() > 1) {
 			for(int i = 0; i < proj.getProjCode().size(); i++) {
 				try {
 					// 리스트형태의 데이터를 각각 해당 컬럼에 setter 이용
 					proj.setDetProjCode(proj.getDetProjCodes().get(i));
 					proj.setProjCodes(proj.getProjCode().get(i));
 					proj.setDetProjOs(proj.getProjOs().get(i));
 					proj.setDetProjLang(proj.getProjLang().get(i));
 					proj.setDetProjDb(proj.getProjDb().get(i));
 					proj.setDetProjTool(proj.getProjTool().get(i));
 					proj.setDetProjNet(proj.getProjNet().get(i));
 					proj.setDetProjOther(proj.getProjOther().get(i));
 				} catch (ArrayIndexOutOfBoundsException e) {
 				} finally {
 					careerMapper.updateResProj(proj);
 				}
 			} 
 		}
  	}
  	/**
  	 * 이력서 내 근무경력 및 자격증 삭제 
  	 */
  	public void deleteOne(CareerCertVO cert, CareerCompVO comp) {
  		int compCode = 0;
  		int certCode = 0;
  		if(comp.getResCareerCode() != null) {
  			String code = comp.getResCareerCode().get(0);
  			compCode = Integer.valueOf(code);
  			comp.setCareerCode(code);
  		} else if(cert.getResCertCode() != null) {
  			String code = cert.getResCertCode().get(0);
  			certCode = Integer.valueOf(code);
  			cert.setCertCode(code);
  		}
  		if(certCode > 0) {
  			careerMapper.deleteCertRow(cert);
  		} else if(compCode > 0) {
  			careerMapper.deleteCompRow(comp);
  		}
  	}
  	/**
  	 * 이력서 삭제 
  	 * @param career
  	 */
  	public void delete(CareerVO career) {
  		careerMapper.deleteRes(career);
  		careerMapper.deleteProj(career);
  		careerMapper.deleteCert(career);
  		careerMapper.deleteComp(career);
  	}
  	/**
  	 * 자격증 존재 여부 확인  
  	 * @param target
  	 * @return
  	 */
  	public int checkCert(String target) {
  		return careerMapper.CheckCertCode(target);
  	}
  	/**
  	 * 프로젝트 경력 존재 여부 확인  
  	 * @param target
  	 * @return
  	 */
  	public int checkProj(String target) {
  		return careerMapper.CheckprojCode(target);
  	}
  	/**
  	 * 근무경력 존재 여부 확인  
  	 * @param target
  	 * @return
  	 */
  	public int checkComp(String target) {
  		return careerMapper.CheckcompCode(target);
  	}
  	
	@Autowired
	public void setCareerMapper(CareerMapper careerMapper) {
		this.careerMapper = careerMapper;
	}
}
