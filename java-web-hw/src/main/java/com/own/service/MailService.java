package com.own.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private MailSender mailSender;

    public void sendMail(String emailTo, String subject, String message) {
        mailSender.send(emailTo, subject, message);
    }
}