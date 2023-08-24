package archive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Film;

public class FilmParser {
    public static String[] getLines(String csvFilePath) {
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
            e.printStackTrace();
        }
        return lines;
    }

    public static String setId(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        while (line.charAt(index) != ',') {
            sBuilder.append(line.charAt(index++));
        }
        film.setId(Integer.parseInt(sBuilder.toString()));
        return line.substring(index + 1);
    }

    public static String setType(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        while (line.charAt(index) != ',') {
            sBuilder.append(line.charAt(index++));
        }
        film.setType(sBuilder.toString());
        if (index < line.length() && line.charAt(index) == ',') {
            index++;
        }
        return line.substring(index);
    }

    public static String setTitle(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        if (line.charAt(index) == '\"') {
            index++;
            while (index < line.length() && line.charAt(index) != '\"') {
                sBuilder.append(line.charAt(index++));
            }
            index++;
        } else {
            while (index < line.length() && line.charAt(index) != ',') {
                sBuilder.append(line.charAt(index++));
            }
        }
        String title = sBuilder.toString().trim();
        film.setTitle(title.isEmpty() ? "Unknown" : title);
        if (index < line.length() && line.charAt(index) == ',') {
            index++;
        }
        return line.substring(index);
    }

    public static String setDirectior(String line, Film film) {
        int index = 0;
        StringBuilder sBuilder = new StringBuilder();
        if (line.charAt(index) == '\"') {
            index++;
            while (index < line.length() && line.charAt(index) != '\"') {
                sBuilder.append(line.charAt(index++));
            }
            index++;
        } else {
            while (index < line.length() && line.charAt(index) != ',') {
                sBuilder.append(line.charAt(index++));
            }
        }
        String directorName = sBuilder.toString().trim();
        film.setDirector(directorName.isEmpty() ? "Unknown" : directorName);
        if (index < line.length() && line.charAt(index) == ',') {
            index++;
        }
        return line.substring(index);
    }

    public static String setCast(String line, Film film) {
        int index = 0;
        StringBuilder holdString = new StringBuilder();

        if (line.charAt(index) == '\"') {
            index++;
            while (index < line.length() && line.charAt(index) != '\"') {
                holdString.append(line.charAt(index));
                index++;
            }
            index++; // Move past the closing double quote
        } else {
            while (index < line.length() && line.charAt(index) != ',') {
                holdString.append(line.charAt(index));
                index++;
            }
        }

        String cast = holdString.toString().trim();
        if (cast.isEmpty()) {
            cast = "Unknown";
        }
        film.setCast(cast);

        // Return the remaining part of the line after extracting the cast
        if (index < line.length() && line.charAt(index) == ',') {
            index++; // Move past the comma
        }
        return line.substring(index);
    }

    public static String setCountry(String line, Film film) {
        int index = 0;
        StringBuilder holdString = new StringBuilder();

        if (line.charAt(index) == '\"') {
            index++;
            while (index < line.length() && line.charAt(index) != '\"') {
                holdString.append(line.charAt(index));
                index++;
            }
            index++; // Move past the closing double quote
        } else {
            while (index < line.length() && line.charAt(index) != ',') {
                holdString.append(line.charAt(index));
                index++;
            }
        }

        String country = holdString.toString().trim();
        if (country.isEmpty()) {
            country = "Unknown";
        }
        film.setCountry(country);

        // Return the remaining part of the line after extracting the country
        if (index < line.length() && line.charAt(index) == ',') {
            index++; // Move past the comma
        }
        return line.substring(index);
    }

    public static String setDate(String line, Film film) {
        int index = 1;
        StringBuilder holdString = new StringBuilder();

        // Skip leading spaces
        if (line.charAt(index) == ' ') {
            index++;
        }

        // Check for a comma at the beginning
        if (line.charAt(0) == ',') {
            film.setDateAdded(null);
            return line.substring(index + 1);
        }

        // Extract the date string
        while (index < line.length() && line.charAt(index) != '\"') {
            holdString.append(line.charAt(index));
            index++;
        }

        // Parse the date string using SimpleDateFormat
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy");
        Date date;
        try {
            date = inputFormat.parse(holdString.toString());
            film.setDateAdded(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Return the remaining part of the line
        if (index + 2 < line.length()) {
            return line.substring(index + 7);
        } else {
            return "";
        }
    }

    public static String setRating(String line, Film film) {
        int index = 0;
        char[] charBuilder = new char[5];

        // Extract rating string until comma is encountered
        while (index < charBuilder.length && line.charAt(index) != ',') {
            charBuilder[index] = line.charAt(index);
            index++;
        }

        // Set the extracted rating to the film object
        film.setRating(charBuilder);

        // Return the remaining part of the line
        if (index + 1 < line.length()) {
            return line.substring(index + 1);
        } else {
            return "";
        }
    }

    public static String setTimeDuration(String line, Film film) {
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
}
