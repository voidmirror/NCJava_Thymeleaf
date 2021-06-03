package com.own.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


import com.own.handler.FileHandler;
import com.own.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
//
//    @RequestMapping(value="/upload", method=RequestMethod.GET)
//    public @ResponseBody String provideUploadInfo() {
//        return "Вы можете загружать файл с использованием того же URL.";
//    }
//
//    @RequestMapping(value="/upload", method=RequestMethod.POST)
//    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
//                                                 @RequestParam("file") MultipartFile file){
//        FileHandler fileHandler = new FileHandler(name, file);
//        return fileHandler.proceedFile();
//    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/upload")
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL. (Recommendation: use Postman)";
    }

    @PostMapping(value="/upload")
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        FileHandler fileHandler = new FileHandler(name, file, userRepository);
        return fileHandler.proceedFile();
    }


}