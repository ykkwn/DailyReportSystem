package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.entity.Report;
import com.techacademy.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    /** 全件検索 */
    public List<Report> getReportList() {
        return repository.findAll();
    }
    /** 特定のユーザーの一覧検索 */
    public List<Report> getReportList(Employee employee) {
        return repository.findByEmployee(employee);
    }

    /** 一件を検索して返す */
    public Report getReport(Integer id) {
        return repository.findById(id).get();
    }

    /** 従業員の登録 */
    @Transactional
    public Report saveReport(Report report) {
        return repository.save(report);
    }

}
