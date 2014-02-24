/*
	Developed by Luca Severini
*/

import java.util.*;

/**
 * Write a description of class ShortestRemainingTime here.
 *
 * @author Luca Severini
 * @version Feb 23 2014
 */

public class ShortestRemainingTime
{
	private final ArrayList<Process> processList;
    private ArrayList<Process> sortedProcessList;
	private ArrayList<Process> runningProcessList;
 
    /**
     * Constructor for objects of class ShortestRemainingTime
	 * @param processArrayList
	 */
    public ShortestRemainingTime(ArrayList<Process> processArrayList)
    {
		this.processList = processArrayList;
     }
	
	/**
	 * Simulation of preemptive SRT
	 * @param totQuanta
	 * @return
	 */
	public String simulatePreemptive(int totQuanta) 
	{
		String output = "";
		
		sortedProcessList = processList;
		sortProcessesByArrivalTime(sortedProcessList);
		
		runningProcessList = new ArrayList<>();
		
		printProcessList(sortedProcessList);
		System.out.println();
 
		Process nextProcess = null;
 		Process currentProcess = null;
 		int processIdx = 0;
        int quantum = 0;
		 
		// Run loop for some quanta...
        while (quantum < totQuanta) 
		{
			System.out.println("quantum " + quantum + " :");
			
			// Add the processes to the running list as they become arrive...
			boolean processesAdded = false;
			if(nextProcess == null && processIdx < sortedProcessList.size())
			{
				nextProcess = sortedProcessList.get(processIdx++);
			}
			while (nextProcess != null && nextProcess.getArrivalTime() <= quantum) 
			{
				runningProcessList.add(nextProcess);
				
				nextProcess.setTimeToFinish(nextProcess.getExpectedTime());
				
				System.out.println("process " + nextProcess.getName() + " added");
							
				if(processIdx < sortedProcessList.size() - 1)
				{
					nextProcess = sortedProcessList.get(processIdx++);
				}
				else
				{
					nextProcess = null;
				}
				
				processesAdded = true;
			}
			
			// Sort runningProcessList by Shortest Remaining Time
			if(processesAdded)
			{
				sortProcessesByTimeToFinish(runningProcessList);
			}
			
			// Prepare to the process on top of runningProcessList if any... 
			if(runningProcessList.size() > 0)
			{
				currentProcess = runningProcessList.get(0);
				
				// If the process never ran before does some setup...
				if(!currentProcess.getStarted())
				{
					currentProcess.setStarted(true);
					currentProcess.setStartTime(quantum);
					
					System.out.println("process " + currentProcess.getName() + " started");
				}
			}
			else
			{
				System.out.println("no process running (idle)");
			}
			
			// Run the current Process...
			if(currentProcess != null)
			{
				// This method, which does nothing, is just to show the current process running for a quantum
				currentProcess.run();
				
				float timeToFinish = currentProcess.getTimeToFinish() - 1;
				if(timeToFinish <= 0)
				{
					timeToFinish = 0;
				}
				
				currentProcess.setTimeToFinish(timeToFinish);
				
				if(timeToFinish == 0)
				{
					System.out.println("process " + currentProcess.getName() + " terminated");
					
					currentProcess.setFinishTime(quantum);
					
					runningProcessList.remove(currentProcess);
					currentProcess = null;
				}
				else
				{
					sortProcessesByTimeToFinish(runningProcessList);
				}
			}

			quantum++;	// increment quanta's counter
		}
		
		printProcessList(sortedProcessList);
		System.out.println();

		return output;
	}
	
	private void printProcessList(ArrayList<Process> list)
	{
		for(Process p : list)
		{
			System.out.printf("%s %d %6.3f %6.3f %2d %2d\n", p.getName(), p.getPriority(), p.getArrivalTime(),
												p.getExpectedTime(), p.getStartTime(), p.getFinishTime());
		}
	}
	
	private void sortProcessesByTimeToFinish(ArrayList<Process> list)
	{
		Collections.sort(list, new ProcessComparator(2));
	}

	private void sortProcessesByArrivalTime(ArrayList<Process> list)
	{
		Collections.sort(list, new ProcessComparator(1));
	}

	private void sortProcessesByExpectedTime(ArrayList<Process> list)
	{
		Collections.sort(list, new ProcessComparator(0));
	}
	 
	public static void main (String [] args)
	{
		ProcessGenerator procGen = new ProcessGenerator(100, 1);
		ArrayList<Process> procs = procGen.generateProcesses();
		
		System.out.println("xx");
  	}
	
	public class ProcessComparator implements Comparator<Process> 
	{
		private int selector;
		
		ProcessComparator (int selector)
		{
			this.selector = selector;
		}
		
		@Override
		public int compare(Process p1, Process p2) 
		{
			switch(selector)
			{
				case 0:
					return Float.compare(p1.getExpectedTime(), p2.getExpectedTime());
					
				case 1:
					return Float.compare(p1.getArrivalTime(), p2.getArrivalTime());
					
				case 2:
					return Float.compare(p1.getTimeToFinish(), p2.getTimeToFinish());

				default:
					return 0;
			}
		}
	}
}
