package pl.javastart.library.io;

import pl.javastart.library.model.*;

import java.util.Collection;

public class ConsolePrinter {

    public void printBooks(Collection<Publication> publications) {
        long countBooks = publications.stream()
                .filter(publication -> publication instanceof Book)
                .peek(publication -> printLine(publication.toString()))
                .count();

        if (countBooks == 0) {
            printLine("Brak książek w bibliotece.");
        }
    }

    public void printMagazines(Collection<Publication> publications) {
        long countMagazines = publications.stream()
                .filter(publication -> publication instanceof Magazine)
                .peek(publication -> printLine(publication.toString()))
                .count();

        if (countMagazines == 0) {
            printLine("Brak magazynów w bibliotece.");
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);
    }

    public void printLine(String string) {
        System.out.println(string);
    }

}
