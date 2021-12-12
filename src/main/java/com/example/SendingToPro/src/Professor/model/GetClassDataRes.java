package com.example.SendingToPro.src.Professor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetClassDataRes {
    private String profName;
    private String profPhoneNum;
    private String profEmail;
    private String subjectName;
    private String bookImageUrl;
    private String bookName;
    private String author;
    private String bookEdition;
    private int bookPrice;
    private int lecId;
    private int bookId;

}
