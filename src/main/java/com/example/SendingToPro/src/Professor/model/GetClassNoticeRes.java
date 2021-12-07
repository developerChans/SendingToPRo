package com.example.SendingToPro.src.Professor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetClassNoticeRes {
    private String profName;
    private int boardId;
    private String title;
    private String subjectName;
    private String content;
    private String ImageUrl;
}
