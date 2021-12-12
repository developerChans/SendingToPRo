package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostStudentReq {
    private String studName;
    private String studPhoneNum;
    private String studEmail;
    private String password;
}
