package com.wan.cms.service.model;

import com.wan.cms.dao.model.Book;
import com.wan.cms.dao.model.User;

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
