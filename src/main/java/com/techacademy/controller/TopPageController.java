package com.techacademy.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;


@Controller
@RequestMapping()
public class TopPageController {

    private final ReportService service;

    public TopPageController(ReportService service) {
        this.service = service;
    }


    @GetMapping("/")
    public String getTop(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        model.addAttribute("reportlist", service.getReportList(userDetail.getUser()));
        model.addAttribute("username", userDetail.loginName());
        model.addAttribute("userrole", userDetail.getUserRole());
        // topPage.htmlに画面遷移
        return "topPage";
    }
}
