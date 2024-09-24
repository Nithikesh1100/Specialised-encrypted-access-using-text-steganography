package com.springauth.SpringAuth.Steganography;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springauth.SpringAuth.Encryption.EncryptionService;
import com.springauth.SpringAuth.Encryption.EncryptionUtil;
import com.springauth.SpringAuth.SecretKeys.SecretKeys;
import com.springauth.SpringAuth.SecretKeys.SecretKeysRepository;

@Component
public class SteganographyUtil {

    private static final char ZERO_WIDTH_SPACE = '\u200B'; // Used for '1'
    private static final char ZERO_WIDTH_NON_JOINER = '\u200C'; // Used for '0'
    
    
    private final SecretKeysRepository secretKeysRepository;
    private final EncryptionUtil encryptionUtil; // Assuming you have an instance of EncryptionUtil

    public SteganographyUtil(SecretKeysRepository secretKeysRepository, EncryptionUtil encryptionUtil) {
        this.secretKeysRepository = secretKeysRepository;
        this.encryptionUtil = encryptionUtil;
    }

    

//	
    
    public static String encode(String message, String secret) {
        StringBuilder encodedMessage = new StringBuilder();
        StringBuilder binarySecret = new StringBuilder();

        // Convert secret to binary
        for (char c : secret.toCharArray()) {
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binarySecret.append(binaryString);
        }

        // Calculate the length of the binary representation needed for the message
        int messageLengthInBits = message.length() * 8;
        int binarySecretLength = binarySecret.length();

        // Calculate how many full blocks of the binary secret are needed
        int fullBlocksRequired = (messageLengthInBits + binarySecretLength - 1) / binarySecretLength; // Round up

        // Generate the full binary secret by repeating it
        StringBuilder fullBinarySecret = new StringBuilder();
        for (int i = 0; i < fullBlocksRequired; i++) {
            fullBinarySecret.append(binarySecret);
        }

        // Trim excess binary secret data to match the exact length required for the message
        fullBinarySecret.setLength(messageLengthInBits);

        int secretIndex = 0;
        for (char c : message.toCharArray()) {
            encodedMessage.append(c);

            // Encode each character in binary
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binaryString.toCharArray()) {
                if (secretIndex >= fullBinarySecret.length()) {
                    throw new IllegalArgumentException("Secret key is too short for the message.");
                }
                char secretChar = fullBinarySecret.charAt(secretIndex++);
                encodedMessage.append(secretChar == '1' ? ZERO_WIDTH_SPACE : ZERO_WIDTH_NON_JOINER);
            }
        }

        return encodedMessage.toString();
    }






    public static String decode(String encodedMessage) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (char c : encodedMessage.toCharArray()) {
            if (c == ZERO_WIDTH_SPACE) {
                binaryStringBuilder.append('1');
            } else if (c == ZERO_WIDTH_NON_JOINER) {
                binaryStringBuilder.append('0');
            }
        }

        // Convert binary string to characters
        String binaryString = binaryStringBuilder.toString();
        StringBuilder decodedMessage = new StringBuilder();

        // Split binary string into chunks of 8 bits
        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteString = binaryString.substring(i, i + 8);
            char character = (char) Integer.parseInt(byteString, 2);
            decodedMessage.append(character);
        }

        String fullDecodedMessage = decodedMessage.toString();

        // Apply the HelperUtil methods
        String cleanedMessage = HelperUtil.removePartialRepetition(fullDecodedMessage);
        String originalMessage = HelperUtil.findOriginalSubstring(cleanedMessage);

        return originalMessage;
        
    }
    
    public String encodeAfterEncryption(String coverText, String secretMessage, Integer projectId) throws Exception {
        // Generate a new secret key
        SecretKey secretKey = EncryptionUtil.generateKey();

        // Convert the secret key to a string
        String secretKeyString = EncryptionUtil.getKeyAsString(secretKey);

        // Print the generated secret key to the console
        System.out.println("Generated Secret Key: " + secretKeyString);
        
        // Encrypt the secret message
        String encryptedSecretMessage = "";
        try {
            encryptedSecretMessage = encryptionUtil.encryptAPI(secretMessage, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("steg util");

        // Encode the encrypted secret message using steganography
        String encodedMessage = encode(coverText, encryptedSecretMessage);

        // Save the secret key and encoded message in the SecretKeys entity
        SecretKeys secretKeysEntity = new SecretKeys();
        secretKeysEntity.setProjectId(projectId);
        secretKeysEntity.setSecretKey(secretKeyString);
        secretKeysEntity.setEncodedMessage(encodedMessage); // Save the encoded message
        secretKeysRepository.save(secretKeysEntity);

        return encodedMessage;
    }
    
    public  String decodeAfterDecryption(String encodedMessage, String secretKeysString) throws Exception {
        // Convert the secret key string back to a SecretKey object
//    	SecretKeys secretKeyString=secretKeysRepository.findByProjectId(projectId);
        SecretKey secretKey = EncryptionUtil.getKeyFromString(secretKeysString);

        // Decode the hidden encrypted message using steganography
        String decodedEncryptedMessage = decode(encodedMessage);

        // Decrypt the decoded encrypted message
        return encryptionUtil.decrypt(decodedEncryptedMessage, secretKey);
    }
    
    public String Key(Integer projectId) {
    	SecretKeys secretKeyString=secretKeysRepository.findByProjectId(projectId);
    	return secretKeyString.getSecretKey();
    }
    
    public String encodedMessage(Integer projectId) {
        SecretKeys secretKeysEntity = secretKeysRepository.findByProjectId(projectId);
        if (secretKeysEntity != null) {
            return secretKeysEntity.getEncodedMessage();
        } else {
            return null; // or throw an exception if appropriate
        }
    }


}
