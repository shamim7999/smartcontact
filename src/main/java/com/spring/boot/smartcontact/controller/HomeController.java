package com.spring.boot.smartcontact.controller;

import com.spring.boot.smartcontact.dao.UserRepository;
import com.spring.boot.smartcontact.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        User user = new User();
        user.setName("shamim");
        user.setEmail("shmaim@gmail.com");
        this.userRepository.save(user);
        return "Test working fine";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home - Smart Contact");
        return "home";
    }

}
