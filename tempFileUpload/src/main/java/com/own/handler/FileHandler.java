package com.own.handler;

import com.own.service.MailSender;
import com.own.service.MailService;
import com.own.service.MailService;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class FileHandler {
    private String name;
    private MultipartFile file;


    public FileHandler(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String proceedFile() {
        MailService mailService = new MailService();
        mailService.sendMail();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(name + "-uploaded", true));
                stream.write(bytes);
                stream.close();
                System.out.println(stream);
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
