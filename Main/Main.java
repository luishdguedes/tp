package Main;

//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
import java.io.FileReader;

import Model.Film;

import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.FileInputStream;

class Main {

    static String[] getLines(String csvFilePath) {
        String[] lines = new String[8807];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            int index = 0;
            reader.readLine();
            while ((lines[index] = reader.readLine()) != null) {
                index++;
            }
            reader.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lines;
    }

    static String setId(String line, Film film) {
        int index = 0;
        String holdString = "";
        while (line.charAt(index) != ',') {
            holdString += line.charAt(index);
            index++;
        }
        film.setId(Integer.parseInt(holdString));
        return line = line.substring(index + 1, line.length());
    }

    static String setType(String line, Film film) {
        int index = 0;
        String holdString = "";
        while (line.charAt(index) != ',') {
            holdString += line.charAt(index);
            index++;
        }
        film.setType(holdString);
        return line = line.substring(index + 1, line.length());
    }

    static String setTitle(String line, Film film) {
        int index = 0;
        String holdString = "";
        if (line.charAt(index) == '\"') {
            index++;
            while (line.charAt(index) != '\"') {
                holdString += line.charAt(index);
                index++;
            }
        } else {
            while (line.charAt(index) != ',') {
                holdString += line.charAt(index);
                index++;
            }
        }
        film.setTitle(holdString);
        return line = line.substring(index + 1, line.length());
    }

    static String setDirectior(String line, Film film) {
        int index = 0;
        String holdString = "";
        while (line.charAt(index) != ',') {
            holdString += line.charAt(index);
            index++;

        }
        if (holdString == "") {
            film.setDirector("Unknown");
            return line = line.substring(index + 1, line.length());
        }
        film.setDirector(holdString);
        return line = line.substring(index + 1, line.length());

    }

    static void breakingTheLine(String line) {
        Film film = new Film();
        line = setId(line, film);
        line = setType(line, film);
        line = setTitle(line, film);
        line = setDirectior(line, film);
        System.out.println(film.getDirector());
    }

    public static void main(String[] args) {

        String csvFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/netflix_titles.csv";
        String[] lines = getLines(csvFilePath);

        for (int i = 0; i < lines.length; i++) {
            breakingTheLine(lines[i]);
        }
    }
}