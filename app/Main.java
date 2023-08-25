package app;

import Model.Film;
import archive.FilmParser;

class Main {

    static Film breakingTheLine(String line) {
        Film film = new Film();
        line = FilmParser.setId(line, film);
        line = FilmParser.setType(line, film);
        line = FilmParser.setTitle(line, film);
        line = FilmParser.setDirectior(line, film);
        line = FilmParser.setCast(line, film);
        line = FilmParser.setCountry(line, film);
        line = FilmParser.setDate(line, film);
        line = FilmParser.setRating(line, film);
        line = FilmParser.setTimeDuration(line, film);
        film.setList();
        // System.out.println(line);
        return film;
    }

    public static void main(String[] args) {

        String csvFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/netflix_titles.csv";
        String[] lines = FilmParser.getLines(csvFilePath);

        for (int i = 0; i < lines.length; i++) {
            Film film = breakingTheLine(lines[i]);
            System.out.println(film.toString());
            
        }
    }
}