package com.example.SendingToPro.src.Book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class GetBookSellListRes {
    private int studId;
    private String bookName;
    private String author;
    private String bookEdition;
    private int bookPrice;
    private String bookState;
    private String contact;
    private String sellorText;
    private String sellState;
}
