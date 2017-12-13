package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;

public class BusValidator {
    private int capacity;
    private String identificationNumber;
    private LocalDate dataConstruction;
    private Bus.Model model;
    @JsonIgnore
    @XmlTransient
    private int id;

    public BusValidator setId(int id){
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public BusValidator setCapacity(int capacity)throws RuntimeException{

        if(capacity < 0){
            throw new RuntimeException("Negative capacity is impossible!!!");
        }

        this.capacity = capacity;
        return this;
    }

    public BusValidator setIdentificationNumder(String identificationNumder){

        this.identificationNumber = identificationNumder;
        return this;
    }

    public BusValidator setDataConstruction(LocalDate dataConstruction)throws RuntimeException{

        if(dataConstruction.isAfter(LocalDate.now())){
            throw new RuntimeException("This date has not come yet!!!");
        }
        this.dataConstruction = dataConstruction;
        return this;
    }

    public BusValidator setModel(Bus.Model model){

        this.model = model;
        return this;
    }

    public Bus build() {

        return new Bus(capacity, identificationNumber, dataConstruction, model);
    }


}
