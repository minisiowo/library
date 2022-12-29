package pl.javastart.library.model;

public class Library {

    private static final int MAX_PUBLICATIONS = 2000;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];
    private int publicationsNumber; // initialized with 0 automatically

    public void addBook(Book book) {
        if (publicationsNumber < MAX_PUBLICATIONS) {
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("Maksymalna liczba książek została osiągnięta.");
        }
    }

    public void printBooks() {
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book) {
                System.out.println(publications[i]);
                countBooks++;
            }
        }
        if (countBooks == 0) {
            System.out.println("Brak książek w bibliotece.");
        }
    }

    public void addMagazine(Magazine magazine) {
        int countMagazines = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine) {
                System.out.println(publications[i]);
                countMagazines++;
            }
        }
        if (countMagazines == 0) {
            System.out.println("Maksymalna liczba magazynów została osiągnięta.");
        }
    }

    public void printMagazines() {
        if (publicationsNumber == 0) {
            System.out.println("Brak magazynów w bibliotece.");
        }
        for (int i = 0; i < publicationsNumber; i++) {
            System.out.println(publications[i]);
        }
    }
}
