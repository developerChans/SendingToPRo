package com.example.SendingToPro.src.Student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetStudentLectureRes {
    private int lecId;
    private String profName;
    private String subjectName;    
}
