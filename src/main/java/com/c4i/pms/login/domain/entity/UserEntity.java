package com.c4i.pms.login.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * DB Table과 매칭된 Entity
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "kbm_user")
public class UserEntity {
	@Id
    @Column(name = "US_ID", length = 50, nullable = true)
    private String usId;
    @Column(name = "US_PASS", length = 60, nullable = true)
    private String usPass;
    @Column(name = "US_NAME", length = 50, nullable = true)
    private String usName;
    @Column(name = "AUTH", columnDefinition="char(2)", nullable = true)
    private String auth;
    @Column(name = "USE_YN", columnDefinition="char", nullable = true)
    private String useYN;
    @Column(name = "BUSEO", length = 100, nullable = false)
    private String buseo;
    @Column(name = "TEL", length = 20, nullable = false)
    private String tel;
    @Column(name = "MTEL", length = 20, nullable = false)
    private String mTel;
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Builder
    public UserEntity(String usId, String usPass, String usName, String auth, String useYN, String buseo, String tel, String mTel, String email) {
        this.usId = usId;
        this.usPass = usPass;
        this.usName = usName;
        this.auth = auth;
        this.useYN = useYN;
        this.buseo = buseo;
        this.tel = tel;
        this.mTel = mTel;
        this.email = email;
    }
}