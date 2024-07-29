package com.bilgeadam.okul.yonetim.sistemi.proje1.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserSaveDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String EMAIL);

	boolean existsByEmail(String EMAIL);

	Optional<UserEntity> findByName(String currentUsername);

	void save(UserSaveDto user);	
	 List<UserEntity> findByNameContainingIgnoreCase(String name);	
}
