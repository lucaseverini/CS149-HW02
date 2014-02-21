/*
 ddeveloped by Arash
 */

import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class simulates the first come first served (FCFS) algorithm It receives
 * the arrayList with 100 processes that have already been initialized. It then
 * simulates the FCFS algorithm, and prints out results
 *
 * The first come first served algorithm sorts the array list based on time of
 * arrival
 *
 *
 */
public class FirstComeFirstServed {

    // instance variables - replace the example below with your own
    private ArrayList<Process> processArrayList;
    private String content;

    /**
     * Constructor for objects of class FirstComeFirstServed
     */
    public FirstComeFirstServed(ArrayList<Process> processArrayList) {
        // initialise instance variables
        this.processArrayList = processArrayList;
    }

    public void simulateFCFS() {

        int numProcesses = 0;
        int quantum = 0;
        double timeRemaining = 0;
        String timeChart = "";
        Process currentProcess = processArrayList.get(numProcesses);//store first process
        timeRemaining = currentProcess.getExpectedTime();
        boolean oldProcess = true;
        boolean processRunning = true;

        while (processRunning) {//as long as there are processes being done continue processing
            //if the current process has an arival time past than number of elapsed quantums
            if (currentProcess.getArrivalTime() > quantum) {
                quantum++;//good until here///////////////////////
            } else {
                currentProcess.addToTurnaroundTime(1);//add to current processes turnaround time
                timeChart += currentProcess.getName();//for printing out the chart of names
                timeRemaining -= 1.0;
                //if current process ends and still less than 100 quanta elapsed, start new process
                if (timeRemaining < 0 && quantum < 99) {
                    numProcesses++;
                    currentProcess = processArrayList.get(numProcesses);
                } else if (timeRemaining < 0 && quantum >= 99) {
                    //if current process ends and is past 100 quanta, stop working
                    processRunning = false;
                }
                quantum++;
            }

        }

    }

    /**
     * Constructor for objects of class FirstComeFirstServed
     */
    public void introduceProcess() {
        int letterCounter = 65;
        int firstNumber = 1;

        content += "First Come First Serve: \n";
        for (int i = 0; i < processArrayList.size(); i++) {
            content += processArrayList.get(i).toString();
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     */
    public void displayProcesses() {
        introduceProcess();

        try {
            File file = new File("test", "newFile.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            //parameter 'content' is content to be written to file
            //should probably store in a string, then put here.
            bw.write(content);
            bw.close();
            System.out.println("\nFirst Come First Serve has been printed to file. \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
