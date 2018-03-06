/*
 * DynamicQuery.java
 *
 * Created on 2018-02-27, 7:29
 */
package com.marcnuri.spring.mongo.customrepository.book;

import java.util.Date;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
public class DynamicQuery {

//**************************************************************************************************
//  Fields
//**************************************************************************************************
	private String authorNameLike;
	private Date publishDateBefore;
	private Date publishDateAfter;
	private String subject;

//**************************************************************************************************
//  Constructors
//**************************************************************************************************

//**************************************************************************************************
//  Abstract Methods
//**************************************************************************************************

//**************************************************************************************************
//  Overridden Methods
//**************************************************************************************************

//**************************************************************************************************
//  Other Methods
//**************************************************************************************************

//**************************************************************************************************
//  Getter/Setter Methods
//**************************************************************************************************
	public String getAuthorNameLike() {
		return authorNameLike;
	}

	public void setAuthorNameLike(String authorNameLike) {
		this.authorNameLike = authorNameLike;
	}

	public Date getPublishDateBefore() {
		return publishDateBefore;
	}

	public void setPublishDateBefore(Date publishDateBefore) {
		this.publishDateBefore = publishDateBefore;
	}

	public Date getPublishDateAfter() {
		return publishDateAfter;
	}

	public void setPublishDateAfter(Date publishDateAfter) {
		this.publishDateAfter = publishDateAfter;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
//**************************************************************************************************
//  Static Methods
//**************************************************************************************************

//**************************************************************************************************
//  Inner Classes
//**************************************************************************************************

}
