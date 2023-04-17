package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 主キー。社員番号。20桁。null不許可*/
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private String code;

    /** パスワード。255桁。null不許可*/
    @Column(length = 255, nullable = false)
    private String password;

    /** 権限。10桁。null不許可*/
    @Column(length = 10, nullable = false)
    private String role;

    /** 従業員テーブルのID（FK）*/
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}
