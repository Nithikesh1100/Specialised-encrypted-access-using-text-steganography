package com.springauth.SpringAuth.Encryption;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class EncryptionService {

    private final SecretKey secretKey;

    public EncryptionService(@Value("${encryption.key}") String secretKeyStr) {
        this.secretKey = EncryptionUtil.getKeyFromString(secretKeyStr);
    }

    public String encrypt(String data) {
        try {
            return EncryptionUtil.encrypt(data, secretKey);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            return EncryptionUtil.decrypt(encryptedData, secretKey);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
    
    
}

