package lab2;

import lab2.serialize.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class BusApp {

    public static void main(String[] args) throws IOException{
        Bus obj1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
        Bus obj2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        Bus obj3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
        Bus obj4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);

        ArrayList<Bus> buses = new ArrayList<>();
        buses.add(obj1);
        buses.add(obj2);
        buses.add(obj3);
        buses.add(obj4);
        Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", buses);

        Garage gar2;

        Serializing<Garage> garTxt = new SerializeGarageTxt();
        FileWriter fw = new FileWriter("Garage.txt");
        FileReader fr = new FileReader("Garage.txt");
        garTxt.serializingObj(gar1, fw);

        gar2 = garTxt.deserializingObj(fr);

        System.out.println(gar2.toString());

    }

}
