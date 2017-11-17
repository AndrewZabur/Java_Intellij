package lab2.serialize;

import lab2.Garage;

import java.io.*;
import java.util.ArrayList;

public class SerializeGarageTxt implements Serializing<Garage> {

    @Override
    public void serializingObj(Garage obj, Writer out) throws IOException {
        out.write(obj.toString());
        out.flush();
        out.close();
    }

    @Override
    public Garage deserializingObj(Reader in) throws IOException,  NullPointerException {
        Garage garage = new Garage();
        BufferedReader bf = new BufferedReader(in);
        String[] str = new String[1000];

        for(int i = 0; i < str.length; i++){
            str[i]= bf.readLine();
        }

        garage.fromString(str);
        return garage;

    }
}
