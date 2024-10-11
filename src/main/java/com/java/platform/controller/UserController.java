package com.java.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.platform.model.User;
import com.java.platform.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/agents")
public class UserController 
{
    private @Autowired UserService userService;

    
    // LIST
//    @GetMapping()
//    public String index(Model model) 
//    {
//        model.addAttribute("agents", userService.findAll());
//        return "agents/index";
//    }

    
    // CREATE
//    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("user", new User());
//        return "agents/create"; // nome della vista
//    }
//
//    @PostMapping("/create")
//    public String store(@Valid @ModelAttribute("user") User user, 
//    						BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "agents/create"; 
//        }
//        userService.create(user);
//        return "redirect:/agents"; 
//    }
}
