package lab2;

import java.time.LocalDate;
import lab2.Bus.Model;
public class BusBuilder {

    private int capacity;
    private String identificationNumber;
    private LocalDate dataConstruction;
    private Model model;

    public BusBuilder setCapacity(int capacity)throws RuntimeException{

        if(capacity < 0){
            throw new RuntimeException("Negative capacity is impossible!!!");
        }

        this.capacity = capacity;
        return this;
    }

    public BusBuilder setIdentificationNumder(String identificationNumder){

        this.identificationNumber = identificationNumder;
        return this;
    }

    public BusBuilder setDataConstruction(LocalDate dataConstruction)throws RuntimeException{

        if(dataConstruction.isAfter(LocalDate.now())){
            throw new RuntimeException("This date has not come yet!!!");
        }
        this.dataConstruction = dataConstruction;
        return this;
    }

    public BusBuilder setModel(Model model){

        this.model = model;
        return this;
    }


    public Bus build() {

        return new Bus(capacity, identificationNumber, dataConstruction, model);
    }
}
