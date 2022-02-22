package contacts;

import java.io.*;

public class SerializationUtils{

    public static void serialize(Object object, String fileNme) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileNme);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.close();
    }

    public static Object deserialize(String fileNme) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(fileNme);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        ois.close();
        return object;
    }
}
