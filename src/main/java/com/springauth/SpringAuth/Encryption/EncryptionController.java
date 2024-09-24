package com.springauth.SpringAuth.Encryption;



import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

//    @PostMapping("/encrypt")
//    public String encrypt(@RequestBody String data) {
//        return encryptionService.encrypt(data);
//    }
    
    


    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedData) {
        return encryptionService.decrypt(encryptedData);
    }
    
//    @PostMapping("/decrypt")
//    public ResponseEntity<String> decrypt(@RequestBody Map<String, String> request) throws Exception {
//        String encryptedData = request.get("encryptedData");
//        String keyString = request.get("key");
//        SecretKey key = EncryptionUtil.getKeyFromString(keyString);
//        String decryptedData = EncryptionUtil.decrypt(encryptedData, key);
//        return ResponseEntity.ok(decryptedData);
//    }
}
