package com.techacademy.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**全件検索をして返す*/
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAll()を呼び出す
        return repository.findAll();
    }

    /** 一件を検索して返す*/
    public Employee getEmployee(Integer id) {
//        // findByIdで検索
//        Optional<Employee> optional = repository.findById(id);
//        // 取得出来なかった時はnullを返す
//        Employee employee = optional.orElse(null);
//        return employee;
        return repository.findById(id).get();
    }

    /** 従業員の登録を行う*/
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    /** 従業員の削除*/
    @Transactional
    public void deleteEmployee(Integer id) {
        repository.deleteById(id);
    }
}
