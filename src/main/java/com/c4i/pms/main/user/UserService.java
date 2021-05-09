package com.c4i.pms.main.user;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.c4i.pms.comm.util.CommUtil;
import com.c4i.pms.main.file.FileService;
import com.c4i.pms.main.file.vo.FileVO;
import com.c4i.pms.main.proj.ProjController;
import com.c4i.pms.main.proj.vo.ProjVO;
import com.c4i.pms.main.user.vo.UserVO;
import com.c4i.pms.main.user.vo.projStatusVO;
import com.c4i.pms.mapper.main.ProjUserMapper;
import com.c4i.pms.mapper.main.UserMapper;
import com.c4i.pms.web.method.serverpage.ServerPage;
import com.c4i.pms.web.method.serverpage.ServerPageCommand;
	
@Service("UserService")
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjController.class);
	
    private UserMapper userMapper;

    private ProjUserMapper projUserMapper;
    
    @Resource(name = "FileService")
    private FileService fileService;
    /**
     * [ 사용자 현황 ]유저 리스트
     * @param command
     * @return
     */
    public ServerPage userList1(ServerPageCommand command) {
    	int count = userMapper.PMSUserCount(command);
    	List<UserVO> userList = userMapper.PMSUserList1(command);
    	command.setCount(count);
    	return new ServerPage(command, null, userList, null);
    }
    /**
     * 투입인원 리스트 
     * @param user
     * @return
     */
    public List<UserVO> InputuserList(UserVO user) {
    	return userMapper.InputUserList(user);
    }
    /**
     * [ 투입인원 추가 팝업창  ]유저 리스트
     * @return
     */
    public List<UserVO> userList2() {
        return userMapper.PMSUserList2();
    }
    /**
     * 해당 유저 상세정보 
     * @param user
     * @return
     */
    public UserVO UserOne(UserVO user) {
        return userMapper.PMSUserOne(user);
    }
    /**
     * 해당 사용자 존재 여부
     * @param user
     * @return
     */
    public int UserCount(UserVO user) {
        return userMapper.PMSUserOneCount(user);
    }
    /**
     * 해당 사용자 상세 정보 
     * @param user
     * @return
     */
    public UserVO InputUserOne(UserVO user) {
    	return userMapper.PMSInputUserOne(user);
    }
    /**
     * 사용자 신규 등록
     * @param user
     */
    public void insertUser(UserVO user) {
    	String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String reCurrentDate = currentDate.replace("-",".");
		user.setUserModifyDate(reCurrentDate);
    	user.setUserDelete("N");
    	userMapper.insertUser(user);
    }
    /**
     * 사용자 수정( + 이미지 업로드 및 이력서 업로드)
     * @param user
     * @param fileVO
     * @param request
     */
    public void updateUser(UserVO user, FileVO fileVO, HttpServletRequest request) {
    	String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		String reCurrentDate = currentDate.replace("-",".");
		user.setUserModifyDate(reCurrentDate);
		// 이미지 업로드 
		if(user.getUploadImg() != null && user.getUploadImg().getSize() > 0 && user.getUploadImg().getOriginalFilename() != "") {
			try {
				String fileName = user.getUploadImg().getOriginalFilename();
				long fileSize = user.getUploadImg().getSize();
				String path = request.getSession().getServletContext().getRealPath("/"); // 절대경로 
				String uploadPath = path + "resources" + File.separator + "img" + File.separator +  "uploadPath";
				String ymdPath = FileService.calcPath(uploadPath);
				File file = new File(uploadPath);

				if(!file.exists()) {
					try {
						file.mkdir();
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				fileName = FileService.fileUpload(uploadPath, user.getUploadImg().getOriginalFilename(), user.getUploadImg().getBytes(), ymdPath);
				String strCurrentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
				fileVO.setFilePath("/resources" + File.separator + "img"+ File.separator + "uploadPath" + ymdPath + File.separator + fileName);
				fileVO.setUserCode(user.getUserCode());
				fileVO.setFileDate(strCurrentDate);
				fileVO.setFileDivision("img");
				fileVO.setFileName(fileName);
				fileVO.setFileSize(fileSize);
				//파일 존재 여부 체크
				int checkOne = fileService.imgFileCheck(user);
				if(checkOne > 0) {
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
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());			
			}
		}
    	userMapper.updatePMSUser(user);
    }
    /**
     * 사용자 삭제 
     * 2020-01-04 리팩토링 : 유효성 검사 추가 
     * @param user
     */
    public String deleteUser(UserVO user) {
    	if(user != null) {
    		for(int userCode: user.getUSERCODES()) {
    			user.setUserCode(userCode);
    			user.setUserDelete("Y");
    			userMapper.deletePMSUser(user);
    		}
    		return "사용자가 삭제되었습니다.\n복구 시 담당자에게 문의바랍니다.";
    	} else {
    		return "사용자 삭제 오류가 발생하였습니다.\n담당자에게 문의바랍니다.";
    	}
    }
    /**
     * 투입 사용자의 프로젝트 현황
     * 2020-12-23 리팩토링 : CommUtil 활용 
     * @param user
     * @return
     */
    public List<ProjVO> projList(UserVO user) {
    	List<ProjVO> projList = projUserMapper.inputUserProjList(user);
		// 남은 기간 계산
		for(int i =0; i < projList.size(); i++) {
			String budget = CommUtil.setCommaBudget(projList.get(i).getProjBudget());
				long diffDay = CommUtil.CalcDiffDay(projList.get(i).getProjInDate(), projList.get(i).getProjOutDate());
				long leftDay = CommUtil.CalcLeftDay(projList.get(i).getProjOutDate());
				projList.get(i).setProjBudget(budget);
				projList.get(i).setProjLeftDate(leftDay);
				projList.get(i).setProjDate(diffDay);
		}
		return projList;
	}
    /**
     * 해당 사용자 상태별 프로젝트 현황
     * 2020-01-01 리팩토링 : 서브쿼리 이용해 하나의 쿼리로 정리 
     * @param user
     * @param model
     * @return
     */
    public Model inputUserStatusData(UserVO user, Model model) {
    	projStatusVO projStatusData = userMapper.inputUserStatusData(user);
    	model.addAttribute("projStatusData", projStatusData);
    	return model;
    }
    // setter 활용하여 의존성 주입 필요
    @Autowired
    public void setUserMapper(UserMapper usMapper) {
        this.userMapper = usMapper;
    }
    @Autowired
    public void setProjUserMapper(ProjUserMapper puMapper) {
        this.projUserMapper = puMapper;
    }
    
}
