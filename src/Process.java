/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 * Write a description of class Process here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Process
{
    // instance variables - replace the example below with your own
    private float arrivalTime;//between 0 and 99
    private float expectedTime;//between 0.1 and 10
    private int priority;//1, 2, 3. or 4

    /**
     * Constructor for objects of class Process
     */
    public Process(float arrivalTime, float expectedTime, int priority)
    {
        this.arrivalTime = arrivalTime;
        this.expectedTime = expectedTime;
        this.priority = priority;
    }

    /**
     * This returns the arrival time value
     *
     * @return     value of arrivalTime
     */
    public float getArrivalTime()
    {
        // put your code here
        return arrivalTime;
    }

    /**
     * This returns the expected time value
     *
     * @return     value of arrivalTime
     */
    public float getExpectedTime()
    {
        // put your code here
        return expectedTime;
    }

    /**
     * This returns the priority value
     *
     * @return     value of priority
     */
    public int getPriority()
    {
        // put your code here
        return priority;
    }

    /**
     * This returns the arrival time value
     *
     * @return     value of arrivalTime
     */
    public void setArrivalTime(float arrivalTime)
    {
        // put your code here
        this.arrivalTime = arrivalTime;
    }

    /**
     * This returns the expected time value
     *
     * @return     value of arrivalTime
     */
    public  void setExpectedTime(float expectedTime)
    {
        // put your code here
        this.expectedTime = expectedTime;;
    }

    /**
     * This returns the priority value
     *
     * @return     value of priority
     */
    public void setPriority(int priority)
    {
        // put your code here
        this.priority = priority;
    }

}
