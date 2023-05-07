package com.techacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class TopPageController {

    @GetMapping("/")
    public String getTop() {
        // topPage.htmlに画面遷移
        return "topPage";
    }
}
