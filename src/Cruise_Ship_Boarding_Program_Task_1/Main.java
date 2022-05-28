
package Cruise_Ship_Boarding_Program_Task_1;

import java.io.File;                                                             // import java.io.File class.
import java.io.FileWriter;                                                       // import java.io.FileWriter class.
import java.io.IOException;                                                      // Import the IOException class to handle errors. or can import whole (java.io.*) package.
import java.util.*;                                                              // import java util, whole package.

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Task I  Boarding System for a Cruise ship in java
 SDII Coursework L4 Sem2
 @author Sandeepa Induwara Samaranayake
 @version 1 2022-03-30
 */

public class Main
{
    private final String[] cabinsInfo = new String[12];                          // create a string array of size 12 as cabinsInfo. we are making cabinsInfo array final
                                                                                 // but still we can modify and delete array elements, because arrays are objects, and they
                                                                                 // do not hold values Instead, they point to the address of the locations that holds values.
                                                                                 // In the case of objects and arrays if a reference variable is final it cannot point to
                                                                                 // another object/array. simply we can't assign new array to cabinsInfo because it is final.
    private static final Main cruiseShipClass = new Main();                      // create a final class object of Main as cruiseShipClass, it is static, so all methods including main() can access it.
    private final String fileName = "CabinInfo.txt";                             // create a fileName as a final string variable.


    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);                                     // create scanner object sc for main method.

        // we have to use class object to access the cabinsInfo array because being inside a static method we can't access nonstatic variables or arrays.
        for( int i=0; i < cruiseShipClass.cabinsInfo.length; i++ )
        {
            cruiseShipClass.cabinsInfo[i] = "Empty";                             // set all array elements to string "Empty".
        }

        cruiseShipClass.showMenu();                                              // have to use class object because we can't call nonstatic method inside a static method. call ShowMenu() method.
        while(true)
        {
            System.out.print("Enter your option :");
            String userInput = String.valueOf( sc.next().charAt(0) ).toUpperCase();          // get user input first letter and convert it into uppercase and also to a string.
            cruiseShipClass.callActions(userInput);                                          // callActions is not a static method. so we should call that method through class object.
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
                E: Display Empty cabins
                D: Delete customer from cabin
                F: Find cabin from customer name
                S: Store program data into file
                L: Load program data from file
                O: View passengers Ordered alphabetically by name
                X: Exit
                ________________________________________________________________
                """);
    }

    /**
     * viewAllCabins method will display all the cabin status, whether they are empty or occupied by someone.
     */
    public void viewAllCabins()
    {
        for (int i = 0; i < cabinsInfo.length; i++ )
        {
            if(!cabinsInfo[i].equals("Empty"))                                                             // if cabinsInfo[i] is not equals to "Empty".
            {
                System.out.println("Cabin " + i + " is occupied by " + cabinsInfo[i]);
            }
            else                                                                                           // if cabinsInfo[i] is equals to "Empty".
            {
                System.out.println("Cabin " + i + " is " + cabinsInfo[i]);
            }
        }
    }

    /**
     * displayEmptyCabins method will only display the empty cabins.
     */
    public void displayEmptyCabins()
    {
        for (int i = 0; i < cabinsInfo.length; i++ )
        {
            if (cabinsInfo[i].equals("Empty"))                                                             // check every cabinInfo element for equality of String "Empty"
            {
                System.out.println("Cabin " + i + " is Empty");
            }
        }
    }

    /**
     * addCustomerToCabin method will add a customer to a cabin.
     * it will ask a cabin number which you wish add the customer and his name.
     * if the given cabin is empty then it will add customer to that cabin otherwise print already occupied.
     */
    public void addCustomerToCabin()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                           // create scanner object sc for addCustomerToCabin() method.
            System.out.print("Enter Cabin Number (0-11) to Add a Customer (Enter 12 to Go Back):");
            int cabinNum = sc.nextInt();                                                                   // get cabinNum as an int. if pass anything other than an int will trigger catch block.
            if(cabinNum == 12) {/* if cabinNum is 12 then do nothing and let method end without doing anything.*/}
            else if (cabinsInfo[cabinNum].equals("Empty"))                                                 // if the requested cabin is empty, then ask for name. and add to the array.
            {
                System.out.print("Enter Customer Name For Cabin " + cabinNum + " :");
                String cabinCustomerName = sc.next();                                                      // get customer name for cabin as a String.
                cabinsInfo[cabinNum] = cabinCustomerName.toUpperCase();
                System.out.println("Customer " + cabinCustomerName.toUpperCase() + " Added to Cabin " + cabinNum + " Successfully");
            }
            else
            {
                System.out.println("This Cabin is Already occupied by " + cabinsInfo[cabinNum]);           // if cabin is not empty then print a message.
                addCustomerToCabin();                                                                      // allow user to add a customer again.
            }
        }
        catch (Exception e)
        {
            System.out.println("The Entered Value can't be proceed. Please Enter a valid Cabin Number. Error :" + e);
            addCustomerToCabin();                                                                          // if any exception occurred when adding a customer, then allow user to add a customer again.
        }
    }

    /**
     * deleteCustomer method will delete a user using cabin number.
     * if the given cabin is empty then show a message that cabin is already empty, otherwise it will delete customer record.
     */
    public void deleteCustomer()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                           // create scanner object sc for addCustomerToCabin() method.
            System.out.print("Enter Cabin Number to Delete (Enter 12 to Go Back):");
            int cabinNum = sc.nextInt();                                                                   // get cabinNum as int.
            if(cabinNum == 12) {/* if cabinNum is 12 then do nothing and let method end without doing anything.*/}
            else if (cabinNum < cabinsInfo.length)                                                         // if cabinNum < cabinsInfo.length then
            {
                if(cabinsInfo[cabinNum].equals("Empty"))                                                   // check whether the cabin is empty or not. if empty then, print error
                {
                    System.out.println("Can't Proceed Your Request to deletion. The cabin " + cabinNum + " is already empty.");
                }
                else                                                                                       // if cabin is not empty then
                {
                    cabinsInfo[cabinNum] = "Empty";                                                        // make cabin empty.
                    System.out.println("Customer Deleted successfully");
                }
            }
            else
            {
                System.out.println("The Entered Cabin Number Does Not Exist");
                deleteCustomer();                                                                          // allow user to enter cabinNum again and delete customer.
            }
        }
        catch(Exception e)                                                                                 // if any exception occurred when deleting customer catch block will run.
        {
            System.out.println("The Entered Cabin Number is Not Valid. Error :" + e);
            deleteCustomer();                                                                              // allow user to enter cabinNum again and delete customer.
        }
    }

    /**
     * findCustomerFromName method will find a customer using his name.
     * if program couldn't find a matching record then show a message.
     */
    public void findCustomerFromName()
    {
        try
        {
            Scanner sc = new Scanner(System.in);                                                           // create scanner object sc for findCustomerFromName() method.
            System.out.print("Enter Name to Find a Customer (q to Go Back):");
            String findName = sc.next();
            boolean check = false;                                                                         // create a boolean variable to keep track whether the customer located or not.
            if(findName.equalsIgnoreCase("q")) {return;}                                        // if the user input is "q" or "Q" then exit from this method.
            for (int i = 0; i < cabinsInfo.length; i++)
            {
                if (findName.toUpperCase().equals(cabinsInfo[i]))
                {
                    System.out.println("Customer " + findName.toUpperCase() + " Located in cabin " + i);   // print the cabin number of customer matched to the user's input.
                    check = true;                                                                          // set check to true.
                }
            }
            if (!check)
            {
                System.out.println("Customer can't find. Please check the name again.");
                findCustomerFromName();                                                                   // if program couldn't find the customer allow user to search again.
            }
        }
        catch(Exception e)                                                                                // catch if any exception occurred while running findCustomerFromName()
        {
            System.out.println("The Entered Name is Not Valid. Please Try again. Error :" + e);
            findCustomerFromName();                                                                       // if the user's input is invalid allow user to search customer again.
        }
    }

    /**
     * saveToFile method will save cabinsInfo array data to a text file.
     */
    public void saveToFile()
    {
        try
        {
            StringBuilder textToWrite = new StringBuilder();                                              // here we are creating a new StringBuilder object from StringBuilder class.
            FileWriter myWriter = new FileWriter(fileName);                                               // creating a FileWriter object as myWriter.
            for(int i = 0; i < cabinsInfo.length; i++)
            {
                if (cabinsInfo[i].equals("Empty"))
                {
                    textToWrite.append("Cabin ").append(i).append(" is ").append(cabinsInfo[i]).append("\n");
                }
                else
                {
                    textToWrite.append("Cabin ").append(i).append(" is Occupied by ").append(cabinsInfo[i]).append("\n");
                }
            }
            myWriter.write(textToWrite.toString());                                                       // write textToWrite object content to file.
            myWriter.close();                                                                             // close FileWriter.
            System.out.println("Successfully wrote to the file " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("An Error Occurred While Writing File. Error :" + e);
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
            while(rf.hasNext())                                                                            // repeat line by line until reach to the end of the document.
            {
                fileLine = rf.nextLine();                                                                  // read the line.
                System.out.println(fileLine);                                                              // print the line.
            }
            rf.close();                                                                                    // close the Scanner rf.
        }
        catch(IOException e)                                                                               // catch IOException if any error occurred when loading the file.
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
        String [] cabinNamesOrdered = cabinsInfo.clone();                                 // create a clone of cabinsName array and initialize it to a new array cabinNamesOrdered. otherwise, it will change the original array.
        for(int i = 0; i < cabinNamesOrdered.length; i++)                                 // outer for loop(0 to 11).
        {
            for(int j = 0; j < cabinNamesOrdered.length - (i + 1); j++)                   // inner for loop.
            {
                if (compareNames(cabinNamesOrdered[j], cabinNamesOrdered[j + 1]) == 1)    // call compareNames method to compare two adjacent elements in the cabinNames array.
                {
                    String temp = cabinNamesOrdered[j];                                   // swap values of cabinNamesOrdered[j] and cabinNamesOrdered[j+1].
                    cabinNamesOrdered[j] = cabinNamesOrdered[j + 1];
                    cabinNamesOrdered[j + 1] = temp;
                }
            }
        }
        boolean check = false;                                                            // create check variable to keep track of is it printing at least one name.
        for (String s : cabinNamesOrdered)                                                // enhanced for loop(for each loop) to print the cabinNamesOrdered array elements.
        {
            if(!s.equals("Empty"))                                                        // if s is not equal to "Empty" then,
            {
                System.out.println(s);                                                    // print the element(s).
                check = true;                                                             // set check as true.
            }
        }
        if(!check)                                                                        // if check is false then,
        {
            System.out.println("All Cabins are Empty. Nothing to Sort.");                 // print that nothing to sort.
        }
    }

    /**
     * compareNames method will compare two adjacent elements in the cabinNamesOrdered array.
     * @param first first element chosen from cabinNamesOrdered array to compare.
     * @param second second element chosen from cabinNamesOrdered array to compare.
     * @return an integer depending on comparison of two words letter by letter.
     */
    public int compareNames(String first , String second)
    {
        int len;                                                                          // create an int variable len.
        if(first.length() >= second.length())                                             // we need to take the smallest string length as len.
            len=second.length();
        else
            len= first.length();

        for(int i = 0; i < len; i++)                                                      // for loop to check letter by letter from both strings first and second.
        {
            if(first.charAt(i) > second.charAt(i))
                return 1;                                                                 // return  1.
            else if(first.charAt(i) < second.charAt(i))
                return -1;                                                                // return -1.
        }
        return  0;                                                                        // return 0 if both (string first and string second) are equal.
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
            case "X" -> exit();
            default  -> System.out.println("Invalid Input.");                             // if user's input does not match for any of these, it will print this.
        }
    }

}
