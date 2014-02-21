/*
	Developed by Luca Severini
*/

import java.util.*;
/**
 * Write a description of class ShortestRemainingTime here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShortestRemainingTime
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class ShortestRemainingTime
     */
    public ShortestRemainingTime()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
	
	public static void main (String [] args)
	{
		ProcessGenerator procGen = new ProcessGenerator(100, 1);
		ArrayList<Process> procs = procGen.generateProcesses();
		
		System.out.println("xx");
  	}
}
