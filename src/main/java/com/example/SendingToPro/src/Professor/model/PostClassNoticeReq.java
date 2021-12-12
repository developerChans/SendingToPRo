package com.example.SendingToPro.src.Professor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostClassNoticeReq {
    private int profId;
    private int lecId;
    private String title;
    private String content;
    private String imageUrl;
}
