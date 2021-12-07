package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class PostLoginReq {
    private String email;
    private int password;
}
