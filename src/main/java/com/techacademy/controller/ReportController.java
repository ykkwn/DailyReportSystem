package com.techacademy.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.service.ReportService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/report")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }


    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetail userDetail, Model model) {

      //全件検索結果をModelに格納
        model.addAttribute("reportlist", service.getReportList());
        model.addAttribute("username", userDetail.loginName());

        return "report/list";
    }

    // Reportの新規作成
    @GetMapping("/register")
    public String getRegister(Report report, @AuthenticationPrincipal UserDetail userDetail, Model model) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(dtf);

        model.addAttribute("date", date);
        model.addAttribute("username", userDetail.loginName());
        // report登録画面に遷移
        return "report/register";
    }

    @PostMapping("/register")
    public String postRegister(Report report, @AuthenticationPrincipal UserDetail userDetail) {
        LocalDateTime now = LocalDateTime.now();

        report.setEmployee(userDetail.getUser());
        report.setCreatedAt(now);
        report.setUpdatedAt(now);
        service.saveReport(report);

        return "redirect:/report/list";
    }

    @GetMapping("/detail/{id}")
    public String getDetail(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail userDetail, Model model) {

        model.addAttribute("report", service.getReport(id));
        model.addAttribute("username", userDetail.loginName());

        return "report/detail";
    }

    /** Employeeの更新画面の表示*/
    @GetMapping("update/{id}")
    public String getUpdate(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        // Modelに登録
        model.addAttribute("report", service.getReport(id));
        model.addAttribute("username", userDetail.loginName());
        // employeeの詳細画面に遷移
        return "report/update";
    }

    @PostMapping("update/{id}")
    public String postUpdate(Report report, @AuthenticationPrincipal UserDetail userDetail) {
        LocalDateTime now = LocalDateTime.now();
        Employee employee = userDetail.getUser();

        report.setEmployee(employee);
        report.setCreatedAt(now);
        report.setUpdatedAt(now);
        service.saveReport(report);
        return "redirect:/report/list";
    }

}
