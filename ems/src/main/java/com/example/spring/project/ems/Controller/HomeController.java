package com.example.spring.project.ems.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToRegister() {
        return "redirect:/registration.html";
    }
}
