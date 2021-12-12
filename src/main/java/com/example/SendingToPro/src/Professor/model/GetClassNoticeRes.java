package com.example.SendingToPro.src.Professor.model;

import java.sql.Date;
import java.sql.Timestamp;

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
    private Timestamp createdAt;
}
