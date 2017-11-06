package lab2.serialize;

import lab2.Bus;
import lab2.BusBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class SerializeBusTxt implements Serializing<Bus> {
    @Override
    public void serializingObj(Bus obj, Writer out) throws IOException {
        out.write(obj.toString());
        out.flush();
        out.close();
    }

    @Override
    public Bus deserializingObj(Reader in) throws IOException {
        BufferedReader bf = new BufferedReader(in);
        String[] str = new String[4];

        Bus bus = new Bus();

        for(int i = 0; i < 4; i++){
            str[i]= bf.readLine();
        }
        bus.fromString(str);
        return bus;

    }
}
