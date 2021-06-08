package com.own.handler;

import com.own.auxiliary.ResString;
import com.own.model.User;
import com.own.repository.UserRepository;
import com.own.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler implements ResString {
    private String name;
    private MultipartFile file;

//    @Autowired
    private UserRepository userRepository;

    public FileHandler(String name, MultipartFile file, UserRepository userRepository) {
        this.name = name;
        this.file = file;
        this.userRepository = userRepository;
    }

    public String proceedFile() {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(name + "-uploaded"));
                stream.write(bytes);
                stream.close();
                System.out.println(stream);
                //TODO: addFileToDatabase()
                boolean b = addFileToDatabase();
                System.out.println("HEY");
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded ! Обновление базы данных:" + b;
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

    private boolean addFileToDatabase() {
        Pattern pattern = Pattern.compile(".+@.+\\..+");
        File f = new File(name + "-uploaded");
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line = reader.readLine();
            System.out.println(line);

            while (line != null) {
                System.out.println("While iteration");
                String[] arr = line.split(":");

                if (arr.length != 6) {
                    return false;
                }

                User user = new User();
                user.setFirstName(arr[0]);
                user.setLastName(arr[1]);
                user.setPatronymic(arr[2]);
                user.setEmail(arr[3]);
                user.setWorkPlace(arr[4]);
                user.setSalary(Double.parseDouble(arr[5]));

                System.out.println(user);

                Matcher matcher = pattern.matcher(user.getEmail());
                if (!matcher.find()) {
                    return false;
                }

                try {
                    MailService mailService = new MailService();
                    mailService.sendMail(user.getEmail(), welcomeToServerEmailSubject, welcomeToServerEmailText);
                } catch (Exception e) {
                    System.out.println("Email yandex error (normal, don't panic in FileHandler). The problem is in yandex-services: they cannot be activated without user's activity. See ya.cc in exception.printStackTrace");
                }

                try {
                    userRepository.save(user);
                } catch (Exception e) {
                    return false;
                }


                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File is NOT opened correctly");
        }

        return true;
    }
}
