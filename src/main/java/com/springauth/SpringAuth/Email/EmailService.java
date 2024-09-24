package com.springauth.SpringAuth.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springauth.SpringAuth.Steganography.SteganographyService;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SteganographyService steganographyService;

    public void sendAuthorizationEmail(String toEmail, Integer projectId) {
        String secretKey = steganographyService.getKey(projectId);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Secret Key Authorization");
        message.setText("Dear User,\n\nYour request for a secret key has been authorized.\n\n"
                + "Here is your secret key: " + secretKey + "\n\n"
                + "Please keep it secure and do not share it with anyone.\n\n"
                + "Best regards,\nAdmin");

        mailSender.send(message);
    }
}

