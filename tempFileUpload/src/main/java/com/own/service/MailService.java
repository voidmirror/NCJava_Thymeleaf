package com.own.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MailService {
    @Autowired
    private MailSender mailSender;

    public void sendMail() {
        mailSender.send("mapep20430@edmondpt.com", "something", "Hello from mail sender");
    }
}
