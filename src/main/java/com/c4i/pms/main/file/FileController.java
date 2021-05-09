package com.c4i.pms.main.file;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.c4i.pms.main.file.vo.FileVO;
import com.c4i.pms.main.proj.ProjController;
import com.c4i.pms.main.proj.ProjService;
import com.c4i.pms.main.user.UserService;
import com.c4i.pms.main.user.vo.UserVO;

@Controller
@RequestMapping("/main")
public class FileController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjController.class);

	@Resource(name = "ProjService")
	private ProjService projService;

	@Resource(name = "UserService")
	private UserService userService;

	@Resource(name = "FileService")
	private FileService fileService;


	@PostMapping("/file/insertImg")
	public FileVO insertFile(@RequestParam("uploadImg") MultipartFile Mtfile, @RequestParam("userCode") int userCode, 
			UserVO user, FileVO fileVO, HttpServletRequest request) { 
			 
		try {
			String fileName = Mtfile.getOriginalFilename();
			long fileSize = Mtfile.getSize();
			LOGGER.info("[USER] CODE : " + userCode);
			LOGGER.info("[IMAGE] Upload FILENAME : " + fileName);
			LOGGER.info("[IMAGE] Upload FILESIZE : " + fileSize);
			String path = request.getSession().getServletContext().getRealPath("/"); // 이게 뭐지??
			String uploadPath = path + File.separator + "resources" + File.separator + "profile" + File.separator +  "uploadPath";
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
			fileVO.setFilePath("/resources" + File.separator + "profile"+ File.separator + "uploadPath" + ymdPath + File.separator + fileName);
			fileVO.setUserCode(userCode);
			//사용자 체크
			UserVO check = userService.UserOne(user); // 사용자 존재 유무
			if(check.getUserCode() != 0) {
				// 파일 업데이트 
			} else {
				return fileVO;
			}
			 
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		} 
		return fileVO;
	}
	
	
	
}
