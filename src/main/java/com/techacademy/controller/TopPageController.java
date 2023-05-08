package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.UserDetail;


@Controller
@RequestMapping()
public class TopPageController {

    @GetMapping("/")
    public String getTop(@AuthenticationPrincipal UserDetail employee, Model model) {
        model.addAttribute("username", employee.loginName());
        // topPage.htmlに画面遷移
        return "topPage";
    }
}
