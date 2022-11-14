package pl.javastart.library.app;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;

public class LibraryApp {
    public static void main(String[] args){
        final String appName = "Biblioteka v0.9";
        System.out.println(appName);
        LibraryControl libControl = new LibraryControl();
        libControl.controlLoop();
    }
}   