package com.springauth.SpringAuth.Encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import com.springauth.SpringAuth.SecretKeys.SecretKeysRepository;

import java.util.Base64;

//@Component
//public class EncryptionUtil {
//
//    private static final String ALGORITHM = "AES";
//    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding"; 
//    private  final  SecretKeysRepository secretKeysRepository;
//    
//    @Autowired
//    public EncryptionUtil(SecretKeysRepository secretKeysRepository) {
//		
//		this.secretKeysRepository = secretKeysRepository;
//	}
//
//	
//	// Generate a new AES key
//    public static SecretKey generateKey() throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
//        keyGenerator.init(256); // You can use 128 or 192 bits key size also
//        return keyGenerator.generateKey();
//    }
//
//    // Convert key string to SecretKey
//    public static SecretKey getKeyFromString(String keyStr) {
//        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
//        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
//    }
//
//    // Convert SecretKey to string
//    public static String getKeyAsString(SecretKey key) {
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//    }
//
//    // Encrypt data using a specified key
//    public static String encrypt(String data, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    // Decrypt data using a specified key
//    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//        return new String(decryptedBytes);
//    }
//
//    // Encrypt data by generating a new key
//    public  String encryptAPI(String data, SecretKey key) throws Exception {
////        SecretKey key = generateKey(); // Automatically generate a new key
//        System.out.println("Generated Key: " + getKeyAsString(key)); // Print the generated key
//        
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//        System.out.println("encrypt util");
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    
//}

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding"; 
    private final SecretKeysRepository secretKeysRepository;
    
    @Autowired
    public EncryptionUtil(SecretKeysRepository secretKeysRepository) {
        this.secretKeysRepository = secretKeysRepository;
    }

    // Generate a new AES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256); // Ensure the key size is consistent
        return keyGenerator.generateKey();
    }

    // Convert key string to SecretKey
    public static SecretKey getKeyFromString(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
    }

    // Convert SecretKey to string
    public static String getKeyAsString(SecretKey key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Encrypt data using a specified key
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt data using a specified key
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Encrypt data by generating a new key
    public String encryptAPI(String data, SecretKey key) throws Exception {
        //SecretKey key = generateKey(); // Automatically generate a new key
        System.out.println("Generated Key: " + getKeyAsString(key)); // Print the generated key

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        System.out.println("encrypt util");
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
