package lab2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.regex.*;

public class Garage {

    private String adress;
    private String owner;
    private ArrayList<Bus> buses;
    @XmlTransient
    @JsonIgnore
    private int id;

    private final static String ADRESS_PATTERN = "adress=([A-Z][a-z]{1,}\\s[a-z]{1,}\\.\\s\\d{1,}\\-[A-Z])";
    private final static String OWNER_PATTERN = "owner=([A-Z][a-z]{1,}\\s[A-Z][a-z]{1,}\\s[A-Z][a-z]{1,})";

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
                "adress='" + adress + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    public String formString() {
        StringBuilder str = new StringBuilder();
        str.append("adress=");
        str.append(this.adress);
        str.append("\n");
        str.append("owner=");
        str.append(this.owner);
        str.append("\n");
        str.append("buses=[");
        str.append("\n");
        int bound = this.buses.size();
        for (int i = 0; i < bound; i++) {
            str.append(buses.get(i).toString());
            str.append("\n");
        }
        str.append("]");
        return str.toString();
    }


    public Garage fromString(String[] str){
        Pattern pattern1 = Pattern.compile(ADRESS_PATTERN);
        Pattern pattern2 = Pattern.compile(OWNER_PATTERN);
        Matcher match;
        String[] tmp = new String[4];
        Bus bus = new Bus();
        ArrayList<Bus> buses1 = new ArrayList<>();
        int i;
        for(i = 0; i < 3; i++)
        {
            if((match = pattern1.matcher(str[i])).matches() == true){
                this.adress = match.group(1);
            }
            if((match = pattern2.matcher(str[i])).matches() == true){
                this.owner = match.group(1);
            }
        }
        while (true){
            if(str[i].equals("]")){
                break;
            }
            for(int j = 0; j < 4; j++){
                tmp[j] = str[i];
                i++;
            }

            bus.fromString(tmp);
            buses1.add(bus);
            this.setBuses(buses1);
        }
        return this;
    }
}