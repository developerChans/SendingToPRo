package com.example.SendingToPro.src.Professor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostClassNoticeImgReq {
    private int boardId;
    private String imageUrl;
}
