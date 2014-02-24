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
	public static final int QUANTA = 100;

    public static void main(String[] args) {
        printToFile printer = new printToFile();
        String simulationString = "";
        String totalFile = "";
        String RR;
        String FCFS;
        String SJF;
        String SRT;
        String HPF;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;
        ArrayList<Process> processArrayList;
/*
        RR = runRR();
        totalFile += RR;

        SJF = runSJF();
        totalFile += SJF;

        FCFS = runFCFS();
        totalFile += FCFS;
		
        SRT = runSRT();
        totalFile += SRT;
*/
		HPF = runHPF();
        totalFile += HPF;

        printer.printToFile(totalFile);

        //printer.printToFile(simulationString);
    }

    public static String runRR() {

        ArrayList<Process> processArrayList;
        ArrayList<Process> unsortedArrayList;

        String simulationString;
        String totalFile = "";
        float[] statistics;
        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        float averageResponseTime = 0;
        float throughput = 0;
        float totalAverageWaitingTime;
        float totalAverageTurnaroundTime;
        float totalAverageResponseTime;
        float totalThroughput;
        int i;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;

        RoundRobin RR;

        //Running 5 simulations, and adding results to 'totalFile' to be printed out
        for (i = 0; i < SIMULATIONS; i++) {
            newProcesses = new ProcessGenerator(PROCESSES, i);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();

            RR = new RoundRobin(processArrayList, unsortedArrayList);
            simulationString = RR.simulateRR();
            statistics = RR.getStatistics();
            if (i == 0) {
                totalFile += "---------------------------------------------------------------------------------------";

            }
            totalFile += "\nSimulation #" + (i + 1) + " of Round Robin: \n";
            totalFile += simulationString;

            averageWaitingTime += statistics[0];
            averageResponseTime += statistics[2];
            averageTurnaroundTime += statistics[1];
            throughput += statistics[3];
        }

        totalAverageWaitingTime = averageWaitingTime / i;
        totalAverageResponseTime = averageResponseTime / i;
        totalAverageTurnaroundTime = averageTurnaroundTime / i;
        totalThroughput = throughput / (float) i;

        totalFile += "\nTotal Average Waiting Time for RR was: " + totalAverageWaitingTime;
        totalFile += "\nTotal Average Response Time for RR was: " + totalAverageResponseTime;
        totalFile += "\nTotal Average Turnaround for RR was: " + totalAverageTurnaroundTime;
        totalFile += "\nTotal Average Throughput for RR was: " + totalThroughput + "\n\n";

        return totalFile;
    }

    public static String runFCFS() {
        ArrayList<Process> processArrayList;
        ArrayList<Process> unsortedArrayList;
        String simulationString;
        String totalFile = "";
        float[] statistics;
        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        float averageResponseTime = 0;
        float throughput = 0;
        float totalAverageWaitingTime;
        float totalAverageTurnaroundTime;
        float totalAverageResponseTime;
        float totalThroughput;
        int i;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;

        FirstComeFirstServed FCFS;

        //Running 5 simulations, and adding results to 'totalFile' to be printed out
        for (i = 0; i < SIMULATIONS; i++) {
            newProcesses = new ProcessGenerator(PROCESSES, i);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();

            FCFS = new FirstComeFirstServed(processArrayList, unsortedArrayList);
            simulationString = FCFS.simulateFCFS();
            statistics = FCFS.getStatistics();
            if (i == 0) {
                totalFile += "---------------------------------------------------------------------------------------";
            }
            totalFile += "\nSimulation #" + (i + 1) + " of First Come First Served: \n";
            totalFile += simulationString;

            averageWaitingTime += statistics[0];
            averageResponseTime += statistics[2];
            averageTurnaroundTime += statistics[1];
            throughput += statistics[3];
        }

        totalAverageWaitingTime = averageWaitingTime / i;
        totalAverageResponseTime = averageResponseTime / i;
        totalAverageTurnaroundTime = averageTurnaroundTime / i;
        totalThroughput = throughput / (float) i;

        totalFile += "\nTotal Average Waiting Time for FCFS was: " + totalAverageWaitingTime;
        totalFile += "\nTotal Average Response Time for FCFS was: " + totalAverageResponseTime;
        totalFile += "\nTotal Average Turnaround for FCFS was: " + totalAverageTurnaroundTime;
        totalFile += "\nTotal Average Throughput for FCFS was: " + totalThroughput + "\n\n";

        return totalFile;
    }

    /**
     *
     * @return
     */
    public static String runSJF() {
        ArrayList<Process> processArrayList;
        ArrayList<Process> unsortedArrayList;
        String simulationString;
        String totalFile = "";
        float[] statistics;
        float averageWaitingTime = 0;
        float averageTurnaroundTime = 0;
        float averageResponseTime = 0;
        float throughput = 0;
        float totalAverageWaitingTime;
        float totalAverageTurnaroundTime;
        float totalAverageResponseTime;
        float totalThroughput;
        int i;

        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses;

        ShortestJobFirst SJF;

        //Running 5 simulations, and adding results to 'totalFile' to be printed out
        for (i = 0; i < SIMULATIONS; i++) {
            newProcesses = new ProcessGenerator(PROCESSES, i);
            processArrayList = newProcesses.generateProcesses();
            unsortedArrayList = newProcesses.getUnsortedArrayList();

            SJF = new ShortestJobFirst(processArrayList, unsortedArrayList);
            simulationString = SJF.simulateSJF();
            statistics = SJF.getStatistics();
            if (i == 0) {
                totalFile += "---------------------------------------------------------------------------------------";
            }
            totalFile += "\nSimulation #" + (i + 1) + " of Shortest Job First: \n";
            totalFile += simulationString;

            averageWaitingTime += statistics[0];
            averageResponseTime += statistics[2];
            averageTurnaroundTime += statistics[1];
            throughput += statistics[3];
        }

        totalAverageWaitingTime = averageWaitingTime / i;
        totalAverageResponseTime = averageResponseTime / i;
        totalAverageTurnaroundTime = averageTurnaroundTime / i;
        totalThroughput = throughput / (float) i;

        totalFile += "\nTotal Average Waiting Time for SJF was: " + totalAverageWaitingTime;
        totalFile += "\nTotal Average Response Time for SJF was: " + totalAverageResponseTime;
        totalFile += "\nTotal Average Turnaround for SJF was: " + totalAverageTurnaroundTime;
        totalFile += "\nTotal Average Throughput for SJF was: " + totalThroughput + "\n\n";

        return totalFile;
    }
	
	public static String runSRT()
	{
        ArrayList<Process> processArrayList;
		String simulationString = "";

		for (int idx = 0; idx < SIMULATIONS; idx++) 
		{
            ProcessGenerator procGen = new ProcessGenerator(PROCESSES, idx);
            processArrayList = procGen.generateProcesses();

            ShortestRemainingTime SRT = new ShortestRemainingTime(processArrayList);

            simulationString = SRT.simulatePreemptive(QUANTA);
        }
		
		return simulationString;
	}
	
	public static String runHPF()
	{
        ArrayList<Process> processArrayList;
		String simulationString = "";

		for (int idx = 0; idx < 1; idx++) 
		{
            ProcessGenerator procGen = new ProcessGenerator(20, idx);
            processArrayList = procGen.generateProcesses();

            HighestPriorityFirst HPF = new HighestPriorityFirst(processArrayList);

            simulationString = HPF.simulatePreemptive(200);
        }
		
		return simulationString;
	}
}
