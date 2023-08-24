package archive;

import Model.Film;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class FilmArchiveWriter {

    FileOutputStream arq;
    DataOutputStream dos;

    FileInputStream arq2;
    DataInputStream dis;

    public void writeBinaryArchive(Film film) {
        try {
            arq = new FileOutputStream("/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/BinaryFilmsFile.db");
            dos = new DataOutputStream(arq);

            dos.writeInt(film.getId());
            dos.writeInt(film.getDirector().length());
            dos.writeUTF(film.getDirector());
            dos.writeInt(film.getRating().length); // Write the length of the char array
            for (char c : film.getRating()) {
                dos.writeChar(c); // Write each character to the output stream
            }

        } catch (Exception e) {
        }

    }
}
