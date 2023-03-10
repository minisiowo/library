package pl.javastart.library.app;

import pl.javastart.library.exceptions.DataExportException;
import pl.javastart.library.exceptions.DataImportException;
import pl.javastart.library.exceptions.InvalidDataException;
import pl.javastart.library.exceptions.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManager;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.InputMismatchException;

public class LibraryControl {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowane dane z pliku");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }
    }

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
                default -> printer.printLine("Nie ma takiej opcji, wprowadź ponownie:");
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
            library.addPublication(book);
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
            library.addPublication(magazine);
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
        try {
            fileManager.exportData(library);
            printer.printLine("Export do pliku zakończony powodzeniem.");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu.");
        // close the output stream
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "wyjście z programu"),
        ADD_BOOK(1, "dodanie nowej książki"),
        ADD_MAGAZINE(2, "dodanie nowego magazynu"),
        PRINT_BOOKS(3, "wyświetl dostępne książki"),
        PRINT_MAGAZINES(4, "wyświetl dostępne magazyny");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new NoSuchOptionException("Brak opcji o numerze " + option + ". Wprowadź ponownie: ");
            }
        }
    }
}
