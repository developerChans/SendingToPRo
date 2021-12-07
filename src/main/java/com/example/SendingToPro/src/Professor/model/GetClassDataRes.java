package com.example.SendingToPro.src.Professor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetClassDataRes {
    private String profName;
    private String email;
    private String profPhoneNum;
    private String subjectName;

}
