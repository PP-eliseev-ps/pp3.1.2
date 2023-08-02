package com.example.spring_boot.controller;

import com.example.spring_boot.model.User;
import com.example.spring_boot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String showAllUsers(Model model) {
        List<User> allUsers = userService.findAllUser();
        model.addAttribute("allUsers", allUsers);
        return "admin/all-users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/user-save-update";
    }

    @GetMapping(value = "/update")
    public String updateUser(@RequestParam Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin/user-save-update";
    }

    @DeleteMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @PatchMapping(value = "/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/user-save-update";
        } else {
            userService.save(user);
            return "redirect:/admin/users";
        }
    }
}
