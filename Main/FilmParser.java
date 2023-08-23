package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Film;

public class FilmParser {
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
            return line.substring(index + 1);
        } else {
            return "";
        }
    }

    static String setTimeDuration(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        while (index < line.length() && line.charAt(index) != ',') {
            sBuilder.append(line.charAt(index));
            index++;
        }
        film.setTimeDuration(sBuilder.toString());
        if (index + 1 < line.length()) {

            return line.substring(index + 1);
        } else {
            return "";
        }
    }

    static String setListedIn(String line, Film film) {
        int index = 0;
        StringBuilder holdString = new StringBuilder();
        if (line.charAt(index) == '\"') {
            index++;
            while (line.charAt(index) != '\"') {
                holdString.append(line.charAt(index));
                index++;
            }
            film.setListedIn(holdString.toString());
            return line = line.substring(index + 2, line.length());
        }
        while (line.charAt(index) != ',') {
            holdString.append(line.charAt(index));
            index++;
        }
        if (holdString.toString() == "") {
            film.setListedIn("Unknown");
        }
        film.setListedIn(holdString.toString());
        return line = line.substring(index + 1, line.length());
    }

    static void setDescription(String line, Film film) {
        int index = 0;
        StringBuilder holdString = new StringBuilder();
        if (line.charAt(index) == '\"') {
            index++;
            while (index < line.length() - 1) {
                holdString.append(line.charAt(index));
                index++;
            }
        } else {
            while (index < line.length() - 1) {
                holdString.append(line.charAt(index));
                index++;
            }
        }
        if (holdString.toString() == "") {
            film.setDescription("Unknown");
        }
        film.setDescription(holdString.toString());
    }
}
