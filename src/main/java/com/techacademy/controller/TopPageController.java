package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class TopPageController {

    @GetMapping("/")
    public String getTop(@AuthenticationPrincipal UserDetails employee, Model model) {
        model.addAttribute("username", employee.getUsername());
        // topPage.htmlに画面遷移
        return "topPage";
    }
}
