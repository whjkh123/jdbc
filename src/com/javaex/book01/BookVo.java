package com.javaex.book01;

public class BookVo extends AuthorVo {

	public int book_id;
	public String title;
	public String pubs;
	public String pub_date;
	public int author_id;

	public BookVo(int book_id, String title) {
		super();
		this.book_id = book_id;
		this.title = title;
	}

	public BookVo(String title, String pubs, String pub_date, int author_id) {
		super();
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
	}

	public BookVo(int book_id, String title, String pubs, String pub_date, int author_id) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
	}

	public BookVo(int book_id, String title, String pubs, String pub_date, int author_id, String authorName, String authorDesc) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

}
