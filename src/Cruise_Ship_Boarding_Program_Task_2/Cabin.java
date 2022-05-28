package Cruise_Ship_Boarding_Program_Task_2;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Task II  Boarding System for a Cruise ship in java
 SDII Coursework L4 Sem2
 @author Sandeepa Induwara Samaranayake
 @version 2 - classes solution - Cabin Class 2022-04-02
 */

public class Cabin
{
    private int numberOfPassengersInTheCabin = 0;                                             // creating attributes for Cabin class as private.
    private Passenger passenger1;
    private Passenger passenger2;
    private Passenger passenger3;

    /**
     * Cabin constructor
     */
    public Cabin()
    {
        setPassenger1(new Passenger("N/A","N/A",0.0));                // we are creating a new Passenger object and set it as passenger1.
        setPassenger2(new Passenger("N/A","N/A",0.0));                // we are creating a new Passenger object and set it as passenger2.
        setPassenger3(new Passenger("N/A","N/A",0.0));                // we are creating a new Passenger object and set it as passenger3.
    }

    /**
     * getter for numberOfPassengersInTheCabin.
     * @return no of passengers in the cabin.
     */
    public int getNumberOfPassengersInTheCabin()
    {
        return numberOfPassengersInTheCabin;
    }

    /**
     * setter for numberOfPassengersInTheCabin.
     * @param numberOfGuestsInTheCabin the value for numberOfPassengersInTheCabin.
     */
    public void setNumberOfGuestsInTheCabin(int numberOfGuestsInTheCabin)
    {
        this.numberOfPassengersInTheCabin = numberOfGuestsInTheCabin;
    }

    /**
     * this method will increment numberOfPassengersInTheCabin by 1.
     */
    public void incrementNumberOfGuests()
    {
        this.numberOfPassengersInTheCabin++;
    }

    /**
     * this method will decrement numberOfPassengersInTheCabin by 1.
     */
    public void decrementNumberOfGuests()
    {
        this.numberOfPassengersInTheCabin--;
    }

    /**
     * getter for passenger1.
     * @return passenger1 Passenger object.
     */
    public Passenger getPassenger1()
    {
        return passenger1;
    }

    /**
     * setter for passenger1.
     * @param passenger1 the Passenger object which will assign to passenger1.
     */
    public void setPassenger1(Passenger passenger1)
    {
        this.passenger1 = passenger1;
    }

    /**
     * getter for passenger2.
     * @return passenger2 Passenger object.
     */
    public Passenger getPassenger2()
    {
        return passenger2;
    }

    /**
     * setter for passenger2.
     * @param passenger2 the Passenger object which will assign to passenger2.
     */
    public void setPassenger2(Passenger passenger2)
    {
        this.passenger2 = passenger2;
    }

    /**
     * getter for passenger3.
     * @return passenger3 Passenger object.
     */
    public Passenger getPassenger3()
    {
        return passenger3;
    }

    /**
     * setter for passenger3.
     * @param passenger3 the Passenger object which will assign to passenger3.
     */
    public void setPassenger3(Passenger passenger3)
    {
        this.passenger3 = passenger3;
    }

    /**
     * method to delete a cabin.
     */
    public void deleteCabin()
    {
        this.passenger1.deletePassenger();                                                     // here we are calling deletePassenger() method for all 3 passengers.
        this.passenger2.deletePassenger();
        this.passenger3.deletePassenger();
        this.numberOfPassengersInTheCabin = 0;                                                 // set noOfPassengersInCabin to 0.
    }
}
