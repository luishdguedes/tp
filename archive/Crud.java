package archive;

import java.io.IOException;
import java.io.RandomAccessFile;

import Model.Film;

public class Crud {
    private static final String dbFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/films.db";

    public Crud() {
    }

    public void create(Film film) {

    }

    public void readAll() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(dbFilePath, "rw")) {
            randomAccessFile.seek(0);

            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                int filmSize = randomAccessFile.readInt();
                byte[] filmData = new byte[filmSize];
                randomAccessFile.read(filmData);

                Film film = new Film();
                film.fromByteArray(filmData);

                System.out.println(film.toString());
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

}
