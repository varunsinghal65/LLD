package LibMgmtSystem.services;

public class SearchImplementations {

	private SearchImplementations() {
	}

	public static final SearchCriteria BY_ID = (book, inputId) -> book.getId().equals(inputId);

	public static final SearchCriteria BY_AUTHOR = (book, inputAuthor) -> book.getAuthors().contains(inputAuthor);

	public static final SearchCriteria BY_PUBLISHER = (book, inputPublisher) -> book.getPublishers()
			.contains(inputPublisher);
}
