package app;

import java.io.IOException;

import Model.Film;
import archive.Crud;
import archive.FilmParser;

class Main {

    public static void main(String[] args) throws IOException {
        Crud crud = new Crud();
        FilmParser.start();

        Film film = new Film(8449, "Igor O criador", "Movie", 9999999, "R,90 min");
        crud.update(film);
        System.out.println(crud.delete(8449));
        System.out.println(crud.sequencialSearch(8449));

    }
}