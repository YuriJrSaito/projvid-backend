package com.appapi.projvid.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.appapi.projvid.entity.user.UserAccess;

import jakarta.transaction.Transactional;

public interface AccessRepository extends JpaRepository<UserAccess, UUID>{
	Optional<UserAccess> findByUsername(String username);

	@Transactional
	@Modifying
	@Query(value = "UPDATE useraccess set is_logged = ?2 where id = ?1", nativeQuery = true)
	void updateIsLoggedById(UUID id, Boolean logged);
}
