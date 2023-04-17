package com.techacademy.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    /** 主キー。自動生成*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**名前。20桁。null不許可*/
    @Column(length = 20, nullable = false)
    private String name;

    /** 登録日時。null不許可*/
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /** 更新日時。null不許可*/
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /** 削除フラグ。null不許可*/
    @Column(nullable = false)
    private Integer deleteFlag;

    @OneToOne(mappedBy="employee")
    private Authentication authentication;
}
