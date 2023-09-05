package archive;

import java.io.IOException;
import java.io.RandomAccessFile;

import Model.Film;

public class ExternalSorting {
    private static char tombstone = '¬';
    private static RandomAccessFile raf;
    private static long posOfFilmReading;

    ExternalSorting() {
        try {
            raf = new RandomAccessFile("DataBase/films.db", "rw");
            posOfFilmReading = 4;
        } catch (Exception e) {
        }
    }

    void quickSort(Film[] array, int left, int right) {
    }

    Film readFilm() throws IOException {
        raf.seek(posOfFilmReading);
        if (raf.getFilePointer() < raf.length()) {

            Film film = new Film();
            film.setTombstone(raf.readChar());

            while (film.getTombstone() != tombstone) {
                int registerSize = raf.readInt();
                raf.skipBytes(registerSize);
                film.setTombstone(raf.readChar());
            }
            int registerSize = raf.readInt();
            byte[] filmData = new byte[registerSize];
            raf.read(filmData);
            posOfFilmReading = raf.getFilePointer();
            return film;
        }
        return null;
    }

    void balance(int B, int M) throws IOException {

    }

}
