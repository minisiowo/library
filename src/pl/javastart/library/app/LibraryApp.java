package pl.javastart.library.app;

import pl.javastart.library.io.ConsolePrinter;

public class LibraryApp {
    private static final String APP_NAME = "Biblioteka v2.0";

    public static void main(String[] args){
        System.out.println(APP_NAME);
        LibraryControl libControl = new LibraryControl();
        libControl.controlLoop();
    }
}