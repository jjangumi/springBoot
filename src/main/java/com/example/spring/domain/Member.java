package com.example.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//db가 생성해주는것은 IDENTITY이다.
    private Long id; //시스템이 알아볼 식별자

    //db와 컬럼명이 같으니 따로 설정할 필요없지만 만약 다르다면 @Column(name="username")이라고 매핑 해주기
    private String name; //이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
