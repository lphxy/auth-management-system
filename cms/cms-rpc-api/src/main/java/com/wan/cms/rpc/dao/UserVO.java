package com.wan.cms.rpc.dao;

import com.wan.cms.dao.model.CmsBook;
import com.wan.cms.dao.model.CmsUser;

import java.io.Serializable;
import java.util.List;

public class UserVO extends CmsUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CmsBook> books;

	public List<CmsBook> getBooks() {
        return books;
	}

	public void setBooks(List<CmsBook> books) {
		this.books = books;
	}
}