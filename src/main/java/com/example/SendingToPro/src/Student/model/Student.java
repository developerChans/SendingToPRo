package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Student {
    private int studId;
    private String studName;
    private String studPhoneNum;
    private String email;
    private String password;
}
