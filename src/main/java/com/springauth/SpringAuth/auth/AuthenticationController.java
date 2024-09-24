package com.springauth.SpringAuth.auth;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.SpringAuth.Question.Question;
import com.springauth.SpringAuth.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;
	private final UserRepository userRepository;
	

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	@GetMapping("/authenticate")
	public String login() {
		return "Login Woking";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "Logged out successfully";

	}
	
	

}
