package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

public class Garage {
    private String adress;
    private String owner;
    private ArrayList<Bus> buses;
    @XmlTransient
    @JsonIgnore
    private int id;

    public Garage(){

    }

    public Garage(String adress, String owner, ArrayList<Bus> buses){
        this.adress = adress;
        this.owner = owner;
        this.buses = buses;
    }

    /**
     *
     * @param passengers - amount of people.
     * This method will inform you about cars(buses)which can
     * transport passengers and in which garage they are situated.
     */

    public ArrayList<Bus> garageInfo(int passengers){
        assert passengers > 0 : "Wrong param!!!";

        ArrayList<Bus> tmpBuses = new ArrayList<>();

        for(int i = 0; i < buses.size(); i++)
        {
            if(buses.get(i).checkOfTheSize(passengers)){

                tmpBuses.add(buses.get(i));
            }
        }

        return tmpBuses;
    }

    /**
     *
     * @param company - amount of people
     * This method will tell you, whether the company of people can be
     * transported to the destination, by the cars from one garage.
     */

    public boolean companyOfPeople(int company){

        assert company > 0 : "Wrong param!!!";
        int temp = 0;
        for(int i = 0; i < buses.size(); i++)
        {
            temp += buses.get(i).getCapacity();
        }
        return temp >= company;
    }

    /**
     * This method returns capacity of the cars,
     * which are situated in one garage together.
     * @return
     */

    public int garageCapacity(){

        int cnt = 0;
        for(int i = 0; i < buses.size(); i++)
        {
            cnt += buses.get(i).getCapacity();
        }
        return cnt;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adress == null) ? 0 : adress.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Garage other = (Garage) obj;

        return (other.adress.equals(this.adress) &&  other.owner.equals(this.owner) /*&& other.buses.equals(this.buses)*/);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id='" + id + '\'' +
                "adress='" + adress + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
