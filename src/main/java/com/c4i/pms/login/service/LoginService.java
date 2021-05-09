package com.c4i.pms.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.c4i.pms.login.domain.Role;
import com.c4i.pms.login.domain.entity.UserEntity;
import com.c4i.pms.login.domain.repository.UserRepository;
import com.c4i.pms.login.domain.vo.LoginVo;

import lombok.AllArgsConstructor;

/**
 * 로그인 서비스 구현체
 */
@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Spring Security로 로그인된 사용자의 권한 확인
     * @param role
     * @return
     */
    public static boolean checkAuthentication(Role role) {
        SimpleGrantedAuthority targetRole = new SimpleGrantedAuthority(role.getValue());
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(targetRole);
    }

    /**
     * Spring Security User정보에 저장된 ID값 반환
     * @return
     */
    public static String getLoginId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentLoginId = user.getUsername();
        return (currentLoginId.equals("anonymousUser") ? "" : currentLoginId);
    }

    /**
     * Spring Security User 정보 반환
     * @return
     */
    public static LoginVo getLoginVo() {
        Object loginVo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(loginVo instanceof LoginVo){
            return (LoginVo) loginVo;
        }else{
            return null;
        }
    }

    /**
     * 상세정보 조회
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityWrapper = userRepository.findByUsId(userId);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        /**
         * 롤 부여
         */
        for(Role role : Role.values()) {
            String roleKey = ((role.getValue().equals("ROLE_" + userEntity.getAuth())) ? role.name() : "");
            if (role.getValue().equals("ROLE_" + userEntity.getAuth())) {
                authorities.add(new SimpleGrantedAuthority(Role.valueOf(roleKey).getValue()));
            }
        }


        /**
         * SpringSecurity에서 제공하는 UserDetails를 구현한 User를 반환합니다.
         * ( org.springframework.security.core.userdetails.User )
         */
        return new LoginVo(userEntity, authorities);
//        return new User(userEntity.getUsId(), userEntity.getUsPass(), authorities);
    }
}
