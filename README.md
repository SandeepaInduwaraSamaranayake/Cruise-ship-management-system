# Cruise-ship-management-system
This repository consists of Java program for a Cruise Ship management System. This is a basic menu-driven command line program which I created as my Level 4 Sem 2 Software Development II coursework at University of Westminster. This program can manage activities like,<br>
<br> 1) Allocate cabins for passengers 
<br> 2) Delete passengers from cabins 
<br> 3) Keep records of passengers' expences
<br> 4) Update expences passengerwise
<br> 4) Find passengers by using either first name or last name
<br> 5) View all cabin status
<br> 6) Display empty cabins
<br> 7) Save all customer data into a text(.txt) file
<br> 8) Load all data back again into the program
<br> 9) Order all passenger names alphabetically
<br><br>
In this program exception handling is properly done to deal with any kind of unexpected exception. Basically this program contains three main versions(as Packages).<br>
<br> 1) Cruise_Ship_Boarding_Program_Task_1 - This is a basic version of the program with limited features. This version allows you to populate twelve cabins, one passenger for each cabin and perform basic activities like add & delete passengers, Display empty cabins, view all cabin status etc.<br>

### a ) Menu example<br>
______________BOARDING SYSTEM FOR A CRUISE SHIP_________________<br>

*M: Show Menu<br>
A: Add a Customer to a Cabin<br>
V: View All Cabins<br>
E: Display Empty cabins<br>
D: Delete customer from cabin<br>
F: Find cabin from customer name<br>
S: Store program data into file<br>
L: Load program data from file<br>
O: View passengers Ordered alphabetically by name<br>
X: Exit*<br> 
________________________________________________________________<br><br>

### b ) Add a customer to a cabin.<br>

Enter your option :A<br>
Enter Cabin Number (0-11) to Add a Customer (Enter 12 to Go Back):5<br>
Enter Customer Name For Cabin 5 :John Garix<br>
Customer JOHN Added to Cabin 5 Successfully<br>
Enter your option :<br>

### c ) View cabins<br>

Enter your option :v<br>
Cabin 0 is Empty<br>
Cabin 1 is Empty<br>
Cabin 2 is Empty<br>
Cabin 3 is Empty<br>
Cabin 4 is Empty<br>
Cabin 5 is occupied by JOHN<br>
Cabin 6 is Empty<br>
Cabin 7 is Empty<br>
Cabin 8 is Empty<br>
Cabin 9 is Empty<br>
Cabin 10 is Empty<br>
Cabin 11 is Empty<br>
Enter your option :<br>

### d ) Display empty cabins<br>

Cabin 0 is Empty<br>
Cabin 1 is Empty<br>
Cabin 2 is Empty<br>
Cabin 3 is Empty<br>
Cabin 4 is Empty<br>
Cabin 6 is Empty<br>
Cabin 7 is Empty<br>
Cabin 8 is Empty<br>
Cabin 9 is Empty<br>
Cabin 10 is Empty<br>
Cabin 11 is Empty<br>

### 

<br> 2) Cruise_Ship_Boarding_Program_Task_2 - This version can hold upto 36 passengers, as 3 passengers per cabin. This version will hold passenger's first name, surname and expences and this consist an sdditional menu item "T" which will give the user the option to print the expenses per passenger as well as the total expenses of all passengers.<br>
<br> 3) Cruise_Ship_Boarding_Program_Task_3 - As an additional feature, a waiting list of 10 slots is added to the cruise ship. If the cruise ship is full(all cabins are occupied by passengers), then passengers will be added to the waiting list.

Definition of "cabin is full" - if at least one passenger is in the cabin, the program will consider it as an already occupied cabin.<br>

### Technology
Java 16 or higher

### IDE 
IntelliJ IDEA

### How to run the program

1) First you need to clone this repository
2) Run this project as part of java code through IntelliJ IDEA.

#### Note - If this project helped you, kindly don't forget to give this repository a star that motivates me to work further and improve this project.


