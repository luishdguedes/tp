package archive;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.Film;

public class ExternalSorting {
    private static char tombstone = '¬';
    private static RandomAccessFile raf;
    private static final String dbFilePath = "DataBase/films.db";

    ExternalSorting() {
        try {
            raf = new RandomAccessFile(dbFilePath, "rw");
            raf.seek(4);
        } catch (Exception e) {
        }
    }

    private static Film readFilm(RandomAccessFile raf) throws IOException {
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
            return film;
        }
        return null;
    }

    public static void externalMergeSort(String inputFile, String outputFile, int chunkSize) throws IOException {
        // Step 1: Split the input into sorted chunks
        List<String> chunkFileNames = splitIntoSortedChunks(inputFile, chunkSize);

        // Step 2: Merge the sorted chunks
        mergeSortedChunks(chunkFileNames, outputFile);
    }

    private static List<String> splitIntoSortedChunks(String inputFile, int chunkSize)
            throws IOException {
        List<String> chunkFileNames = new ArrayList<>();
        int chunkCount = 0;
        List<Film> chunk = new ArrayList<>();
        Film film;
        while ((film = readFilm(raf)) != null) {
            chunk.add(film);
            if (chunk.size() >= chunkCount) {
                quickSort(chunk);
                Collections.sort(chunk,(f1,f2)->f1.getTitle().compareTo(f2.getTitle()));
                String chunkFileName = "chunk" + chunkCount + ".db";
                chunkFileNames.add(chunkFileName);
                try (RandomAccessFile chunkRandomAccessFile = new RandomAccessFile(chunkFileName, "rw")) {
                    for (Film item : chunk) {
                        Crud.createInTempFiles(item, chunkRandomAccessFile);
                    }
                }
                chunk.clear();
                chunkCount++;
            }

            if (!chunk.isEmpty()) {
                quickSort(chunk);
                String chunkFileName = "chunk" + chunkCount + ".db";
                chunkFileNames.add(chunkFileName);
                try (RandomAccessFile chunkRandomAccessFile = new RandomAccessFile(chunkFileName, "rw")) {
                    Crud.createInTempFiles(film, chunkRandomAccessFile);
                }
            }
        }
        return chunkFileNames;
    }

    private static void mergeSortedChunks(List<String> chunkFileNames, String outputFile) {

    }

    public static void quickSort(List<Film> arr) {
        if (arr == null || arr.size() <= 1) {
            return; // No need to sort
        }
        quickSort(arr, 0, arr.size() - 1);
    }

    private static void quickSort(List<Film> arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Film> arr, int low, int high) {
        Film pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr.get(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Film> arr, int i, int j) {
        Film temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

}

/*
 * void distribution(RandomAccessFile[] tempFiles, int memorySize, int
 * numberOfBlocks) throws IOException {
 * int circularCounter = 0;
 * while (this.raf.getFilePointer() < raf.length()) {
 * Film[] buffer = new Film[memorySize];
 * for (int j = 0; j < memorySize; j++) {
 * Film film = readFilm(this.raf);
 * if (film == null)
 * break;
 * buffer[j] = film;
 * }
 * quickSort(buffer, 0, memorySize - 1);
 * for (Film film : buffer) {
 * Crud.createInTempFiles(film, tempFiles[circularCounter]);
 * }
 * circularCounter = (++circularCounter) % numberOfBlocks;
 * }
 * }
 * 
 * Film smaller(Film[] array) {
 * int smaller = 0;
 * for (int i = 1; i < array.length; i++) {
 * if (array[smaller].getTitle().compareTo(array[i].getTitle()) > 0) {
 * smaller = i;
 * }
 * }
 * return array[smaller];
 * }
 * 
 * void variableIntercalation(RandomAccessFile[] tempFiles, RandomAccessFile[]
 * tempFilesIntercalation)
 * throws IOException {
 * for (int i = 0; i < tempFiles.length; i++) {
 * tempFiles[i].seek(0);
 * }
 * while (true) {
 * int[] accountants = new int[tempFiles.length];
 * boolean[] accountantsChecks = new boolean[tempFiles.length];
 * for (int i = 0; i < tempFiles.length; i++) {
 * 
 * }
 * int fileCounter2 = 0;
 * 
 * fileCounter2 = (++fileCounter2) % tempFiles.length;
 * }
 * }
 * 
 * void balance(int numberOfBlocks, int memorySize) throws IOException {
 * RandomAccessFile[] tempFiles = new RandomAccessFile[numberOfBlocks];
 * RandomAccessFile[] tempFilesIntercalation = new
 * RandomAccessFile[numberOfBlocks];
 * for (int i = 0; i < numberOfBlocks; i++) {
 * tempFiles[i] = new RandomAccessFile("outputs/tempFile_" + i + ".db", "rw");
 * tempFilesIntercalation[i] = new
 * RandomAccessFile("outputs/tempFileIntercalation_" + i + ".db", "rw");
 * }
 * distribution(tempFiles, memorySize, numberOfBlocks);
 * variableIntercalation(tempFiles, tempFilesIntercalation);
 * 
 * }
 * 
 * 
 * Film readFilm() throws IOException {
 * this.raf.seek(posOfFilmReading);
 * if (this.raf.getFilePointer() < this.raf.length()) {
 * 
 * Film film = new Film();
 * film.setTombstone(this.raf.readChar());
 * 
 * while (film.getTombstone() != tombstone) {
 * int registerSize = this.raf.readInt();
 * this.raf.skipBytes(registerSize);
 * }
 * film.setTombstone(this.raf.readChar());
 * int registerSize = this.raf.readInt();
 * byte[] filmData = new byte[registerSize];
 * this.raf.read(filmData);
 * posOfFilmReading = this.raf.getFilePointer();
 * return film;
 * }
 * return null;
 * }
 * 
 * 
 * private static void swap(Film[] array, int i, int j) {
 * Film temp = array[i];
 * array[i] = array[j];
 * array[j] = temp;
 * }
 * 
 * void quickSort(Film[] array, int left, int right) {
 * int i = left, j = right;
 * Film pivot = array[(right + left) / 2];
 * while (i <= j) {
 * while (array[i].getTitle().compareTo(pivot.getTitle()) < 0)
 * i++;
 * while (array[j].getTitle().compareTo(pivot.getTitle()) > 0)
 * j++;
 * if (i <= j) {
 * swap(array, i, j);
 * i++;
 * j++;
 * }
 * }
 * if (left < j) {
 * quickSort(array, left, j);
 * }
 * if (right > i) {
 * quickSort(array, i, right);
 * }
 */
