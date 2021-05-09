package com.c4i.pms.main.user;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.c4i.pms.comm.util.ConvertMapToObject;
import com.c4i.pms.main.career.CareerService;
import com.c4i.pms.main.career.vo.CareerCertVO;
import com.c4i.pms.main.career.vo.CareerCompVO;
import com.c4i.pms.main.career.vo.CareerProjVO;
import com.c4i.pms.main.career.vo.CareerVO;
import com.c4i.pms.main.file.FileService;
import com.c4i.pms.main.file.vo.FileVO;
import com.c4i.pms.main.proj.ProjController;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.web.method.serverpage.ServerPage;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;


@Controller
@RequestMapping(value = "/main")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjController.class);
    
    @Resource(name = "UserService")
    private UserService userService;
    
    @Resource(name = "CareerService")
    private CareerService careerService; 
    
    @Resource(name = "FileService")
    private FileService fileService;
    /**
     * 유저 리스트
     * @param model
     * @param params
     * @param command
     * @param user
     * @return
     */
    @RequestMapping(value = "/user")
    public String getUserList(Model model, @RequestParam HashMap<String, Object> params,
    						ServerPageCommand command, UserVO user) {
        UserVO userList = (UserVO) ConvertMapToObject.convertMapToObject(params, user);
        command.setUser(userList);
        command.setLength(11); // default
		command.setPaginateButtonlength(5);
        ServerPage serverPage = userService.userList1(command);
        model.addAttribute("searchUserData", userList);
        model.addAttribute("serverPage", serverPage);
        model.addAttribute("serverPageSub", serverPage.getUserList());
        return "f:user/user";
    }
    /**
     * 유저 상세정보
     * @param userCode
     * @param user
     * @param file
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/detail")
    public String getUserDetail(@RequestParam(name = "userCode") int userCode,
                                		UserVO user,FileVO file, Model model) {
    	user.setUserCode(userCode);
        // 사용자 메타데이터 
        UserVO userInfo = userService.UserOne(user);
        model.addAttribute("detailUsInfo", userInfo);
     	// 20201006_투입 프로젝트 현황
        List<ProjVO> userProjList = userService.projList(user);
        model.addAttribute("detailUserProjList", userProjList);
        model = userService.inputUserStatusData(user, model); // 해당 사용자 상태별 프로젝트 현황
        // 20201112_이력서 사용자 정보 불러오기 
        model = careerService.selectUserDetail(user, model);
        model.addAttribute("checkCareer", careerService.checkCareer(user));
        // 20201020_이미지 파일명 재명시
        FileVO imgData = fileService.selectImgOne(user);
        model.addAttribute("imgData", imgData);
        return "f:user/user_detail";
    }
    /**
     * 사용자 등록 
     * @param user
     * @return
     */
    @PostMapping(value = "/user/insertUser")
    public String insertUser(UserVO user) {
    	userService.insertUser(user);
    	return "f:user/user"; 
    }
    /**
     * 사용자 수정 
     * @param user
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/user/updateUser")
    public ModelAndView updateUser(UserVO user, FileVO file, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	userService.updateUser(user, file, request); 
    	mav.setViewName("redirect:/main/user/detail?userCode=" + user.getUserCode());
    	return mav;
    }
    /**
     * 사용자 삭제 
     * @param userCode
     * @param user
     * @return
     */
	@PostMapping(value = "/user/deleteUser")
	public ModelAndView deleteProj(@RequestParam(value = "userCode[]") List<Integer> userCode) {
		ModelAndView mav = new ModelAndView();
		UserVO user = new UserVO();
		user.setUSERCODES(userCode);
		String alert = userService.deleteUser(user);
		mav.addObject("alert", alert);
		return mav;
	}
	/**
	 * 이미지 업로드 
	 * @param Mtfile
	 * @param userCode
	 * @param user
	 * @param fileVO
	 * @param request
	 * @return
	 */
    @PostMapping("/file/updateImg")
    @ResponseBody
	public FileVO updateImg(@RequestPart(value = "Mtfile") MultipartFile Mtfile, @RequestParam("userCode") int userCode,
							 UserVO user, FileVO fileVO, HttpServletRequest request) {
		try {
			user.setUserCode(userCode);
			String fileName = Mtfile.getOriginalFilename();
			long fileSize = Mtfile.getSize();
			LOGGER.info("[USER] CODE : " + userCode);
			LOGGER.info("[IMAGE] Upload FILENAME : " + fileName);
			LOGGER.info("[IMAGE] Upload FILESIZE : " + fileSize);
			// application의 절대 경로를 구할 수 있는 ServletContext()를 할 수 있다.
			String path = request.getSession().getServletContext().getRealPath("/");  
			String uploadPath = path + "uploadPath";
			LOGGER.info("[IMAGE] Upload IMGPATH : " + uploadPath);
			String ymdPath = FileService.calcPath(uploadPath);
			File file = new File(uploadPath);
			
			if(!file.exists()) {
				try {
					file.mkdir();
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			fileName = FileService.fileUpload(uploadPath, Mtfile.getOriginalFilename(), Mtfile.getBytes(), ymdPath);
			String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			fileVO.setFilePath("/uploadPath" + ymdPath + File.separator + fileName);
			fileVO.setUserCode(userCode);
			fileVO.setFileDate(currentDate);
			fileVO.setFileDivision("img");
			fileVO.setFileName(fileName);
			fileVO.setFileSize(fileSize);
			//파일 존재 여부 체크
			int checkOne = fileService.imgFileCheck(user);
			if(checkOne == 1) {
				// 해당 사용자의 파일데이터 중 path(경로)를 가져온다.
				FileVO fileData = fileService.selectImgOne(user);
				// 파일 삭제 
				File deleteFile = new File(uploadPath + ymdPath + File.separator + fileData.getFileName());
				deleteFile.delete();
				// 파일 업데이트
				fileService.updateFile(fileVO);
			} else {
				// 파일 등록 
				fileService.insertFile(fileVO);
				return fileVO;
			}
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		} 
		return fileVO;
	}
    /**
     * 이력서 등록
     * @param model
     * @param career
     * @param cert
     * @param user
     * @param comp
     * @param proj
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/user/career/insertCareer")
    public ModelAndView insertCareer( Model model ,CareerVO career, CareerCertVO cert, UserVO user,
    								CareerCompVO comp, CareerProjVO proj, HttpServletResponse response) throws IOException {
    	ModelAndView mav = new ModelAndView("jsonView");
    	// 이력서 존재유무 확인 
    	Boolean ResCheck = careerService.checkCareer(user);
    	// 이력서 데이터 INSERT & UPDATE
    	careerService.insertAndUpdate(mav, ResCheck, career, cert, user, comp, proj);
		return mav;
    }
    /**
     * 근무경력 및 자격증 1 row 삭제 
     * @param cert
     * @param comp
     * @return
     */
    @PostMapping("/user/career/deleteOne")
    public ModelAndView deleteOne(CareerCertVO cert, CareerCompVO comp) {
    	ModelAndView mav = new ModelAndView();
    	careerService.deleteOne(cert, comp);
    	return mav;
    }
    /**
     * 이력서 삭제 
     * @param career
     * @return
     */
    @PostMapping("/user/career/deleteCareer")
    public ModelAndView deleteCareer(CareerVO career) {
    	ModelAndView mav = new ModelAndView("jsonView");
    	careerService.delete(career);
    	mav.setViewName("redirect:/main/user/detail?userCode=" + career.getUserCode());
    	return mav;
    }
}
