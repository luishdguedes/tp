package app;

import java.io.IOException;

import archive.FilmParser;
import archive.Crud;

class Main {

    public static void main(String[] args) throws IOException {
        Crud crud = new Crud();
        FilmParser.start();
        // crud.readAll();
        System.out.println(crud.sequencialSearch(8007).toString());

    }
}