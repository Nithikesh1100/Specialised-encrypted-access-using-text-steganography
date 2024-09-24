package com.springauth.SpringAuth.SecretKeyRequests;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface SecretKeyRequestsRepository extends JpaRepository<SecretKeyRequests, Integer>{
	Optional<SecretKeyRequests>  findByProjectId(Integer projectId);
}
