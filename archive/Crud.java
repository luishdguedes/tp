package archive;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import Model.Film;

public class Crud {
    private static final String dbFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/films.db";
    private static final char tombstone = '¬';
    static private DataOutputStream dos;

    public Crud() {
        try {
            FileOutputStream arq = new FileOutputStream(dbFilePath);
            dos = new DataOutputStream(arq);
            dos.writeInt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(Film film) {
        try (RandomAccessFile raf = new RandomAccessFile(dbFilePath, "rw")) {
            raf.seek(0);
            int lastID = raf.readInt();
            raf.seek(0);
            film.setId(lastID + 1);
            raf.writeInt(film.getId());

            byte[] array = film.toByteArray();
            raf.seek(raf.length());
            raf.writeChar(tombstone);
            raf.writeInt(array.length);
            raf.write(array);
            System.out.println(film.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Film sequencialSearch(int id) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(dbFilePath, "rw")) {
            randomAccessFile.seek(4);
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                if (randomAccessFile.readChar() != '&') {
                    int filmSize = randomAccessFile.readInt();
                    byte[] filmData = new byte[filmSize];
                    randomAccessFile.read(filmData);

                    Film film = new Film();
                    film.fromByteArray(filmData);
                    if (film.getId() == id) {
                        return film;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void readAll() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(dbFilePath, "rw")) {
            randomAccessFile.seek(4);

            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                if (randomAccessFile.readChar() != '&') {
                    int filmSize = randomAccessFile.readInt();
                    byte[] filmData = new byte[filmSize];
                    randomAccessFile.read(filmData);

                    Film film = new Film();
                    film.fromByteArray(filmData);

                    System.out.println(film.toString());
                } else {
                    int filmSize = randomAccessFile.readInt();
                    byte[] filmData = new byte[filmSize];
                    randomAccessFile.read(filmData);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}