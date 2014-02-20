/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class generates a fixed number of processes that will be
 * dictated by the class that initializes it. The method that generates
 * these processes will return an array list of Process objects.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ProcessGenerator
{
    // instance variables - replace the example below with your own
    private int numProcesses;//Number of Process objects in array
    private ArrayList<Process> processArrayList;
    private Random generator;
    private Process newProcess;

    /**
     * Constructor: objects of ProcessGenerator
     * numProcesses is passed in as the number of processes needed to
     * be generated
     * @param numProcesses: an integer, number of processes to be created
     */
    public ProcessGenerator(int numProcesses)
    {
        // initialise instance variables
        this.numProcesses = numProcesses;
        processArrayList = new ArrayList<Process>();

    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y   a sample parameter for a method
     * @return     an arraylist Process objects sorted by arrival time
     */
    public ArrayList<Process> generateProcesses()
    {
        float arrivalTime;
        float expectedTime;
        int priority;
        generator = new Random(1); //paramater is seed used for random number generator

        for(int i = 0; i < numProcesses; i++){
            //create random number for arrival time
            arrivalTime = (float)(generator.nextDouble() * 99.0);
            //create random number for expected run time
            expectedTime = (float)(generator.nextDouble() * 9.9 + 0.1);
            //create random number for priority
            priority = generator.nextInt(4) + 1;
            //create new Process object and pass in these three
            newProcess = new Process(arrivalTime, expectedTime, priority);
            //put the Process object in array processArray using i as index
            processArrayList.add(newProcess);
            System.out.println(processArrayList.get(i).getArrivalTime()
                + "  " + processArrayList.get(i).getExpectedTime()
                + "  " + processArrayList.get(i).getPriority());
        }
        System.out.println("\n  ");

        Collections.sort(processArrayList, new Comparator<Process>()
            {
                public int compare(Process process1, Process process2)
                {
                    if (process1.getArrivalTime() < process2.getArrivalTime()) return -1;
                    if (process1.getArrivalTime() > process2.getArrivalTime()) return +1;
                    return 0;
                }
            });

        for(int i = 0; i < numProcesses; i++){
            System.out.println("arrival time:  " + processArrayList.get(i).getArrivalTime()
                + "  " + processArrayList.get(i).getExpectedTime()
                + "  " + processArrayList.get(i).getPriority());
        }

        return processArrayList;
    }
}
