/*
 * RockBand.java
 *
 * Created on 2018-02-27, 7:18
 */
package com.marcnuri.spring.mongo.customrepository.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
@Document(collection = "books")
public class Book {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	@Id
	private String id;
	private String title;
	private String isbn;
	private List<String> authorNames;
	private Date publishDate;
	private List<String> subjects;

//**************************************************************************************************
//  Constructors
//**************************************************************************************************

//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************

//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (id != null ? !id.equals(book.id) : book.id != null) return false;
		if (title != null ? !title.equals(book.title) : book.title != null) return false;
		if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
		if (authorNames != null ? !authorNames.equals(book.authorNames) : book.authorNames != null) return false;
		if (publishDate != null ? !publishDate.equals(book.publishDate) : book.publishDate != null) return false;
		return subjects != null ? subjects.equals(book.subjects) : book.subjects == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
		result = 31 * result + (authorNames != null ? authorNames.hashCode() : 0);
		result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
		result = 31 * result + (subjects != null ? subjects.hashCode() : 0);
		return result;
	}

//**************************************************************************************************
//  Other Methods
//**************************************************************************************************

//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

//**************************************************************************************************
//  Static Methods
//**************************************************************************************************

//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************

}
