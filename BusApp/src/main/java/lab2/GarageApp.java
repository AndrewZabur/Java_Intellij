package lab2;

import java.util.ArrayList;

public class GarageApp {

    private	ArrayList<Garage> garages;

    /**
     * This method returns the collection of garages,
     * which can transport the company of people.
     *
     * This method will count the capacity of every garage,
     * and then if the garage can transport this company, method
     * will return this garage.
     * @param garages
     * @param company
     * @return
     */

    public ArrayList<Garage> ifCanTransportCompany(int company){

        ArrayList<Garage> tmpGarages = new ArrayList<Garage>();
        int cnt;
        for(int i = 0; i < garages.size(); i++)
        {
            cnt = garages.get(i).garageCapacity();
            if(cnt >= company)
            {
                tmpGarages.add(garages.get(i));
            }
            cnt = 0;
        }

        return tmpGarages;
    }

    public ArrayList<Garage> getGarages() {
        return garages;
    }

    public void setGarages(ArrayList<Garage> garages) {
        this.garages = garages;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((garages == null) ? 0 : garages.hashCode());
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

        GarageApp other = (GarageApp) obj;

        return (other.garages.equals(this.garages));
    }

}