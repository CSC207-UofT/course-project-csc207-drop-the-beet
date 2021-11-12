package Connection;

import java.io.IOException;
import java.util.HashMap;

public interface ReadWrite {
    /**
     * @param filepath location of ser file
     * @param o object to be serialized
     */
    void saveToFile(String filepath, Object o) throws IOException;

    /**
     * @param filepath location of ser file
     */
    HashMap readFromFile(String filepath) throws IOException, ClassNotFoundException;
}