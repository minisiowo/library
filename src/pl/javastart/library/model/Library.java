package pl.javastart.library.model;

import pl.javastart.library.exceptions.PublicationAlreadyExistsException;
import pl.javastart.library.exceptions.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator) {
        // creating list to get access to sort()
        ArrayList<Publication> list = new ArrayList<>(publications.values());
        list.sort(comparator);
        return list;
    }

    public Optional<Publication> findPublicationByTitle(String title) {
        return Optional.ofNullable(publications.get(title));
    }

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator) {
        ArrayList<LibraryUser> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle())) {
            throw new PublicationAlreadyExistsException("Publikacja o takim tytule już istnieje.");
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser libraryUser) {
        if (users.containsKey(libraryUser.getPesel())) {
            throw new UserAlreadyExistsException("Użytkownik o takim peselu już istnieje.");
        }
        users.put(libraryUser.getPesel(), libraryUser);
    }

    public boolean removePublication(Publication publication) {
        if (publications.containsValue(publication)) {
            publications.remove(publication.getTitle());
            return true;
        } else {
            return false;
        }
    }
}
