package archive;

import java.io.IOException;
import java.io.RandomAccessFile;

import Model.Film;

public class ExternalSorting {
    private static char tombstone = '¬';
    private static RandomAccessFile raf;
    private static long posOfFilmReading;
    private static final String dbFilePath = "DataBase/films.db";

    ExternalSorting() {
        try {
            raf = new RandomAccessFile(dbFilePath, "rw");
            posOfFilmReading = 4;
        } catch (Exception e) {
        }
    }

    private static void swap(Film[] array, int i, int j) {
        Film temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    void quickSort(Film[] array, int left, int right) {
        int i = left, j = right;
        Film pivot = array[(right + left) / 2];
        while (i <= j) {
            while (array[i].getTitle().compareTo(pivot.getTitle()) < 0)
                i++;
            while (array[j].getTitle().compareTo(pivot.getTitle()) > 0)
                j++;
            if (i <= j) {
                swap(array, i, j);
                i++;
                j++;
            }
        }
        if (left < j) {
            quickSort(array, left, j);
        }
        if (right > i) {
            quickSort(array, i, right);
        }
    }

    Film readFilm() throws IOException {
        raf.seek(posOfFilmReading);
        if (raf.getFilePointer() < raf.length()) {

            Film film = new Film();
            film.setTombstone(raf.readChar());

            while (film.getTombstone() != tombstone) {
                int registerSize = raf.readInt();
                raf.skipBytes(registerSize);
            }
            film.setTombstone(raf.readChar());
            int registerSize = raf.readInt();
            byte[] filmData = new byte[registerSize];
            raf.read(filmData);
            posOfFilmReading = raf.getFilePointer();
            return film;
        }
        return null;
    }

    void balance(int numberOfBlocks, int memorySize) throws IOException {
        RandomAccessFile[] tempFiles = new RandomAccessFile[numberOfBlocks];
        for (int i = 0; i < numberOfBlocks - 1; i++) {
            tempFiles[i] = new RandomAccessFile("outputs/tempFile_" + i + ".db", "rw");
        }
        for (int i = 0; i < numberOfBlocks; i++) {
            Film[] buffer = new Film[memorySize];
            for (int j = 0; j < memorySize; j++) {
                Film film = readFilm();
                if (film == null)
                    break;
                buffer[j] = film;
            }
            quickSort(buffer, 0, memorySize - 1);
        }
    }

}
