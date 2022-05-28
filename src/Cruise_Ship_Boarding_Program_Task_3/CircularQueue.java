package Cruise_Ship_Boarding_Program_Task_3;

/**
 COPYRIGHT (C) Sandeepa Induwara Samaranayake - 20210302 - W1867067- sandeepa.20210302@iit.ac.lk. All Rights Reserved.
 Task II  Boarding System for a Cruise ship in java
 SDII Coursework L4 Sem2
 @author Sandeepa Induwara Samaranayake
 @version 3 - classes solution with a Circular Queue - CircularQueue class 2022-04-02
 */
public class CircularQueue
{
    private final int len = 10;
    private int rear;
    private int front;
    private final Passenger [] passengerQueue = new Passenger[len];
    private int noOfPassengersInTheQueue = 0;

    /**
     * CircularQueue constructor
     */
    public CircularQueue()
    {
        setFront(-1);
        setRear(-1);
    }

    /**
     * this method will check the queue is full or not.
     * if the queue is full it will return 1, otherwise it will return 0;
     * @return 1 if the queue is full, otherwise 0
     */
    public int isFull()
    {
        if(getFront() == 0 && getRear() == len - 1 || getFront() == getRear() + 1)
        {
            return 1;
        }
        return 0;
    }

    /**
     * this method will check the queue is empty or not.
     * if the queue is empty it will return 1, otherwise it will return 0;
     * @return 1 if the queue is empty, otherwise 0.
     */
    public int isEmpty()
    {
        if(getFront() == -1)
        {
            return 1;
        }
        return 0;
    }

    /**
     * this is the method to enqueue the circular queue.
     * @param passenger the Passenger object to enqueue the circular queue.
     */
    public void enQueue(Passenger passenger)
    {
        if(isFull() == 1)
        {
            System.out.println("Waiting List is Full");
        }
        else
        {
            if(getFront() == -1)
            {
                setFront(0);
            }
            setRear((getRear() + 1) % len);
            getPassengerQueue()[getRear()] = passenger;
        }
    }

    /**
     * this is the method to dequeue the circular queue.
     * @return null if the circular queue is empty, otherwise it will return the dequeued item.
     */
    public Passenger deQueue()
    {
        Passenger item;
        if(isEmpty() == 1)
        {
            System.out.println("Waiting List is Empty");
            return null;
        }
        else
        {
            item = getPassengerQueue()[getFront()];
            if(getFront() == getRear())
            {
                setFront(-1);
                setRear(-1);
            }
            else
            {
                setFront((getFront() + 1) % len);
            }
            return item;
        }
    }

    /**
     * this method will print the circular queue
     */
    public void traverse()
    {
        int i;
        if(isEmpty() == 1)
        {
            System.out.println("Waiting List is Empty.");
        }
        else
        {
            System.out.print("Front: ");
            for (i = getFront(); i != getRear(); i = (i + 1) % len)
                System.out.print("| " + getPassengerQueue()[i].getFirstName() + " " + getPassengerQueue()[i].getSurName() + " |");
            System.out.print("| " + getPassengerQueue()[i].getFirstName() + " " + getPassengerQueue()[i].getSurName() + " |");
            System.out.println(" :Rear" );
        }
    }

    /**
     * getter for passengerQueue.
     * @return passengerQueue
     */
    public Passenger[] getPassengerQueue()
    {
        return passengerQueue;
    }

    /**
     * getter for rear.
     * @return rear position of the circular queue
     */
    public int getRear()
    {
        return rear;
    }

    /**
     * getter for front.
     * @return front position of the circular queue.
     */
    public int getFront()
    {
        return front;
    }

    /**
     * setter for rear.
     * @param rear rear position of the circular queue.
     */
    public void setRear(int rear)
    {
        this.rear = rear;
    }

    /**
     * setter for front.
     * @param front front position of the circular queue.
     */
    public void setFront(int front)
    {
        this.front = front;
    }

    /**
     * this method will return the number of passengers in the queue.
     * @return number of passengers in the queue.
     */
    public int getNoOfPassengersInTheQueue()
    {
        return noOfPassengersInTheQueue;
    }

    /**
     * this method will increase the number of passengers in the queue by 1.
     */
    public void increaseNoOfPassengersInTheQueue()
    {
        this.noOfPassengersInTheQueue++;
    }

    /**
     * this method will decrease the number of passengers in the queue by 1.
     */
    public void decreaseNoOfPassengersInTheQueue()
    {
        this.noOfPassengersInTheQueue--;
    }
}
