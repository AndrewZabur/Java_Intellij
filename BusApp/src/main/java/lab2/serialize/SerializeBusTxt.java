package lab2.serialize;

import lab2.Bus;

import java.io.*;
import java.time.LocalDate;

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
        String[] temp = new String[4];

        for(int i = 0; i < 4; i++){
            str[i]= bf.readLine();
            temp[i] ="";
        }

        for(int i = 0; i < str.length; i++){
            for(int j = 0; j < str[i].length(); j++)
            {
                if(str[i].charAt(j)=='=') {
                    while (j < str[i].length()-1) {
                        temp[i] += str[i].charAt(j + 1);
                        j++;
                    }
                }
            }
        }

        Bus bus = new Bus();
        bus.setCapacity(Integer.parseInt(temp[0]));
        bus.setIndentificationNumber(temp[1]);
        bus.setDataConstruction(LocalDate.parse(temp[2]));
        bus.setModel(Bus.Model.valueOf(temp[3]));
        return bus;

    }
}
