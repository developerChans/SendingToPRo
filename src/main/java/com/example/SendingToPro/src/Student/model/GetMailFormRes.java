package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetMailFormRes { // 과제 인지 질문인지 구분하는거 필요
    private String emailForm;
}
