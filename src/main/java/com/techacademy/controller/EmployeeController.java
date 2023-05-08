package com.techacademy.controller;

import java.time.LocalDateTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techacademy.entity.Employee;
import com.techacademy.service.AuthenticationService;
import com.techacademy.service.EmployeeService;
import com.techacademy.service.UserDetail;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService service;
    private final AuthenticationService authenticationService;

    public EmployeeController(EmployeeService service, AuthenticationService authenticationService) {
        this.service = service;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        //全件検索結果をModelに格納
        model.addAttribute("employeelist", service.getEmployeeList());
        model.addAttribute("username", userDetail.loginName());
        // employee/list.htmlに画面遷移
        return "employee/list";
    }

    /** Employee登録画面を表示*/
    @GetMapping("/register")
    public String getRegister(@ModelAttribute Employee employee, @AuthenticationPrincipal UserDetail userDetail, Model model) {

        model.addAttribute("username", userDetail.loginName());
        // employee登録画面に遷移
        return "employee/register";
    }

    /** Employee登録処理*/
    @PostMapping("/register")
    public String postRegister(Employee employee, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        LocalDateTime now = LocalDateTime.now();
        employee.getAuthentication().setEmployee(employee);

        // 社員番号のDB重複を確認
        if(authenticationService.existCode(employee.getAuthentication().getCode())) {
            return getRegister(employee, userDetail, model);
        }

        employee.setDeleteFlag(0);
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面のリダイレクト
        return "redirect:/employee/list";
    }

    /** Employeeの詳細画面の表示*/
    @GetMapping("/detail/{id}/")
    public String getDetail(@PathVariable("id") Integer id,@AuthenticationPrincipal UserDetail userDetail, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        model.addAttribute("username", userDetail.loginName());
        // employeeの詳細画面に遷移
        return "employee/detail";
    }

    /** Employeeの更新画面の表示*/
    @GetMapping("update/{id}")
    public String getEmployee(@PathVariable("id") Integer id, @AuthenticationPrincipal UserDetail userDetail, Model model) {
        // Modelに登録
        model.addAttribute("employee", service.getEmployee(id));
        model.addAttribute("username", userDetail.loginName());
        // employeeの詳細画面に遷移
        return "employee/update";
    }

    /** Employee更新処理*/
    @PostMapping("update/{id}")
    public String postEmployee(Employee employee) {
        LocalDateTime now = LocalDateTime.now();
        employee.getAuthentication().setEmployee(employee);

        service.getEmployee(employee.getId());
        employee.setDeleteFlag(0);
        employee.setUpdatedAt(now);
        // Employee登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }

    /** Employee削除処理*/
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        Employee employee = service.getEmployee(id);
        LocalDateTime now = LocalDateTime.now();

        employee.setDeleteFlag(1);
        employee.setUpdatedAt(now);
        service.saveEmployee(employee);

        // 一覧画面にリダイレクト
        return "redirect:/employee/list";

//      Employeeを削除
//      service.deleteEmployee(id);
    }
}
