package inCompleteLib.services;

import inCompleteLib.models.Book;
import inCompleteLib.models.Library;
import inCompleteLib.models.Rack;

import java.util.function.Predicate;

public class LibraryServiceImpl implements ILibraryService {
    @Override
    public int addBook(Book toAdd, Library library) {
        for (Rack rack : library.getRacks()) {
            if (rack.getBook() == null) {
                rack.setBook(toAdd);
                return rack.getNumber();
            }
        }
        return -1;
    }

    @Override
    public int removeBookByPredicate(Predicate<Book> copyIdPredicate, Library library) {
        for (Rack rack : library.getRacks()) {
            if (rack != null && copyIdPredicate.test(rack.getBook())) {
                rack.setBook(null);
                return rack.getNumber();
            }
        }
        return -1;
    }

    @Override
    public Rack removeAndGetRackByPredicate(Predicate<Book> copyIdPredicate, Library library) {
        for (Rack rack : library.getRacks()) {
            if (rack != null && copyIdPredicate.test(rack.getBook())) {
                Rack toReturn = new Rack(rack.getNumber(), rack.getBook());
                rack.setBook(null);
                return toReturn;
            }
        }
        return null;
    }
}
