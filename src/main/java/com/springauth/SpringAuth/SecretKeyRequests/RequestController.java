package com.springauth.SpringAuth.SecretKeyRequests;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class RequestController {
	
	@Autowired
	RequestsService requestsService;

    @GetMapping("/getsecretkeyrequests")
    public ResponseEntity<List<RequestDTO>> getRequests() {
        List<RequestDTO> requests = requestsService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
    
    @PostMapping("/sendrequestSecretKey")
    public ResponseEntity<String> sendRequestSecretKey(@RequestBody RequestDTO requestDTO) {
        try {
            // Process the request here
            requestsService.processSecretKeyRequest(requestDTO);

            // Return a success response
            return ResponseEntity.ok("Request processed successfully");
        } catch (Exception e) {
            // Handle exceptions and return an error response
            return ResponseEntity.status(500).body("An error occurred while processing the request");
        }
    }
}
