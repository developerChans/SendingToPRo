package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class PatchMailFormReq {
    private String emailForm;
    private int lecId;
    private int studId;

}
