package com.c4i.pms.login.domain.repository;

import com.c4i.pms.login.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 로그인시에 사용자정보를 조회하는 인터페이스
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsId(String usId);
}