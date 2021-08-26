package LibMgmtSystem.services;

public class SearchCriteriaFactory {

	public static SearchCriteria getInstance(String criteriaType) {
		switch (criteriaType) {
		case "book_id":
			return SearchImplementations.BY_ID;
		case "author_id":
			return SearchImplementations.BY_AUTHOR;
		case "publisher":
			return SearchImplementations.BY_PUBLISHER;
		default:
			throw new IllegalArgumentException("criteria not found for search strategy");
		}
	}

}
