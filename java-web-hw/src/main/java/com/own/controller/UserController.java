package com.own.controller;

import com.own.model.User;
import com.own.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/users")
//    public List<User> retrieveAllUsers() {
//        return userRepository.findAll();
//    }

    @GetMapping("/appform")
    public String userSubmit(Model model) {
        model.addAttribute("userModel", new User());
        return "applicationForm";
    }

    @PostMapping("/appform")
    public String userSubmit(@ModelAttribute @Valid User user, BindingResult bindingResult/*, HttpServletResponse response*/) {
        if (bindingResult.hasErrors()) {
            return "errorNotify";
        }
        userRepository.save(user);
        System.out.println(user);

        return "result";
    }

    @GetMapping("/appform-search")
    public String userSearch(Model model) {
        model.addAttribute("userModel", new User());
        return "userSearch";
    }

    @PostMapping("/appform-search")
    public String userSearch(@ModelAttribute User user/*, HttpServletResponse response*/) { //TODO: response --> user-agent
        List<User> userList = userRepository.retrieveByFirstLastName(user.getFirstName(), user.getLastName());
        if (userList.isEmpty()) {
            return "userNotFound";
        }
        for (User u : userList) {
            System.out.println(u);
        }

        return "userFound";
    }

//    @PostMapping("/appform")
//    public Contact createContact(@RequestBody Contact contact) {
//        return contactRepository.save(contact);
//    }


}
