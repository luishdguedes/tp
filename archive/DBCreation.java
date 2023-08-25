package archive;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DBCreation {

    private static final String dbFilePath = "/mnt/c/Users/igorm/OneDrive/Documents/aeds3/tp/DataBase/films.db";
    static private DataOutputStream dos;

    public DBCreation() {
        try {
            FileOutputStream arq = new FileOutputStream(dbFilePath);
            dos = new DataOutputStream(arq);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void binaryWriter(byte[] array) {
        try {
            dos.writeInt(array.length);
            dos.write(array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
