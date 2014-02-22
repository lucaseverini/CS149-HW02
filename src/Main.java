/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author arashzahoory
 */
public class Main {

    public static void main(String[] args) {
        printToFile printer = new printToFile();
        String simulationString = "";
        String totalFile = "";
        float[] statistics = new float[4];
        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        float averageResponseTime = 0;
        float throughput = 0;
        float totalAverageWaitingTime = 0;
        float totalAverageTurnaroundTime = 0;
        float totalAverageResponseTime = 0;
        float totalThroughput = 0;
        int i;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;
        ArrayList<Process> processArrayList;
        ArrayList<Process> unsortedArrayList;
        FirstComeFirstServed FCFS;

        //Running 5 simulations, and adding results to 'totalFile' to be printed out
        for (i = 0; i < 5; i++) {
            newProcesses = new ProcessGenerator(100, i);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();
            FCFS = new FirstComeFirstServed(processArrayList, unsortedArrayList);
            simulationString = FCFS.simulateFCFS();
            statistics = FCFS.getStatistics();
            totalFile += "\nSimulation #" + (i + 1) + " of First Come First Serve: \n";
            totalFile += simulationString;

            averageWaitingTime += statistics[0];
            averageResponseTime += statistics[2];
            averageTurnaroundTime += statistics[1];
            throughput += statistics[3];
        }

        totalAverageWaitingTime = averageWaitingTime / i;
        totalAverageResponseTime = averageResponseTime / i;
        totalAverageTurnaroundTime = averageTurnaroundTime / i;
        totalThroughput = throughput / (float)i;

        totalFile += "\nTotal Average Waiting Time for FCFS was: " + totalAverageWaitingTime;
        totalFile += "\nTotal Average Response Time for FCFS was: " + totalAverageResponseTime;
        totalFile += "\nTotal Average Turnaround for FCFS was: " + totalAverageTurnaroundTime;
        totalFile += "\nTotal Average Throughput for FCFS was: " + totalThroughput;

        printer.printToFile(totalFile);

        //a.displayProcesses();
    }
}
