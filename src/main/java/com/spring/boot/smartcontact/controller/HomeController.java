package com.spring.boot.smartcontact.controller;

import com.spring.boot.smartcontact.dao.UserRepository;
import com.spring.boot.smartcontact.helper.Message;
import com.spring.boot.smartcontact.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String dispatch() {
        return "home";
    }

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

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("title", "Sign Up - Smart Contact");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ("user") User user,
                               @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session) {

        try {
            if(!agreement) {
                System.out.println("You haven't agreed to our terms and conditions.");
                throw new Exception("You haven't agreed to our terms and conditions.");
            }
            user.setEnabled(true);
            user.setRole("ROLE_USER");
            user.setImageUrl("default.png");
            System.out.println("Agreement: "+agreement);
            System.out.println("User: "+user);

            this.userRepository.save(user);

            model.addAttribute("user", new User());

            session.setAttribute("message", new Message("Somuccessfully Registered.. !! ",
                    "alert-success !!"));

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong.. !! "+e.getMessage(),
                    "alert-danger !!"));
        }
        finally {
            return "signup";
        }
    }
}
