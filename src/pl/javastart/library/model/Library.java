package pl.javastart.library.model;

import pl.javastart.library.exceptions.PublicationAlreadyExistsException;
import pl.javastart.library.exceptions.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
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
