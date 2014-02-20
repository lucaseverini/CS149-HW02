/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Round Robin is preemptive
 * Round Robin completes high priority jobs before low priority ones
 *
 * @RomeoStevens
 */
public class RoundRobin
{
    // instance variables
    private ArrayList<Process> processArrayList;
    private String content; 

    /**
     * Constructor for objects of class RoundRobin
     */
    public RoundRobin(ArrayList<Process> processArrayList)
    {
        // initialise instance variables
       this.processArrayList = processArrayList;
    }

  
    public int RR(int y)
    {
        return y + y;
    }
}
