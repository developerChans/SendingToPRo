package com.example.SendingToPro.src.Book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PostBookSellReq {
    private int bookId;
    private int studId;
    private int bookState;
    private int sellPrice;
    private int sellState;
    private String sellorText;
}
