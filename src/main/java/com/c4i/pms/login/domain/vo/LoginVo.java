package com.c4i.pms.login.domain.vo;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import com.c4i.pms.login.domain.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 세션에 담겨있을 로그인 정보 VO
 */
@Getter @Setter @ToString
public class LoginVo extends User {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String usId = "";
    private String usName = "";
    private String buseo = "";
    private String tel = "";
    private String mTel = "";
    private String email = "";
    private String auth = "";
    private boolean useYN = false;  // 계정 잠김 여부

    public LoginVo(UserEntity userEntity, List<GrantedAuthority> authorities) {
        super(userEntity.getUsId(), userEntity.getUsPass(), authorities);

        this.usId = userEntity.getUsId();
        this.usName = userEntity.getUsName();
        this.buseo = userEntity.getBuseo();
        this.tel = userEntity.getTel();
        this.mTel = userEntity.getMTel();
        this.email = userEntity.getEmail();
        this.auth = userEntity.getAuth();

        this.useYN = (userEntity.getUseYN().equals("Y"));
    }

    /**
     * 스프링 시큐리티 - 계정 비활성화 부분 오버라이딩
     * @return
     */
    @Override
    public boolean isEnabled(){
        return this.useYN;
    }
}

