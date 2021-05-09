package com.c4i.pms.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 임시로 사용하는 권한정보
 */
@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_10"),                    // 관리자
    MEMBER_VIEW("ROLE_20"),              // 전체조회
    MEMBER_OWN("ROLE_30");               // 전체조회 및 본인사업만 수정

    private final String value;
}