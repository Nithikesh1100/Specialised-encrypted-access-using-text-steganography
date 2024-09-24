package com.springauth.SpringAuth.Steganography;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SteganographyService {
	
	private final SteganographyUtil steganographyUtil;
	
	
	@Autowired
	public SteganographyService(SteganographyUtil steganographyUtil) {
		this.steganographyUtil = steganographyUtil;
	}

	public String encodeMessage(String message, String secret, Integer projectId) throws Exception {
		System.out.println("steg service");
        return steganographyUtil.encodeAfterEncryption(message, secret, projectId);
    }

    public String decodeMessage(String encodedMessage) {
        return SteganographyUtil.decode(encodedMessage);
    }
    
   
    public String decodeDecryptorMessage(String message, String secretKeyString) throws Exception {
        return steganographyUtil.decodeAfterDecryption(message, secretKeyString);
    }
    
    public String getKey(Integer projectId) {
    	return steganographyUtil.Key(projectId);
    }
    
    public String getMessage(Integer projectId) {
    	return steganographyUtil.encodedMessage(projectId);
    }
    
    
}