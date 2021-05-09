package com.c4i.pms.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.c4i.pms.login.domain.vo.LoginVo;
import com.c4i.pms.login.service.LoginService;


@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    /**
     * TODO : yml 파일내에 정보가져오는 방법
     */
    @Value("${pems.file.uploadpath}")
    private String filepath;

    /**
     * 루트경로 접속시
     * @param loginVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal LoginVo loginVo) throws Exception {
        log.info("TEST : " + filepath, "HELLO LOG");
        return "redirect:/login/login";
    }
    /**
     * 로그인
     * @param request
     * @return
     */
    @RequestMapping("/login/login")
    public String code_account(@AuthenticationPrincipal LoginVo loginVo, HttpSession session, HttpServletRequest request) {
        String returnUrl = "login/login";
        if(loginVo != null){
            returnUrl = "redirect:/main/proj";
        }
        return returnUrl;
    }
    /**
     * 로그인 후, 첫페이지
     * @param loginVo
     * @return
     * @throws Exception
     */
    @RequestMapping("/main/index")
    public String mainIndex(@AuthenticationPrincipal LoginVo loginVo) throws Exception {
        log.debug("## LoginVo@ : " + loginVo);
        log.debug("## LoginVo-LoginService : " + LoginService.getLoginVo());
        return "m:index";
    }
}