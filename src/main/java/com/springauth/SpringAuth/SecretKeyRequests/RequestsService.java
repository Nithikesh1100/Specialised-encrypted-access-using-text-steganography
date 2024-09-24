package com.springauth.SpringAuth.SecretKeyRequests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import com.springauth.SpringAuth.user.User;
import com.springauth.SpringAuth.user.UserRepository;

@Component
public class RequestsService {
	@Autowired
	SecretKeyRequestsRepository secretKeyRequestsRepository;

	@Autowired
	UserRepository userRepository;

	public List<RequestDTO> getAllRequests() {
		List<RequestDTO> requestDTOList = new ArrayList<>();

		// Fetch all requests
		List<SecretKeyRequests> requests = secretKeyRequestsRepository.findAll();

		for (SecretKeyRequests request : requests) {
			// Fetch user details for each request
			User user = userRepository.findById(request.getUserId())
					.orElseThrow(() -> new RuntimeException("User not found"));
			
			 String username = user.getFirstname() + " " + user.getLastname();
			// Create a RequestDTO
			RequestDTO requestDTO = new RequestDTO(user.getEmail(), username,
					request.getProjectId());

			// Add to the list
			requestDTOList.add(requestDTO);
		}

		return requestDTOList;
	}

	public void processSecretKeyRequest(RequestDTO requestDTO) {
		SecretKeyRequests request = new SecretKeyRequests();
        request.setProjectId(requestDTO.getProjectId());
        
        // Assume user is obtained or set somehow (you might need to adjust this)
        User user = userRepository.findByEmail(requestDTO.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        request.setUserId(user.getId());
        
        // Save request
        secretKeyRequestsRepository.save(request);

	}
	
	public boolean deleteRequestByProjectId(Integer projectId) {
        // Implement the deletion logic, e.g., checking if the request exists and then deleting it
        Optional<SecretKeyRequests> request = secretKeyRequestsRepository.findByProjectId(projectId);
        if (request.isPresent()) {
            secretKeyRequestsRepository.delete(request.get());
            return true;
        } else {
            return false;
        }
    }
}
