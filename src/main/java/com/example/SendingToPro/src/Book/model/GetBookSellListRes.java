package com.example.SendingToPro.src.Book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor


public class GetBookSellListRes {
    private int sellId;
    private int studId;
    private String contact;
    private String bookState;
    private String sellState;
    private int sellPrice;
    private String sellorText;
    private String publisher;
    private String bookName;
    private String author;
    private String bookEdition;
}
