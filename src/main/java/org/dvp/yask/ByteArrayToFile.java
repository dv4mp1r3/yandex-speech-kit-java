package org.dvp.yask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayToFile
{
    public static void save(String filepath, byte[] data) throws IOException {
        File file = new File(filepath);
        OutputStream os = new FileOutputStream(file);
        os.write(data);
        os.close();
    }
}
