package com.c4i.pms.main.proj;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.c4i.pms.main.career.CareerService;
import com.c4i.pms.main.proj.vo.CountVO;
import com.c4i.pms.main.proj.vo.ProjUserVO;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.UserService;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.web.method.serverpage.ServerPage;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;


@Controller
@RequestMapping("/main")
public class ProjController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjController.class);
	
	@Resource(name = "ProjService")
	private ProjService projService;

	@Resource(name = "UserService")
	private UserService userService;
	
	@Resource(name = "CareerService")
	private CareerService careerService;
	/**
	 * 프로젝트 현황
	 * @param command
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/proj")
	public String execution(ProjVO proj, ServerPageCommand command, Model model) {
		// 20201229_프로젝트 검색데이터 
		model.addAttribute("searchProjData", proj);
		// 20201020_프로젝트 현황
		command.setProj(proj);
		ServerPage serverPage = projService.projList(command);
		model.addAttribute("serverPage", serverPage);
		model.addAttribute("serverPageSub", serverPage.getProjList());
		// 20201003_총 예산
		ProjVO totalBudget = projService.totalBudget(command);
		model.addAttribute("totalBudget", totalBudget.getProjBudget());
		// 20201003_진행별 프로젝트 갯수
		CountVO detailProjCount = projService.detailProjCount(command);
		model.addAttribute("detailProjCount", detailProjCount);
		// 20201005_프로젝트 현황 
		command.setLength(11); // default
		command.setPaginateButtonlength(5);
		LOGGER.debug("[model]:" + model);
		return "f:proj/proj";
	}
	
	
	/**
	 * 해당 프로젝트 상세정보 
	 * @param command
	 * @param projCode
	 * @param proj
	 * @return
	 */
	@RequestMapping(value = "/proj/detail")
	public ModelAndView executionDetail(ServerPageCommand command,
										@RequestParam(name = "projCode") int projCode, // 파라미터값을 URL에 뿌리는게 요즘 트렌드
										ProjVO proj) {
		ModelAndView mav = new ModelAndView();
		proj.setProjCode(projCode);
		// 20201004_프로젝트 상세보기
		ProjVO projInfo = projService.oneProj(proj); 
		mav.addObject("projInfo", projInfo);
		// 20201004_담당자 현황
		UserVO managerInfo = projService.ManagerInfo(proj);
		mav.addObject("manager", managerInfo);
		// 20201004_사용자 현황(팝업창)
		List<UserVO> userList = userService.userList2();
		mav.addObject("userList", userList);
		// 20201006_투입인원 수 
		command.setProj(proj);
		int count = projService.inputUserCount(command);
		mav.addObject("count", count);
		proj.setProjCode(projCode);
		ServerPage serverPage = projService.selectListExecutionDetailPage(command, proj);
		mav.addObject("serverPage", serverPage);
		mav.addObject("serverPageSub", serverPage.getUserList());
		mav.setViewName("f:proj/proj_detail");
		return mav;
	}

	/*
	 * csrf : 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위(수정, 삭제, 등록 등)를 특정 웹사이트에 요청하게 하는 공격
	 * POST 방식은 csrf Token과 같이 전달(form형태는 자동으로 csrf 토큰 키가 설정 )
	 */
	/**
	 * 프로젝트 등록(insert)
	 * 2020-01-04 리팩토링 : ConverMapToObject(Map -> VO) 활용X
	 * @param param
	 * @param model
	 * @param proj
	 * @return
	 */
	@PostMapping(value = "/proj/insertProj")
	public String insertProj(ProjVO proj) {
		projService.insertAndUpdate(proj);
		return "f:proj/proj";
	}
	/**
	 * 프로젝트 수정(update)
	 * 2020-01-04 리팩토링 : return String  -> return ModelAndView 
	 * @param proj
	 * @return
	 */
	@PostMapping(value = "/proj/updateProj")
	public ModelAndView updateProj(ProjVO proj) {
		ModelAndView mav = new ModelAndView("jsonView");
		String alert = projService.insertAndUpdate(proj);
		mav.addObject("alert", alert);
		return mav;
	}
	/**
	 * 프로젝트 삭제(delete) - 원천데이터 삭제
	 * @param projCode
	 * @param proj
	 * @return
	 */
	@PostMapping(value = "/proj/deleteProj")
	public ModelAndView deleteProj(@RequestParam(value = "projCode[]") List<Integer> projCode) {
		ModelAndView mav = new ModelAndView("jsonView");
		ProjVO proj = new ProjVO(); 
		proj.setProjCodes(projCode);
		String alert = projService.delete(proj);
		mav.addObject("alert", alert);
		return mav;
	}
	/**
	 * [투입인원 수정 팝업창]클릭 시 투입인원 데이터 show
	 * @param userData
	 * @return
	 */
	@PostMapping(value = "/proj/bringInputData")
	@ResponseBody
	public UserVO bringData(UserVO user, Model model) {
		UserVO userInfo = projService.bringData(user);
		model.addAttribute("bringUserData", userInfo);
		return userInfo;
	}
	/**
	 * [투입인원 추가 팝업창]투입인원 수정팝업 데이터 가져오기 
	 * @param projUser
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/proj/bringInputUserData")
	@ResponseBody
	public UserVO bringUserData(ProjUserVO projUser, Model model) {
		UserVO userInfo = projService.InputUserOne(projUser);
		model.addAttribute("bringUserData", userInfo);
		return userInfo;
	}
	/**
	 * 투입인원 추가(insert)
	 * @param projUser
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/proj/insertInputUser")
	@ResponseBody
	public ProjUserVO inserInputUser(ProjUserVO projUser, HttpServletResponse response) throws IOException {
		return projService.insertInputUser(projUser);
	}
	/**
	 * 투입인원 수정 (update)
	 * @param projUser
	 * @param proj
	 * @return
	 */ 	
	@PostMapping(value = "/proj/updateInputUser")
	@ResponseBody
    public int updateInputUser(ProjUserVO projUser, ProjVO proj) {
	    return projService.updateInputUser(projUser, proj); // 담당자가 0명이면 수정 
	}
	/**
	 * 투입인원 방출 (delele)
	 * @param projCode
	 * @param userCode
	 * @param projUser
	 * @return
	 */
	@PostMapping(value = "/proj/deleteInputUser")
	public ModelAndView deleteInputUser(@RequestParam(value = "projCode") int projCode, 
								@RequestParam(value = "userCode[]") List<Integer> userCode) {
		ModelAndView mav = new ModelAndView("jsonView");
		ProjUserVO projUser = new ProjUserVO();
		projUser.setProjCode(projCode);
		projUser.setUserCodes(userCode);
		String alert = projService.deleteInputUser(projUser);
		mav.addObject("alert", alert);
		return mav;
	}
	/**
	 * [투입인원 추가 팝업창]투입인원 팝업창에서 사용자 클릭 시 해당 사용자 데이터 가져오기  
	 * @param params
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/proj/findInputUser")
    @ResponseBody
    public List<UserVO> findInputUser(UserVO user, Model model) {
    	List<UserVO> inputUserList = userService.InputuserList(user);
    	model.addAttribute("inputUserList", inputUserList);
    	return inputUserList;
    }
	/**
	 * 이력서 데이터 가져오기
	 * 2020-01-04 리팩토링 : ConverMapToObject(Map -> VO) 활용X
	 * @param user
	 * @param model
	 * @param userCode
	 * @return
	 */
	@PostMapping(value = "/proj/selectCareerDetail")
	@ResponseBody
	public ModelAndView selectCareerDetail(UserVO user, Model model, @RequestParam("checkUserCode") String userCode) {
		int checkUserCode = user.getUserCode();
        ModelAndView mav = new ModelAndView("jsonView");
        // 이미 열람했는지 파악 ( 미열람 시 DB데이터 가져옴  ) 
        if(!Integer.toString(checkUserCode).equals(userCode)) {
        	mav = careerService.selectCareerDetail(mav, model, user);
        }
		return mav;
	}
	
}