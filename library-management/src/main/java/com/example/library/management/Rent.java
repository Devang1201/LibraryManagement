package com.example.library.management;

import javax.persistence.*;

@Entity
public class Rent {
	@Id
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "book_id")
	private int book_id;

	public Rent(int a, int b) {
		this.user_id = a;
		this.book_id = b;
	}
	
	public Rent() {}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
}
