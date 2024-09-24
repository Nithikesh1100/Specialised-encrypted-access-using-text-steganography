package com.springauth.SpringAuth.APIRequests;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestApiRepository extends JpaRepository<RequestApi, Long> {

//	String findApiKeysByProjectId(Integer projectId);
	@Query("SELECT r.apis FROM RequestApi r WHERE r.projectId = :projectId")
    String findApiKeysByProjectId(@Param("projectId") Integer projectId);
	
}