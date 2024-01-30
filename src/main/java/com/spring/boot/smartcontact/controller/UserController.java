package com.spring.boot.smartcontact.controller;

import com.spring.boot.smartcontact.dao.UserRepository;
import com.spring.boot.smartcontact.model.Contact;
import com.spring.boot.smartcontact.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonAttribute(Model model, Principal principal) {
        User user = this.userRepository.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
    }

    @GetMapping("/index")
    public String dashBoard(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "normal/user_dashboard";
    }

    @GetMapping("/add-contact")
    public String dashBoard(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("title", "Add Contact");
        return "normal/add_contact_form";
    }
}
