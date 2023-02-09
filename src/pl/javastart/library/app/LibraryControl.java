package pl.javastart.library.app;

import pl.javastart.library.exceptions.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();

    public void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADD_BOOK -> addBook();
                case PRINT_BOOKS -> printBooks();
                case ADD_MAGAZINE -> addMagazine();
                case PRINT_MAGAZINES -> printMagazines();
                case EXIT -> exit();
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false; // flaga pozwalająca zapętlić program
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException ex) {
                printer.printLine(ex.getMessage());
            } catch (InputMismatchException ex) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie: ");
            }
        }
        return option;
    }

    private void printOptions() {
        printer.printLine("Wybierz opcję: ");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
        } catch (InputMismatchException ex) {
            printer.printLine("Nie utworzono książki ze względu na błędne dane.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            printer.printLine("Limit biblioteki przekroczony. Nie można dodać nowej książki.");
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);
        } catch (InputMismatchException ex) {
            printer.printLine("Nie utworzono magazynu ze względu na błędne dane.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            printer.printLine("Limit biblioteki przekroczony. Nie można dodać nowego magazynu.");
        }
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications());
    }

    private void exit() {
        printer.printLine("Koniec programu.");
        // close the output stream
        dataReader.close();
    }
}
