package com.springauth.SpringAuth.SecretKeyRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
	
	String username;
	String email;
	Integer projectId;
}
