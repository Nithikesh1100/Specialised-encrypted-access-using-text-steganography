package com.springauth.SpringAuth.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.SpringAuth.SecretKeyRequests.RequestsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestsService requestsService;

    @PostMapping("/authorize")
    public ResponseEntity<String> authorizeRequest(@RequestParam String email, @RequestParam Integer projectId) {
        emailService.sendAuthorizationEmail(email, projectId);
        return ResponseEntity.ok("Authorization email sent successfully.");
    }
    
    @DeleteMapping("/deleterequest/{projectId}")
    public ResponseEntity<String> deleteRequest(@PathVariable Integer projectId) {
        // Delete the request from the database
        boolean isDeleted = requestsService.deleteRequestByProjectId(projectId);

        if (isDeleted) {
            return ResponseEntity.ok("Request deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Request not found.");
        }
    }
}
