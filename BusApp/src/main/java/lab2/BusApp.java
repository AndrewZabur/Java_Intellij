package lab2;

import lab2.serialize.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class BusApp {

    public static void main(String[] args) throws IOException{

        Bus obj = new BusBuilder()
                .setCapacity(12)
                .setIdentificationNumder("IO0989KJ")
                .setDataConstruction(LocalDate.of(1998,12,12))
                .setModel(Bus.Model.NISSAN)
                .build();

        Serializing<Bus> busXml = new SerializeBusXml();
        FileWriter fw = new FileWriter("Bus.xml");
        busXml.serializingObj(obj, fw);
        FileReader fis = new FileReader("Bus.xml");
        Bus temp;
        temp = busXml.deserializingObj(fis);
        System.out.println(temp.toString());
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Bus obj1 = new Bus(15, "AH8781OO", LocalDate.of(1997, 07, 7), Bus.Model.AUDI);
        Bus obj2 = new Bus(25, "AH8110PP", LocalDate.of(2007, 10, 17), Bus.Model.GEELY);
        Bus obj3 = new Bus(35, "AH8550MM", LocalDate.of(2017, 05, 27), Bus.Model.NISSAN);
        ArrayList<Bus> buses = new ArrayList<>();
        buses.add(obj1);
        buses.add(obj2);
        buses.add(obj3);
        Garage garage = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", buses);
        Serializing<Garage> garageXml = new SerializeGarageXml();
        FileWriter fw1 = new FileWriter("Garage.xml");
        garageXml.serializingObj(garage, fw1);
        FileReader fis1 = new FileReader("Garage.xml");
        Garage temp1;
        temp1 = garageXml.deserializingObj(fis1);
        System.out.println(temp1.toString());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       Bus busik = new BusBuilder()
                .setCapacity(10)
                .setIdentificationNumder("PO9076IO")
                .setDataConstruction(LocalDate.of(1997,12,23))
                .setModel(Bus.Model.GEELY)
                .build();
        Serializing<Bus> busTxt = new SerializeBusTxt();
        FileWriter f = new FileWriter("Bus.txt");
        busTxt.serializingObj(busik, f);
        FileReader fr = new FileReader("Bus.txt");
        Bus tempik;
        tempik = busTxt.deserializingObj(fr);
        System.out.println(tempik.toString());
    }

}
