package inCompleteLib.models;

import java.util.function.Predicate;

public class BookAttributePredicateFactory {

    public static Predicate<Book> getPredicate(AttributeEnum attributeEnum, String attributeValue) {
        switch (attributeEnum) {
            case book_id:
                return book -> book != null && book.getBookId().equals(attributeValue);
            case author_id:
                return book -> book != null && book.getAuthors().contains(attributeValue);
            case publisher_id:
                return book -> book != null && book.getPublishers().contains(attributeValue);
        }
        return null;
    }

}

