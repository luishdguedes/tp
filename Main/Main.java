package Main;

//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
import Model.Film;

//import java.io.DataInputStream;
//import java.io.FileInputStream;

class Main {

    static void breakingTheLine(String line) {
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
        // System.out.println(line);
        System.out.println(film.getTimeDuration());
    }

    public static void main(String[] args) {

        String csvFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/netflix_titles.csv";
        String[] lines = FilmParser.getLines(csvFilePath);

        for (int i = 0; i < lines.length; i++) {
            breakingTheLine(lines[i]);
        }
    }
}