package Main;

//import java.io.DataOutputStream;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Film;

import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.FileInputStream;

class Main {

    static String[] getLines(String csvFilePath) {
        String[] lines = new String[8450];
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
            film.setTitle(holdString);
            return line = line.substring(index + 2, line.length());
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
        if (line.charAt(index) == '\"') {
            index++;
            while (line.charAt(index) != '\"') {
                holdString += line.charAt(index);
                index++;
            }
            film.setDirector(holdString);
            return line = line.substring(index + 2, line.length());
        } else {
            while (line.charAt(index) != ',') {
                holdString += line.charAt(index);
                index++;
            }
        }
        if (holdString == "") {
            film.setDirector("Unknown");
            return line = line.substring(index + 1, line.length());
        }
        film.setDirector(holdString);
        return line = line.substring(index + 1, line.length());

    }

    static String setCast(String line, Film film) {
        int index = 0;
        String holdString = "";
        if (line.charAt(index) == '\"') {
            index++;
            while (line.charAt(index) != '\"') {
                holdString += line.charAt(index);
                index++;
            }

            film.setCast(holdString);
            return line = line.substring(index + 2, line.length());
        }
        while (line.charAt(index) != ',') {
            holdString += line.charAt(index);
            index++;
        }
        if (holdString == "") {
            film.setCast("Unknown");
            return line = line.substring(index + 1, line.length());
        }
        film.setCast(holdString);
        return line = line.substring(index + 1, line.length());
    }

    static String setCountry(String line, Film film) {
        int index = 0;
        String holdString = "";
        if (line.charAt(index) == '\"') {
            index++;
            while (line.charAt(index) != '\"') {
                holdString += line.charAt(index);
                index++;
            }
            film.setCountry(holdString);
            return line = line.substring(index + 2, line.length());
        }
        while (line.charAt(index) != ',') {
            holdString += line.charAt(index);
            index++;
        }
        if (holdString == "") {
            film.setCountry("Unknown");
        }
        film.setCountry(holdString);
        return line = line.substring(index + 1, line.length());
    }

    public static String setDate(String line, Film film) {
        int index = 1;
        StringBuilder holdString = new StringBuilder();
        if (line.charAt(index) == ' ') {
            index++;
        }
        if (line.charAt(0) == ',') {
            film.setDateAdded(null);
            return line.substring(index + 1);
        }
        while (index < line.length() && line.charAt(index) != '\"') {
            holdString.append(line.charAt(index));
            index++;
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy");
        Date date;
        try {
            date = inputFormat.parse(holdString.toString());
            film.setDateAdded(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (index + 2 < line.length()) {
            return line.substring(index + 7);
        } else {
            return "";
        }
    }

    static String setRating(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        while (index < line.length() && line.charAt(index) != ',') {
            sBuilder.append(line.charAt(index));
            index++;
        }
        film.setRating(sBuilder.toString());

        if (index + 1 < line.length()) {
            return line.substring(index + 7);
        } else {
            return "";
        }
    }

    static void breakingTheLine(String line) {
        Film film = new Film();
        line = setId(line, film);
        line = setType(line, film);
        line = setTitle(line, film);
        line = setDirectior(line, film);
        line = setCast(line, film);
        line = setCountry(line, film);
        line = setDate(line, film);
        // line = setRating(line, film);
        // System.out.println(line);
        System.out.println(film.getId());
        System.out.println(film.getDateAdded());
    }

    public static void main(String[] args) {

        String csvFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/netflix_titles.csv";
        String[] lines = getLines(csvFilePath);

        for (int i = 0; i < lines.length; i++) {
            breakingTheLine(lines[i]);
        }
    }
}