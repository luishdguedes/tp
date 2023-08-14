import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;

class Main {
    public static void main(String[] args) {

        String csvFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/netflix_titles.csv";
        String[] lines = new String[8807];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            int index = 0;
            reader.readLine();
            while ((lines[index] = reader.readLine()) != null) {
                index++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        Film film = new Film();
    }
}