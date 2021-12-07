package com.example.SendingToPro.src.Book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class GetLectureBookDataRes {
    private int bookId;
    private String bookName;
    private String author;
    private int bookPrice;
    private String bookEdition;
    private String bookImageUrl;
}
