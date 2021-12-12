package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PatchStudentDataReq {
    private String studName;
    private String studPhoneNum;
    private String password;
}
