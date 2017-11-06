package lab2.serialize;

import lab2.Bus;
import lab2.Garage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class SerializeGarageTxt implements Serializing<Garage>{
    @Override
    public void serializingObj(Garage obj, Writer out) throws IOException {
        out.write(obj.toString());
        out.flush();
        out.close();
    }

    @Override
    public Garage deserializingObj(Reader in) throws IOException {
       /* BufferedReader bf = new BufferedReader(in);
        String[] str = new String[4];

        Garage garage = new Garage();

        for(int i = 0; i < 4; i++){
            str[i]= bf.readLine();
        }
        garage.fromString(str);*/
        return null;


    }
}
