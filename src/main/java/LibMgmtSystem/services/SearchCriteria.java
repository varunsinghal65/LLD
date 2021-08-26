package LibMgmtSystem.services;

import LibMgmtSystem.models.Book;

public interface SearchCriteria {

	boolean isMatch(Book book, String bookAttribute);
	
}
