package Cruise_Ship_Boarding_Program_Task_2;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Task II  Boarding System for a Cruise ship in java
 SDII Coursework L4 Sem2
 @author Sandeepa Induwara Samaranayake
 @version 2 - classes solution - Passenger class 2022-04-02
 */

public class Passenger
{
    private String firstName;                                                          // creating attributes for Passenger class as private variables.
    private String surName;
    private double expenses;

    /**
     * Passenger constructor( with constructor overloading )
     * @param firstName firstname for passenger (Default value - N/A)
     * @param surName surname for passenger (Default value - N/A)
     * @param expenses expenses for passenger (Default value - 0.0)
     */
    public Passenger(String firstName, String surName, double expenses)
    {
        this.firstName = firstName;                                                    // set attributes
        this.surName = surName;
        this.expenses = expenses;
    }

    /**
     * getter for firstName.
     * @return firstname of the passenger.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * setter for firstName.
     * @param firstName the value for passenger's firstname.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * getter for surName.
     * @return surname of the passenger.
     */
    public String getSurName()
    {
        return surName;
    }

    /**
     * setter for surName.
     * @param surName the value for passenger's surname.
     */
    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    /**
     * getter for expenses.
     * @return expenses of the passenger.
     */
    public double getExpenses()
    {
        return expenses;
    }

    /**
     * setter for expenses.
     * @param expenses the value for passenger's expenses.
     */
    public void setExpenses(double expenses)
    {
        this.expenses = expenses;
    }

    /**
     * method to delete a passenger
     * here we are setting default values for each field.
     */
    public void deletePassenger()
    {
        this.setFirstName("N/A");
        this.setSurName("N/A");
        this.setExpenses(0.0);
    }
}
