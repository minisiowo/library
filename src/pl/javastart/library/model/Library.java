package pl.javastart.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {

    private static final int INITIAL_CAPACITY = 1;
    private Publication[] publications = new Publication[INITIAL_CAPACITY];
    private int publicationsNumber; // initialized with 0 automatically

    public Publication[] getPublications() {
        Publication[] result = new Publication[publicationsNumber];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addPublication(Publication publication) {
        if (publicationsNumber == publications.length) {
            publications = Arrays.copyOf(publications, publications.length * 2);
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;
    }

    public boolean removePublication(Publication publication) {
        final int notFound = -1;
        int found = notFound;
        int i = 0;
        while (i < publicationsNumber && found == notFound) {
            if (publications[i].equals(publication)) {
                found = i;
            } else {
                i++;
            }
        }
        if (found != notFound) {
            System.arraycopy(publications, found + 1, publications, found, publications.length - found - 1);
            publicationsNumber--;
            publications[publicationsNumber] = null;
        }
        return found != notFound;
    }
}
