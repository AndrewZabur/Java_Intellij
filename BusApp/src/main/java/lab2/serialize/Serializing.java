package lab2.serialize;

import java.io.*;

public interface Serializing<T> {

    void serializingObj(T obj, Writer out) throws IOException;

     T deserializingObj(Reader in)throws IOException;

}
