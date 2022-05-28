
package Cruise_Ship_Boarding_Program_Task_3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Task II  Boarding System for a Cruise ship in java
 SDII Coursework L4 Sem2
 @author Sandeepa Induwara Samaranayake
 @version 3 - classes solution with a Circular Queue - Main class 2022-04-02
 */

public class Main
{
    private final Cabin[] cabinsArray = new Cabin[12];                           // create a Cabin class object array of size 12 as cabinsArray. we are making cabinsInfo array final
                                                                                 // but still we can modify and delete array elements, because arrays are objects, and they
                                                                                 // do not hold values Instead, they point to the address of the locations that holds values.
                                                                                 // In the case of objects and arrays if a reference variable is final it cannot point to
                                                                                 // another object/array. simply we can't assign new array to cabinsArray because it is final.
    private static final Main cruiseShipClass = new Main();                      // create a final class object of Main as cruiseShipClass, it is static, so all methods including main() can access it.
    private static final CircularQueue circularQueue = new CircularQueue();      // create a final class object of CircularQueue as circularQueue, it is static, so all methods can access it.
    private final String fileName = "CabinInfo.txt";                             // create a fileName as a final string variable.


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);                                     // create scanner object sc for main method.

        // we have to use class object to access the cabinsArray array because being inside a static method we can't access nonstatic variables or arrays.
        for (int i = 0; i < cruiseShipClass.cabinsArray.length; i++)
        {
            cruiseShipClass.cabinsArray[i] = new Cabin();                        // create 12 new cabin objects with default values inside Cabin constructor as array elements in cabinsArray.
        }

        cruiseShipClass.showMenu();                                              // have to use class object because we can't call nonstatic method inside a static method. call ShowMenu() method.
        while (true)                                                             // infinite loop to prompt user for input.
        {
            System.out.print("\nEnter your option :");
            String userInput = String.valueOf(sc.next().charAt(0)).toUpperCase();          // get user input first letter and convert it into uppercase and also to a string.
            cruiseShipClass.callActions(userInput);                                        // callActions is not a static method. so we should call that method through class object.
        }
    }

    /**
     * ShowMenu method will display the menu.
     */
    public void showMenu()
    {
        // use a text block. no need of string concatenation.
        System.out.println("""

                ______________BOARDING SYSTEM FOR A CRUISE SHIP_________________

                M: Show Menu
                A: Add a Customer to a Cabin
                V: View All Cabins
                E: Display Empty Cabins
                D: Delete Customer From Cabin
                F: Find Cabin From Customer Name
                S: Store Program Data Into File
                L: Load Program Data From File
                O: View Passengers Ordered Alphabetically by Name
                T: Print Expenses Per Passenger and Total Expense
                U: Update Expenses
                W: Show Waiting List
                X: Exit
                ________________________________________________________________
                """);
    }

    /**
     * viewAllCabins method will display all the cabin status, whether they are empty or occupied by someone.
     */
    public void viewAllCabins()
    {
        for (int i = 0; i < cabinsArray.length; i++)
        {
            int noOfPassengers = cabinsArray[i].getNumberOfPassengersInTheCabin();                      // get numberOfPassengersInTheCabin of i th cabin to int noOfPassengers.
            Passenger passenger3 = cabinsArray[i].getPassenger3();                                      // get all 3 Passenger objects of i th cabin as passenger1, passenger2 and passenger3.
            Passenger passenger2 = cabinsArray[i].getPassenger2();
            Passenger passenger1 = cabinsArray[i].getPassenger1();

            if (noOfPassengers != 0)                                                                    // if i th cabin noOfPassengers is not 0 then,
            {
                String output = "Cabin " + i + " is occupied by ";                                      // show who is occupied this cabin.
                if (noOfPassengers >= 1)
                {
                    output += " |" + passenger1.getFirstName() + " " + passenger1.getSurName() + "|";
                }
                if (noOfPassengers >= 2)
                {
                    output += " |" + passenger2.getFirstName() + " " + passenger2.getSurName() + "|";
                }
                if (noOfPassengers >= 3)
                {
                    output += " |" + passenger3.getFirstName() + " " + passenger3.getSurName() + "|";
                }
                System.out.println(output);                                                             // here printing the created list of passengers.
            }
            else                                                                                        // if noOfPassengers is 0.
            {
                System.out.println("Cabin " + i + " is Empty");                                         // print cabin is empty.
            }
        }
    }

    /**
     * displayEmptyCabins method will only display the empty cabins.
     */
    public void displayEmptyCabins()
    {
        boolean check = false;
        for (int i = 0; i < cabinsArray.length; i++)
        {
            if (cabinsArray[i].getNumberOfPassengersInTheCabin() == 0)                                   // check every cabin, if the noOfPassengers is 0 or not.
            {
                System.out.println("Cabin " + i + " is Empty");                                          // if noOfPassengers is 0, print this cabin is empty.
                check = true;
            }
        }
        if(!check)
        {
            System.out.println("All Cabins Are Reserved. Empty Cabins Are Not Available At This Moment.");
        }
    }

    /**
     * addCustomerToCabin method will add a customer to a cabin.
     * it will ask a cabin number which you wish add the customer and his name.
     * if the given cabin has a vacant position then it will add customer to that cabin otherwise print already occupied.
     */

    public void addCustomerToCabin()
    {
        try
        {
           boolean checkForEmptyCabin = false;
            for (Cabin cabin : cabinsArray)                                                                // get cabin by cabin
            {
                if (cabin.getNumberOfPassengersInTheCabin() == 0)
                {
                    checkForEmptyCabin = true;
                    break;
                }
            }
            Scanner sc = new Scanner(System.in);                                                           // create scanner object sc for addCustomerToCabin() method.
            if(checkForEmptyCabin)
            {
                System.out.print("Enter Cabin Number (0-11) to Add a Customer (Enter 12 to Go Back) :");
                int cabinNum = sc.nextInt();                                                               // get cabinNum as an int. if pass anything other than an int will trigger catch block.
                if (cabinNum < cabinsArray.length)
                {
                    int numOfPassengers = cabinsArray[cabinNum].getNumberOfPassengersInTheCabin();                  // set number of passengers in the cabin to numOfPassengers.
                    Passenger passenger1 = cabinsArray[cabinNum].getPassenger1();                                   // get all 3 passengers inside the user given( cabinNum ) cabin as passenger1, passenger2 and passenger3.
                    Passenger passenger2 = cabinsArray[cabinNum].getPassenger2();
                    Passenger passenger3 = cabinsArray[cabinNum].getPassenger3();
                    if (numOfPassengers < 3)                                                                        // if the numOfPassengers is less than 3, we can add passenger to the cabin.
                    {
                        while (numOfPassengers < 5 - numOfPassengers)                                               // based on numOfPassengers this loop will run maximum up to 3 times.
                        {
                            System.out.print("Enter Customer " + (numOfPassengers + 1) + " 's First Name For Cabin " + cabinNum + ". (Enter q to Go Back) :");
                            String cabinCustomerFirstName = sc.next();                                              // get customer name for cabin as a String.
                            if (cabinCustomerFirstName.equalsIgnoreCase("q"))                            // if "q" is entered, break the loop.
                            {
                                break;
                            }

                            System.out.print("Enter Customer " + (numOfPassengers + 1) + " 's Surname For Cabin " + cabinNum + ". (Enter q to Go Back) :");
                            String cabinCustomerSurName = sc.next();
                            if (cabinCustomerSurName.equalsIgnoreCase("q"))
                            {
                                break;
                            }
                            else
                            {
                                if (numOfPassengers == 0)                                                              // if cabin is empty then set given f.name and surname to passenger1.
                                {
                                    passenger1.setFirstName(cabinCustomerFirstName.toUpperCase());
                                    passenger1.setSurName(cabinCustomerSurName.toUpperCase());
                                } 
                                else if (numOfPassengers == 1)                                                       // if numOfPassengers is 1, then set given info to passenger2.
                                {
                                    passenger2.setFirstName(cabinCustomerFirstName.toUpperCase());
                                    passenger2.setSurName(cabinCustomerSurName.toUpperCase());
                                } 
                                else                                                                                 // now numOfPassengers should be 3, then set given info to passenger3.
                                {
                                    passenger3.setFirstName(cabinCustomerFirstName.toUpperCase());
                                    passenger3.setSurName(cabinCustomerSurName.toUpperCase());
                                }
                                System.out.println("Customer " + cabinCustomerFirstName.toUpperCase() + " " + cabinCustomerSurName.toUpperCase() + " Added to Cabin " + cabinNum + " Successfully");
                                cabinsArray[cabinNum].incrementNumberOfGuests();
                                numOfPassengers++;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Cabin is Full ! This Cabin is Already occupied by " + passenger1.getFirstName() + " " + passenger1.getSurName() + " , " +
                                passenger2.getFirstName() + " " + passenger2.getSurName() + " and " +
                                passenger3.getFirstName() + " " + passenger3.getSurName());
                        addCustomerToCabin();
                    }
                } 
                else if (cabinNum > cabinsArray.length)                                                        // if the entered value is greater than cabinsArray length, show error.
                {
                    System.out.println("Cabin Number is Not Valid. Cabin Number Should Be A Number Between 0 and 11.");
                }
            }
            else
            {
                if(circularQueue.getNoOfPassengersInTheQueue() <= circularQueue.getPassengerQueue().length)
                {
                    System.out.print("Cruise Ship is Full. This Passenger Will Add To The Waiting List.\nEnter Customer's First Name (Enter q to Go Back) :");
                    String firstName = sc.next().toUpperCase();
                    if(firstName.equals("Q")){return;}
                    System.out.print("Enter Customer's Surname (Enter q to Go Back) :");
                    String surName = sc.next().toUpperCase();
                    if(surName.equals("Q")){return;}
                    circularQueue.enQueue(new Passenger(firstName, surName, 0.0));
                    System.out.println("The Customer " + firstName + " " + surName + " Added To The Waiting List.");
                    circularQueue.increaseNoOfPassengersInTheQueue();
                }
                else
                {
                    System.out.println("Cruise Ship is Full, And Also Waiting List Is Full. Be patient Until a Cabin Gets Clear.");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("The Entered Value can't be proceed. Please Enter a valid Cabin Number. Error :" + e);
            addCustomerToCabin();                                                                          // if any exception occurred when adding a customer, then allow user to add a customer again.
        }
    }

    /**
     * deleteCustomer method will delete a user using cabin number and passenger number.
     * if the given cabin is empty then show a message that cabin is already empty, otherwise it will delete customer record.
     */
    public void deleteCustomer()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                           // create scanner object sc for addCustomerToCabin() method.
            System.out.print("Enter Cabin Number to Delete (Enter 12 to Go Back) :");
            int cabinNum = sc.nextInt();                                                                   // get cabinNum as int.
            if (cabinNum < cabinsArray.length)                                                             // check for validity of the entered integer.
            {
                Cabin cabin = cabinsArray[cabinNum];                                                       // get the relevant Cabin object as cabin.
                int noOfPassengers = cabin.getNumberOfPassengersInTheCabin();                              // get noOfPassengers inside this cabin.
                if (noOfPassengers > 0)                                                                    // if cabin is not empty then, deletion is allowed.
                {
                    System.out.print("Delete Options For Cabin " + cabinNum + " (Enter q to Go Back) :\n" + "A: Delete All Passengers Inside Cabin " + cabinNum + "\n" + "P: Delete Specific Passenger From Cabin " + cabinNum + "\nEnter Your Option to Delete Passenger >");
                    String userInput = String.valueOf(sc.next().charAt(0));
                    if(userInput.equalsIgnoreCase("Q"))                                         // if user's input is Q or q then call this method again.
                    {
                        deleteCustomer();
                    }
                    else if (userInput.equalsIgnoreCase("A"))
                    {
                        cabin.deleteCabin();                                                                // delete all 3 passengers inside the cabin.
                        System.out.println("All Passengers in Cabin " + cabinNum + " Deleted Successfully");
                        addPassengersFromQueueToCabin(cabin);
                    }
                    else if (userInput.equalsIgnoreCase("P"))
                    {
                        Passenger passenger1 = cabin.getPassenger1();                                       // get all 3 passengers inside the user given( cabinNum ) cabin as passenger1, passenger2 and passenger3.
                        Passenger passenger2 = cabin.getPassenger2();
                        Passenger passenger3 = cabin.getPassenger3();
                        String instruction = "";                                                            // create an empty string as instruction.
                        System.out.println("Which Passenger Do You Want To Delete :\n");
                        if (noOfPassengers >= 1)
                        {
                            instruction += "1." + passenger1.getFirstName() + " " + passenger1.getSurName() + "\n";
                        }
                        if (noOfPassengers >= 2)
                        {
                            instruction += "2." + passenger2.getFirstName() + " " + passenger2.getSurName() + "\n";
                        }
                        if (noOfPassengers >= 3) {
                            instruction += "3." + passenger3.getFirstName() + " " + passenger3.getSurName() + "\n";
                        }
                        System.out.print(instruction + "\nEnter Passenger Number To Delete >");              // show instructions to delete a passenger.

                        int deleteKey = sc.nextInt();
                        if (deleteKey == 1)
                        {
                            passenger1.deletePassenger();                                                    // delete passenger1.
                            cabin.setPassenger1(passenger2);                                                 // set passenger2 as passenger1.
                            cabin.setPassenger2(passenger3);                                                 // set passenger3 as passenger2.
                            cabin.decrementNumberOfGuests();                                                 // call decrementNumberOfGuests() to decrease numberOfPassengersInTheCabin by 1.
                            System.out.println("Passenger 1 Successfully Deleted.");
                        }
                        else if (deleteKey == 2)
                        {
                            passenger2.deletePassenger();                                                    // delete passenger2.
                            cabin.setPassenger2(passenger3);                                                 // set passenger3 as passenger2.
                            cabin.decrementNumberOfGuests();                                                 // call decrementNumberOfGuests() to decrease numberOfPassengersInTheCabin by 1.
                            System.out.println("Passenger 2 Successfully Deleted");
                        }
                        else if (deleteKey == 3)
                        {
                            passenger3.deletePassenger();                                                    // delete passenger3.
                            cabin.decrementNumberOfGuests();                                                 // call decrementNumberOfGuests() to decrease numberOfPassengersInTheCabin by 1.
                            System.out.println("Passenger 3 Successfully Deleted");
                        }
                        else
                        {
                            System.out.println("Invalid Input1");
                        }
                        if(cabin.getNumberOfPassengersInTheCabin() == 0)
                        {
                            addPassengersFromQueueToCabin(cabin);
                        }
                    }
                    else                                                                                     // if user's input is not "A" or "P" then show an error.
                    {
                        System.out.println("Invalid Input2");
                    }
                }
                else                                                                                         // if noOfPassengers is 0 then show cabin is empty.
                {
                    System.out.println("Can't Proceed Your Request to deletion. The cabin " + cabinNum + " is Already Empty.");
                }
            }
            else if (cabinNum > cabinsArray.length)                                                          // if user's input is greater than 12 show an error.
            {
                System.out.println("The Entered Cabin Number Does Not Exist");
                deleteCustomer();
            }

        }
        catch (Exception e)                                                                                 // if any exception occurred when deleting customer catch block will run.
        {
            System.out.println("The Entered Cabin Number is Not Valid. Error :" + e);
            deleteCustomer();                                                                               // allow user to enter cabinNum again and delete customer.
        }
    }

    /**
     * findCustomerFromName method will find a customer using his firstname or surname.
     * if program couldn't find a matching record then show a message.
     */
    public void findCustomerFromName()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                          // create scanner object sc for findCustomerFromName() method.
            System.out.print("Find Customer Options (q to Go Back): \nF. Find Customer By First Name\nL. Find Customer By Last Name\nEnter Your Option to Find Customer >");
            String option = sc.next();
            if(option.equalsIgnoreCase("F") || option.equalsIgnoreCase("L") || option.equalsIgnoreCase("q"))
            {
                if(option.equalsIgnoreCase("q")){return;}                                      // if user's input is q then stop this method.
                System.out.print("Enter First/Last Name According to Your Selected Option : ");
                String findName = sc.next();
                boolean check = false;                                                                    // create a boolean variable to keep track whether the customer located or not.
                if (option.equalsIgnoreCase("F"))                                              // if user's input is "F" or "f" then,
                {
                    for (int i = 0; i < cabinsArray.length; i++)
                    {
                        if (findName.equalsIgnoreCase(cabinsArray[i].getPassenger1().getFirstName()) || findName.equalsIgnoreCase(cabinsArray[i].getPassenger2().getFirstName()) || findName.equalsIgnoreCase(cabinsArray[i].getPassenger3().getFirstName()))
                        {                                                                                          // check for a matching from all firstnames of the passengers.
                            System.out.println("Customer " + findName.toUpperCase() + " Located in cabin " + i);   // print the cabin number of customer matched to the user's input.
                            check = true;                                                                          // set check to true.
                        }
                    }
                }
                else                                                                                               // else part will run if user's input is "L" or "l".
                {
                    for (int i = 0; i < cabinsArray.length; i++)
                    {
                        if (findName.equalsIgnoreCase(cabinsArray[i].getPassenger1().getSurName()) || findName.equalsIgnoreCase(cabinsArray[i].getPassenger2().getSurName()) || findName.equalsIgnoreCase(cabinsArray[i].getPassenger3().getSurName()))
                        {
                            System.out.println("Customer " + findName.toUpperCase() + " Located in cabin " + i);   // print the cabin number of customer matched to the user's input.
                            check = true;                                                                          // set check to true.
                        }
                    }
                }
                if (!check)
                {
                    System.out.println("Customer can't find. Please check the name again.");
                    findCustomerFromName();                                                                // if program couldn't find the customer allow user to search again.
                }
            }
            else
            {
                System.out.println("Invalid Input");
            }
        }
        catch (Exception e)                                                                                // catch if any exception occurred while running findCustomerFromName()
        {
            System.out.println("The Entered Name is Not Valid. Please Try again. Error :" + e);
            findCustomerFromName();                                                                        // if the user's input is invalid allow user to search customer again.
        }
    }

    /**
     * saveToFile method will save cabin data to a text file.
     */
    public void saveToFile()
    {
        try
        {
            FileWriter myWriter = new FileWriter(fileName);                                               // creating a FileWriter object as myWriter.
            myWriter.write(cabinDetails("An Error Occurred While Writing File.").toString()); // write the returned textToWrite object content to file.
            myWriter.close();                                                                             // close FileWriter.
            System.out.println("Successfully wrote to the file " + fileName);
        }
        catch (IOException e)                                                                             // if an error occurred when writing file catch block will run.
        {
            System.out.println("Writing Data Failure. Error :" + e);
        }
    }

    /**
     * loadFromFile method will load the CabinInfo.txt content to the program and print it.
     */
    public void loadFromFile()
    {
        try
        {
            File inputFile = new File(fileName);                                                           // create a new File object as inputFile from File class.
            Scanner rf = new Scanner(inputFile);                                                           // create a new Scanner object rf from Scanner class.
            String fileLine;                                                                               // declaring string object as fileLine.
            while (rf.hasNext())                                                                           // repeat line by line until reach to the end of the document.
            {
                fileLine = rf.nextLine();                                                                  // read the line.
                System.out.println(fileLine);                                                              // print the line.
            }
            rf.close();                                                                                    // close the Scanner rf.
        }
        catch (IOException e)                                                                              // catch IOException if any error occurred when loading the file.
        {
            System.out.println("An Error Occurred While Reading The File. Error :" + e);
        }
    }

    //________________________________________________________________________Order alphabetically(sorting algorithm)________________________________________________________________________________

    /**
     * orderAlphabetically method will sort the Customer Names alphabetically and print them.
     * here we are applying Bubble Sort as the base sorting algorithm.
     */
    public void orderAlphabetically()
    {
        String[] cabinNamesOrdered = new String[36];                                                                    // create a String array of size 36.
        int index = 0;
        for (Cabin cabin : cabinsArray)                                                                                 // get Cabin object one by one from cabinsArray.
        {
            Passenger passenger1 = cabin.getPassenger1();                                                               // get all 3 passengers in the cabin.
            Passenger passenger2 = cabin.getPassenger2();
            Passenger passenger3 = cabin.getPassenger3();
            int noOfPassengers = cabin.getNumberOfPassengersInTheCabin();                                               // get no of passengers in the cabin.

            switch (noOfPassengers)                                                                                     // based on noOfPassengers in the cabin, concat first and surname and put it into the array cabinNamesOrdered.
            {
                case 3:
                {
                    cabinNamesOrdered[index] = passenger3.getFirstName() + " " + passenger3.getSurName();
                    index++;
                }
                case 2:
                {
                    cabinNamesOrdered[index] = passenger2.getFirstName() + " " + passenger2.getSurName();
                    index++;
                }
                case 1:
                {
                    cabinNamesOrdered[index] = passenger1.getFirstName() + " " + passenger1.getSurName();
                    index++;
                }
            }
        }

        for (int i = 0; i < cabinNamesOrdered.length; i++)                                    // outer for loop(0 to 11).
        {
            for (int j = 0; j < cabinNamesOrdered.length - (i + 1); j++)                      // inner for loop.
            {
                if (cabinNamesOrdered[j + 1] != null)
                {
                    if (compareNames(cabinNamesOrdered[j], cabinNamesOrdered[j + 1]) == 1)    // call compareNames method to compare two adjacent elements in the cabinNames array.
                    {
                        String temp = cabinNamesOrdered[j];                                   // swap values of cabinNamesOrdered[j] and cabinNamesOrdered[j+1].
                        cabinNamesOrdered[j] = cabinNamesOrdered[j + 1];
                        cabinNamesOrdered[j + 1] = temp;
                    }
                }
            }
        }

        boolean check = false;                                                            // create check variable to keep track of is it printing at least one name.
        // enhanced for loop(for each loop) to print the cabinNamesOrdered array elements.
        for (String s : cabinNamesOrdered)
        {
            if (s != null)                                                                // if s is not equal to "Empty" then,
            {
                System.out.println(s);                                                    // print the element(s).
                check = true;                                                             // set check as true.
            }
        }
        if (!check)                                                                       // if check is false then,
        {
            System.out.println("All Cabins are Empty. Nothing to Sort.");                 // print that nothing to sort.
        }
    }

    /**
     * compareNames method will compare two adjacent elements in the cabinNamesOrdered array.
     *
     * @param first  first element chosen from cabinNamesOrdered array to compare.
     * @param second second element chosen from cabinNamesOrdered array to compare.
     * @return an integer depending on comparison of two words letter by letter.
     */
    public int compareNames(String first, String second)
    {
        int len;                                                                          // create an int variable len.
        if (first.length() >= second.length())                                            // we need to take the smallest string length as len.
        {
            len = second.length();
        }
        else
        {
            len = first.length();
        }

        for (int i = 0; i < len; i++)                                                     // for loop to check letter by letter from both strings first and second.
        {
            if (first.charAt(i) > second.charAt(i))
            {
                return 1;                                                                 // return  1.
            }
            else if (first.charAt(i) < second.charAt(i))
            {
                return -1;                                                                // return -1.
            }
        }
        return 0;                                                                         // return 0 if both (string first and string second) are equal.
    }

    //________________________________________________________________________________________________________________________________________________________________________________________________

    /**
     * exit method will terminate the program using exit code 0.
     */
    public void exit()
    {
        System.exit(0);
    }

    /**
     * this method will update the expenses of passengers.
     */
    public void updateExpenses()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                             // create scanner object sc for addCustomerToCabin() method.
            System.out.print("Enter Cabin Number to Update Expenses (12 to Go Back) :");
            int cabinNum = sc.nextInt();                                                                     // get cabinNum as int.
            if (cabinNum < cabinsArray.length)
            {
                Cabin cabin = cabinsArray[cabinNum];                                                         // create a cabin object based on cabin number user entered.
                int noOfPassengers = cabin.getNumberOfPassengersInTheCabin();
                if (noOfPassengers > 0)
                {
                    Passenger passenger1 = cabin.getPassenger1();                                            // get all 3 passengers in the cabin.
                    Passenger passenger2 = cabin.getPassenger2();
                    Passenger passenger3 = cabin.getPassenger3();
                    String instruction = "";
                    System.out.println("Which Passenger Expense Do You Want Update :\n");

                    instruction += "1." + passenger1.getFirstName() + " " + passenger1.getSurName() + "\n";
                    if (noOfPassengers >= 2)
                    {
                        instruction += "2." + passenger2.getFirstName() + " " + passenger2.getSurName() + "\n";
                    }
                    if (noOfPassengers >= 3)
                    {
                        instruction += "3." + passenger3.getFirstName() + " " + passenger3.getSurName() + "\n";
                    }

                    System.out.print(instruction + "\nEnter Passenger Number To Update Expenses >");         // print a list of passengers and ask which passenger expense do you want to update.
                    int updateKey = sc.nextInt();
                    if(updateKey <= noOfPassengers)
                    {
                        System.out.print("Enter Expense ( Use - Prefix to Reduce Existing Expenses ) : ");
                        double updateExpense = sc.nextDouble();

                        if (updateKey == 1)                                                                  // according to user's input update expenses.
                        {
                            passenger1.setExpenses(passenger1.getExpenses() + updateExpense);
                            System.out.println("Expenses Successfully Updated For " + passenger1.getFirstName() + " " + passenger1.getSurName() + " By " + updateExpense + " $");
                        }
                        else if (updateKey == 2)
                        {
                            passenger2.setExpenses(passenger2.getExpenses() + updateExpense);
                            System.out.println("Expenses Successfully Updated For " + passenger2.getFirstName() + " " + passenger2.getSurName() + " By " + updateExpense + " $");
                        }
                        else if (updateKey == 3)
                        {
                            passenger3.setExpenses(passenger3.getExpenses() + updateExpense);
                            System.out.println("Expenses Successfully Updated For " + passenger3.getFirstName() + " " + passenger3.getSurName() + " By " + updateExpense + " $");
                        }
                    }                                                                                        // if update key is greater than noOfPassengers show an error.
                    else
                    {
                        System.out.println("Invalid Input");
                    }
                }
                else                                                                                         // if noOfPassengers is 0 Cabin is already empty.
                {
                    System.out.println("Can't Proceed Your Request to Update Expenses. The cabin " + cabinNum + " is Already Empty.");
                }
            }
            else if(cabinNum > cabinsArray.length)                                                           // if user entered value is not a valid cabin number show an error.
            {
                System.out.println("The Entered Cabin Number Does Not Exist");
            }

        }
        catch(Exception e)                                                                            // if any exception occurred when updating customer expenses catch block will run.
             {
                System.out.println("The Entered Cabin Number is Not Valid. Error :" + e);
                 updateExpenses();                                                                    // allow user to enter cabinNum again and update expenses.
             }

    }

    /**
     * cabinDetails method will return a StringBuilder object(textToWrite) which contains cabin data.
     * @param exceptionDetails String which contains an error msg to handle errors.
     * @return textToWrite StringBuilder object which contains all cabin & passenger data including expenses.
     */
    public StringBuilder cabinDetails(String exceptionDetails)
    {
        StringBuilder textToWrite = new StringBuilder();
        double totalExpenseForAllCabins = 0.0;
        try
        {
            for (int i = 0; i < cabinsArray.length; i++)
            {
                int noOfPassengers = cabinsArray[i].getNumberOfPassengersInTheCabin();                                  // get no of passengers in the cabin.
                if (noOfPassengers == 0)
                {
                    textToWrite.append("Cabin ").append(i).append(" is ").append("Empty\n");                            // if it is 0 then cabin is empty.
                }
                else                                                                                                    // if noOfPassengers is not 0 then,
                {
                    textToWrite.append("Cabin ").append(i).append(" is Occupied by : \n");
                    Passenger passenger1 = cabinsArray[i].getPassenger1();                                              // get all 3 passengers to Passenger objects.
                    Passenger passenger2 = cabinsArray[i].getPassenger2();
                    Passenger passenger3 = cabinsArray[i].getPassenger3();
                    if (noOfPassengers >= 1)                                                                            // based on noOfPassengers append details to textToWrite.
                    {
                        textToWrite.append(" | ").append(passenger1.getFirstName()).append(" ").append(passenger1.getSurName()).append(" - ").append("Expenses :").append(passenger1.getExpenses()).append(" $ | \n");
                    }
                    if (noOfPassengers >= 2)
                    {
                        textToWrite.append(" | ").append(passenger2.getFirstName()).append(" ").append(passenger2.getSurName()).append(" - ").append("Expenses :").append(passenger2.getExpenses()).append(" $ | \n");
                    }
                    if (noOfPassengers >= 3)
                    {
                        textToWrite.append(" | ").append(passenger3.getFirstName()).append(" ").append(passenger3.getSurName()).append(" - ").append("Expenses :").append(passenger3.getExpenses()).append(" $ | \n");
                    }
                    double totalExpenseForCabin = passenger1.getExpenses() + passenger2.getExpenses() + passenger3.getExpenses();
                    totalExpenseForAllCabins += totalExpenseForCabin;
                    textToWrite.append(" [ The Total Expense For cabin ").append(i).append(" : ").append(totalExpenseForCabin).append(" $ ]\n");
                }
            }
            textToWrite.append(" __________________ The Total Expense For All 12 Cabins is :").append(totalExpenseForAllCabins).append(" $ __________________\n");
        }
        catch (Exception e)                                                                                             // if any exception occurred, this block will run.
        {
            System.out.println( exceptionDetails + " Error :" + e);
        }
        return textToWrite;                                                                                             // return textToWrite StringBuilder object.
    }

    /**
     * this method will print cabin details with expenses per passenger,
     * total expense per cabin and total expense for all 12 cabins.
     */
    public void showExpenses()
    {
        System.out.println(cabinDetails("An Error Occurred While Retrieving Data.").toString());
    }

    /**
     *  this method will print the waiting list.
     */
    public void showQueue()
    {
        circularQueue.traverse();
    }

    /**
     * this method will assign a passenger from queue to a cabin.
     * @param cabin the cabin we want to add a passenger.
     */
    public void addPassengersFromQueueToCabin(Cabin cabin)
    {
        if(circularQueue.isEmpty() == 0)
        {
            int noOfPassengersInTheQueue = circularQueue.getNoOfPassengersInTheQueue();            // get number of passengers in the queue to noOfPassengersInTheQueue integer.
            if(noOfPassengersInTheQueue >= 1 )
            {
                cabin.setPassenger1(circularQueue.deQueue());                                      // add passenger from queue to the cabin and dequeue from circular queue.
                cabin.incrementNumberOfGuests();                                                   // increase number of passengers in the cabin.
                circularQueue.decreaseNoOfPassengersInTheQueue();                                  // decrease number of passengers in the queue.
            }
            if(noOfPassengersInTheQueue >= 2)
            {
                cabin.setPassenger2(circularQueue.deQueue());                                      // add passenger from queue to the cabin and dequeue from circular queue.
                cabin.incrementNumberOfGuests();                                                   // increase number of passengers in the cabin.
                circularQueue.decreaseNoOfPassengersInTheQueue();                                  // decrease number of passengers in the queue.
            }
            if(noOfPassengersInTheQueue >= 3)
            {
                cabin.setPassenger3(circularQueue.deQueue());                                      // add passenger from queue to the cabin and dequeue from circular queue.
                cabin.incrementNumberOfGuests();                                                   // increase number of passengers in the cabin.
                circularQueue.decreaseNoOfPassengersInTheQueue();                                  // decrease number of passengers in the queue.
            }
        }
    }

    /**
     * callActions method will call the relevant method depending on user's input.
     * @param UsrInput userInput to be used to run the relevant method according to the user's input.
     */
    public void callActions(String UsrInput)
    {
        // enhanced switch-case
        switch (UsrInput)
        {
            case "M" -> showMenu();
            case "A" -> addCustomerToCabin();
            case "V" -> viewAllCabins();
            case "E" -> displayEmptyCabins();
            case "D" -> deleteCustomer();
            case "F" -> findCustomerFromName();
            case "S" -> saveToFile();
            case "L" -> loadFromFile();
            case "O" -> orderAlphabetically();
            case "U" -> updateExpenses();
            case "T" -> showExpenses();
            case "X" -> exit();
            case "W" -> showQueue();
            default  -> System.out.println("Invalid Input.");                             // if user's input does not match for any of these, it will print this.
        }
    }

}
