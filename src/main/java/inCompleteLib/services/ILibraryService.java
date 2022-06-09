package inCompleteLib.services;

import inCompleteLib.models.Book;
import inCompleteLib.models.Library;
import inCompleteLib.models.Rack;

import java.util.function.Predicate;

public interface ILibraryService {

    int addBook(Book toAdd, Library library);

    int removeBookByPredicate(Predicate<Book> copyIdPredicate, Library library);

    Rack removeAndGetRackByPredicate(Predicate<Book> copyIdPredicate, Library library);
}
