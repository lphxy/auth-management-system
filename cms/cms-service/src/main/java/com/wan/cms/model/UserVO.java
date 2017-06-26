package com.wan.cms.model;

import java.util.List;

/**
 * Created by w1992wishes on 2017/6/26.
 */
public class UserVO extends User {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
