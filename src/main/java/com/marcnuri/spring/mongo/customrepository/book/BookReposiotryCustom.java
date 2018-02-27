package com.marcnuri.spring.mongo.customrepository.book;

import java.util.List;

/**
 * Created by Marc Nuri <marc@marcnuri.com> on 2018-02-27.
 */
public interface BookReposiotryCustom {

	List<Book> query(DynamicQuery dynamicQuery);

}
