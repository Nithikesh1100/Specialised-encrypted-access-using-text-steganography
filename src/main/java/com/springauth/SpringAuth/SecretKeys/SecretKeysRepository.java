package com.springauth.SpringAuth.SecretKeys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SecretKeysRepository extends JpaRepository<SecretKeys, Long> {
//	@Query("SELECT s.secretKey FROM SecretKeys s WHERE s.projectId = :projectId")
	SecretKeys  findByProjectId(Integer projectId);
}
