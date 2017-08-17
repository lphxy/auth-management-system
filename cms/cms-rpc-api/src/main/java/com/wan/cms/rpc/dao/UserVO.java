package com.wan.cms.rpc.dao;

import com.wan.cms.dao.model.Book;
import com.wan.cms.dao.model.User;

import java.io.Serializable;
import java.util.List;

public class UserVO extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Book> books;

	public List<Book> getBooks() {
        return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}