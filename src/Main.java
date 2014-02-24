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

    public static final int SIMULATIONS = 5; 
    public static final int PROCESSES = 100; 
    
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
        int idx;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;
        ArrayList<Process> processArrayList;
        ArrayList<Process> unsortedArrayList;
		
        FirstComeFirstServed FCFS;
        ShortestJobFirst SJF;

<<<<<<< HEAD
        //Running 5 simulations, and adding results to 'totalFile' to be printed out
        for (i = 0; i < SIMULATIONS; i++) {
            newProcesses = new ProcessGenerator(PROCESSES, i);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();

            //FCFS = new FirstComeFirstServed(processArrayList, unsortedArrayList);
            //simulationString = FCFS.simulateFCFS();
            //statistics = FCFS.getStatistics();
            SJF = new ShortestJobFirst(processArrayList, unsortedArrayList);
            simulationString = SJF.simulateSJF();
            statistics = SJF.getStatistics();
            totalFile += "\nSimulation #" + (i + 1) + " of First Come First Serve: \n";
=======
        //Running 5 simulations for FCFS, and adding results to 'totalFile' to be printed out
        for (idx = 0; idx < 5; idx++) {
            newProcesses = new ProcessGenerator(100, idx);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();
            FCFS = new FirstComeFirstServed(processArrayList, unsortedArrayList);
            simulationString = FCFS.simulateFCFS();
            statistics = FCFS.getStatistics();
            totalFile += "\nSimulation #" + (idx + 1) + " of First Come First Serve: \n";
>>>>>>> 40fb1c7ce22db4c2f147e10c9bcbbbc70e52b666
            totalFile += simulationString;

            averageWaitingTime += statistics[0];
            averageResponseTime += statistics[2];
            averageTurnaroundTime += statistics[1];
            throughput += statistics[3];
        }
<<<<<<< HEAD

        totalAverageWaitingTime = averageWaitingTime / i;
        totalAverageResponseTime = averageResponseTime / i;
        totalAverageTurnaroundTime = averageTurnaroundTime / i;
        totalThroughput = throughput / (float) i;
=======
		
        totalAverageWaitingTime = averageWaitingTime / idx;
        totalAverageResponseTime = averageResponseTime / idx;
        totalAverageTurnaroundTime = averageTurnaroundTime / idx;
        totalThroughput = throughput / (float)idx;
>>>>>>> 40fb1c7ce22db4c2f147e10c9bcbbbc70e52b666

        totalFile += "\nTotal Average Waiting Time for FCFS was: " + totalAverageWaitingTime;
        totalFile += "\nTotal Average Response Time for FCFS was: " + totalAverageResponseTime;
        totalFile += "\nTotal Average Turnaround for FCFS was: " + totalAverageTurnaroundTime;
        totalFile += "\nTotal Average Throughput for FCFS was: " + totalThroughput;

        printer.printToFile(totalFile);
		
		
		// Running 5 simulations for SRT
        for (idx = 1; idx < 2; idx++) 
		{
			newProcesses = new ProcessGenerator(100, idx);
			processArrayList = newProcesses.generateProcesses();
			
			ShortestRemainingTime SRT = new ShortestRemainingTime(processArrayList);
		
			simulationString = SRT.simulatePreemptive(100);
		}

		
        //a.displayProcesses();
    }
}
