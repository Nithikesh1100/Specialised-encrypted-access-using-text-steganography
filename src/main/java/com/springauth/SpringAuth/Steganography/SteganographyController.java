package com.springauth.SpringAuth.Steganography;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.SpringAuth.SecretKeys.SecretKeys;
import com.springauth.SpringAuth.SecretKeys.SecretKeysRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/api/steganography")
public class SteganographyController {

    @Autowired
    private SteganographyService steganographyService;
    
    @Autowired
    private SecretKeysRepository secretKeysRepository;

    @PostMapping("/encode")
    public ResponseEntity<String> encode(@RequestParam String message, @RequestParam String secret, @RequestParam Integer projectId) throws Exception {
        String encodedMessage = steganographyService.encodeMessage(message, secret, projectId);
        System.out.println("steg controller working");
        return ResponseEntity.ok(encodedMessage);
    }

    @PostMapping("/decode")
    public ResponseEntity<String> decode(@RequestBody String encodedMessage) {
        String secret = steganographyService.decodeMessage(encodedMessage);
        return ResponseEntity.ok(secret);
    }
    
    @PostMapping("/decodeNdecrypt")
    public String decodeAfterDecryption(@RequestParam String encodedMessage, @RequestParam String secretKeyString) throws Exception {
        return steganographyService.decodeDecryptorMessage(encodedMessage, secretKeyString);
    }
    
    @GetMapping("/key")
    public String getKeyfromId(@RequestParam Integer projectId) {
    	return steganographyService.getKey(projectId);
    }
    
    @GetMapping("/encodedMessage/{projectId}")
    public ResponseEntity<String> getEncodedMessage(@PathVariable Integer projectId) {
        SecretKeys secretKeyEntity = secretKeysRepository.findByProjectId(projectId);
        if (secretKeyEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(secretKeyEntity.getEncodedMessage());
    }
    
}
