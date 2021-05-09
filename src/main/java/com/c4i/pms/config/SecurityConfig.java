package com.c4i.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.c4i.pms.login.service.LoginFailureHandler;
import com.c4i.pms.login.service.LoginService;
import com.c4i.pms.login.service.LoginSuccessHandler;

import lombok.AllArgsConstructor;
// 출처 : https://victorydntmd.tistory.com/328
/**
 * Spring Security 설정
 */
@Configuration		/** @Configuration : 스프링 부트 설정*/
@EnableWebSecurity	/** @EnableWebSecurity : Spring Security 설정할 클래스 */
@AllArgsConstructor /** WebSecurityConfigurerAdapter 클래스 상속[default] */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final LoginService loginService;
    private final String loginIdName = "userId";
    private final String loginPwdName = "userPass";
    private final String loginFormUrl = "/login/login";


    /**
     *  BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  WebSecurity는 필터 항목
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
    	/**
    	 * 이 파일들은 무조건 통과하며, 파일 기준은 resources/static 디렉터리입니다. ( css, js 등의 디렉터리를 추가하진 않았습니다. )
    	 */
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    /**
     * 로그인 성공 후,
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler("/main/search");
    }

    /**
     * 로그인 실패 후,
     * @return
     */
    @Bean
    public AuthenticationFailureHandler failHandler() {
        return new LoginFailureHandler(loginIdName, loginPwdName, loginFormUrl);
    }

    /**
     * HttpSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성할 수 있습니다.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception { // HttpSecurity : HTTP요청에 대한 웹 기반 보안을 구성 
        http
             .authorizeRequests()	                    //# 페이지 권한 설정(HttpServletRequest에 따라 접근을 제한)
//           .antMatchers : 특정 경로 지정
//                .antMatchers("/", "/login/**", "/resources/**").permitAll()
//                .antMatchers("/main/dashboard/**").permitAll()
//                .antMatchers("/main/code/**").hasRole("10") // CodeCommService 이용하기 위해서 권한 허용
             .antMatchers("/main/search/**").hasRole("10")    
             .antMatchers("/main/proj/**").hasRole("10") 
             .antMatchers("/main/user/**").hasRole("10") // /main/{1}/** 의 권한을 10이라는 권한을 가진 사진에게 허용
                .antMatchers("/**/**").permitAll()    // 모든 경로에 대해서 권한없이 접근 가능
//                .anyRequest().authenticated()	        // 모든 요청에 대해, 인증된 사용자만 접근하도록 설정할 수도 있습니다.
            .and().formLogin() 						    //# 로그인
                .loginPage(loginFormUrl)                // 로그인 페이지
                .successHandler(successHandler())       // 로그인 성공 핸들러
                .failureHandler(failHandler())          // 로그인 실패 핸들러
                .usernameParameter(loginIdName)	        // form tag id
                .passwordParameter(loginPwdName)	    // form tag password
                .permitAll()                            // 권한 전체
            .and().logout() 					        //# 로그아웃 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/login/loginOut"))
                .logoutSuccessUrl(loginFormUrl)         // 로그아웃 성공 핸들러
                .invalidateHttpSession(true)            // 로그아웃시 인증정보를 지우고 세션을 무효화
//                .deleteCookies("KEY명")	            // 로그아웃 시, 특정 쿠기를 제거하고 싶을 때 사용하는 메서드입니다.
            .and().exceptionHandling()		            //# 403 예외처리 핸들링
                .accessDeniedPage("/main/error_403")    // 403 페이지 이동
            .and().headers().disable();                 // http 헤더 비활성화 (악의적인 http 헤더 사용을 방지하기 위해서)
        // 세션수 제한
//      http.sessionManagement().maximumSessions(100).maxSessionsPreventsLogin(true);
    }
    
    

    /**
     * Spring Security에서 모든 인증은 AuthenticationManager를 통해 이루어지며 
     * AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용합니다.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	/**
    	 * 로그인 처리 즉, 인증을 위해서는 UserDetailService를 통해서 필요한 정보들을 가져오는데, 예제에서는 서비스 클래스(memberService)에서 이를 처리합니다.
    	 */
        auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
    }
}