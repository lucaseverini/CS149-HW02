/*
*	HighestPriorityFirst.java
*
*   Assignment #2 - CS149 - SJSU
*
*	By Luca Severini (lucaseverini@mac.com)
* 
*	San Jose Feb-25-2014
*/

import java.util.*;

/**
 * HighestPriorityFirst
 * 
 * This class implements the HPF (Highest Priority First) scheduling algorithm
 * with two optional options: Preemptiveness and Aging
 */
public class HighestPriorityFirst
{
	private final int HIGHER_PRIORITY = 1;
	private final int LOWER_PRIORITY = 4;
	private final int MAX_WAITING = 5;

	private final boolean preemptive;
	private final boolean aging;
    private int quantum;
	private final ArrayList<Process> processList;
 	private final ArrayList<Process> runningProcessList;
    private ArrayList<Process> sortedProcessList;
	private ArrayList<Process> P1ProcessList;
	private ArrayList<Process> P2ProcessList;
    private ArrayList<Process> P3ProcessList;
    private ArrayList<Process> P4ProcessList;
 	private ArrayList<Process> processesDone;
	private String oneSimulation;
    private float averageWaitingTime;
    private float averageResponseTime;
    private float averageTurnaroundTime;
    private int throughput;

    /**
     * Constructor for objects of class HighestPriorityFirst
	 * 
	 * @param processArrayList
	 * @param preemptive
	 * @param aging
	 */
    public HighestPriorityFirst(ArrayList<Process> processArrayList, boolean preemptive, boolean aging)
    {
		this.preemptive = preemptive;
		this.aging = aging;
		this.processList = processArrayList;
		this.runningProcessList = new ArrayList<>();
    }
	
	/**
	 * Simulation of Preemptive HPF
	 * 
	 * @param totQuanta
	 * @return
	 */
	public String simulate(int totQuanta) 
	{
		String timeChart = "";
		
		introduceProcess();
				
		sortedProcessList = processList;
		sortProcessesByArrivalTime(sortedProcessList);
		
		P1ProcessList = new ArrayList<>();
		P2ProcessList = new ArrayList<>();
		P3ProcessList = new ArrayList<>();
		P4ProcessList = new ArrayList<>();
		
		processesDone = new ArrayList<>();
/*
		printProcessList(sortedProcessList);
		System.out.println();
*/
		Process nextProcess = null;
 		Process currentProcess = null;
 		int processIdx = 0;
        quantum = 0;
		 
		System.out.println("Simulation running for " + totQuanta + " quanta...");

		// Run loop for some quanta...
        while (quantum < totQuanta) 
		{
			System.out.println("quantum " + quantum + " :");

			// Add the processes to the running list as they become arrive...
			if(nextProcess == null && processIdx < sortedProcessList.size())
			{
				nextProcess = sortedProcessList.get(processIdx++);
			}
			while (nextProcess != null && nextProcess.getArrivalTime() <= quantum) 
			{
				addProcess(nextProcess);
				
				nextProcess.setTimeToFinish(nextProcess.getExpectedTime());
				
				System.out.println("new process " + nextProcess.getName() + " added");
							
				if(processIdx < sortedProcessList.size() - 1)
				{
					nextProcess = sortedProcessList.get(processIdx++);
				}
				else
				{
					nextProcess = null;
				}
			}
			
			if(preemptive)
			{
				// Select the process to run from a queue based on higher priority... 
				currentProcess = selectProcess(currentProcess);
			
				// Run the current Process if any... 
				if(currentProcess != null)
				{
					// If the process never ran before do some setup...
					if(!currentProcess.getStarted())
					{
						currentProcess.setStarted(true);
						currentProcess.setStartTime(quantum);

						System.out.println("process " + currentProcess.getName() + " started");
					}

					timeChart += currentProcess.getName();
					
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

						removeProcess(currentProcess);
						
						processesDone.add(currentProcess);
						
						currentProcess = null;
					}
				}
				else
				{
					System.out.println("no process running (idle)");
				}
				
				quantum++;	// increment quanta's counter
			}
			else
			{
				// Select the process to run from a queue based on higher priority... 
				currentProcess = selectProcess(currentProcess);
				
				// Run the current Process if any... 
				if(currentProcess != null)
				{
					// If the process never ran before do some setup...
					if(!currentProcess.getStarted())
					{
						currentProcess.setStarted(true);
						currentProcess.setStartTime(quantum);

						System.out.println("process " + currentProcess.getName() + " started");
					}

					while (quantum < totQuanta) 
					{		
						timeChart += currentProcess.getName();
						
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

							removeProcess(currentProcess);
												
							processesDone.add(currentProcess);

							currentProcess = null;
							break;
						}

						quantum++;
						
						System.out.println("quantum " + quantum + " :");
					}
				}
				else
				{
					System.out.println("no process running (idle)");
				}
				
				quantum++;
			}
		}
/*		
		sortProcessesByFinishTime(sortedProcessList);
		printProcessList(sortedProcessList);
		System.out.println();
*/
	    throughput = processesDone.size();
		
		oneSimulation += "Simulated order of Highest Priority First";

		if(preemptive)
		{
			oneSimulation += " Preemptive";
		}
		else
		{
			oneSimulation += " Non-Preemptive";
		}
		
		if(aging)
		{
			oneSimulation += " with Aging";
		}
		
		oneSimulation += "\n";
		
        oneSimulation += timeChart;

        oneSimulation += "\n" + getStringOfAverages(processesDone.size());

        return oneSimulation;//this is the OVERALL STRING REPRESENTATION
	}
		
	/**
	 * Add the process to the process list corresponding to its priority
	 * 
	 * @param process
	 */
	private void addProcess(Process process)
	{
		ArrayList<Process> proclist = null;
		
		switch(process.getPriority())
		{
			case 1:
				proclist = P1ProcessList;	
				break;

			case 2:
				proclist = P2ProcessList;
				break;

			case 3:
				proclist = P3ProcessList;
				break;

			case 4:
				proclist = P4ProcessList;
				break;
		}
		
		if(proclist != null)
		{
			// Set the pointer to the next process in the last process object...
			int listSize = proclist.size();
			if(listSize > 0)
			{
				Process prevProcess = proclist.get(listSize - 1);
				prevProcess.setNextProcess(process);
			}
			
			// Add the process to the bottom of the list
			proclist.add(process);
			
			process.setStartWaitingTime(quantum);
		}
		else
		{
			System.out.println("process " + process.getName() + " has an invalid priority");
		}
	}
	
	/**
	 * Remove the process from the process list corresponding to its priority
	 * 
	 * @param process
	 */
	private void removeProcess(Process process)
	{
		ArrayList<Process> proclist = null;

		switch(process.getPriority())
		{
			case 1:
				proclist = P1ProcessList;	
				break;

			case 2:
				proclist = P2ProcessList;
				break;

			case 3:
				proclist = P3ProcessList;
				break;

			case 4:
				proclist = P4ProcessList;
		}

		if(proclist != null)
		{
			// Update the pointer to the next process accordingly...
			int processIdx = proclist.indexOf(process);
			if(processIdx > 0)
			{
				Process nextProcess = null;
				if(processIdx < proclist.size() - 1)
				{
					nextProcess = proclist.get(processIdx + 1);
				}
				
				proclist.get(processIdx - 1).setNextProcess(nextProcess);
			}
		
			// Remove the process from the list
			proclist.remove(process);
		}
	}
	
	/**
	 * Select the next process to run or return null if there is none
	 * 
	 * @param currentProcess
	 * @return 
	 */
	private Process selectProcess(Process currentProcess)
	{
		Process process = null;
		
		int curPriority = 0;
		if(currentProcess != null)
		{
			curPriority = currentProcess.getPriority();
			
			currentProcess.setStartWaitingTime(quantum);
		}
		
		// if aging is enabled move up some process if they are waiting more than MAX_WAITING quanta
		if(aging)
		{
			int listSize = P2ProcessList.size();
			for(int idx = 0; idx < listSize; idx++)
			{
				Process p = P2ProcessList.get(idx);
				
				float waitingTime = p.getStartWaitingTime();
				if(waitingTime >= 0 && quantum > waitingTime + MAX_WAITING)
				{
					P2ProcessList.remove(p);
					idx--;
					listSize--;
					
					p.setPriority(1);
					p.setStartWaitingTime(quantum);
					P1ProcessList.add(p);
					
					System.out.println("aging process " + p.getName() + " moved up to priority 1");
				}
			}

			listSize = P3ProcessList.size();
			for(int idx = 0; idx < listSize; idx++)
			{
				Process p = P3ProcessList.get(idx);
				
				float waitingTime = p.getStartWaitingTime();
				if(waitingTime >= 0 && quantum > waitingTime + MAX_WAITING)
				{
					P3ProcessList.remove(p);
					idx--;
					listSize--;
					
					p.setPriority(2);
					p.setStartWaitingTime(quantum);
					P2ProcessList.add(p);

					System.out.println("aging process " + p.getName() + " moved up to priority 2");
				}
			}

			listSize = P4ProcessList.size();
			for(int idx = 0; idx < listSize; idx++)
			{
				Process p = P4ProcessList.get(idx);
				
				float waitingTime = p.getStartWaitingTime();
				if(waitingTime >= 0 && quantum > waitingTime + MAX_WAITING)
				{
					P4ProcessList.remove(p);
					idx--;
					listSize--;
					
					p.setPriority(3);
					p.setStartWaitingTime(quantum);
					P3ProcessList.add(p);

					System.out.println("aging process " + p.getName() + " moved up to priority 3");
				}
			}
		}
		
		for(int priority = HIGHER_PRIORITY; priority <= LOWER_PRIORITY; priority++)
		{
			switch(priority)
			{
				case HIGHER_PRIORITY:
					if(priority == curPriority)
					{
						if(currentProcess != null)
						{
							process = currentProcess.getNextProcess();
						}
					}
					
					if(process == null && P1ProcessList.size() > 0)
					{
						process = P1ProcessList.get(0);	
					}
					break;

				case 2:
					if(priority == curPriority)
					{
						if(currentProcess != null)
						{
							process = currentProcess.getNextProcess();
						}
					}
					
					if(process == null && P2ProcessList.size() > 0)
					{
						process = P2ProcessList.get(0);	
					}
					break;

				case 3:
					if(priority == curPriority)
					{
						if(currentProcess != null)
						{
							process = currentProcess.getNextProcess();
						}
					}
					
					if(process == null && P3ProcessList.size() > 0)
					{
						process = P3ProcessList.get(0);	
					}
					break;

				case LOWER_PRIORITY:
					if(priority == curPriority)
					{
						if(currentProcess != null)
						{
							process = currentProcess.getNextProcess();
						}
					}
					
					if(process == null && P4ProcessList.size() > 0)
					{
						process = P4ProcessList.get(0);	
					}
					break;
			}
			
			if(process != null)
			{
				process.setStartWaitingTime(-1);
				
				return process;
			}
		}
	
		return null;
	}
	
	/**
	 * Print all the processes in the list
	 * 
	 * @param list
	 */
	private void printProcessList(ArrayList<Process> list)
	{
		System.out.println("Completed processes:");
		for(Process p : list)
		{
			if(p.getFinishTime() == 0)
			{
				continue;
			}
			
			System.out.printf("%s %d %6.3f %6.3f %2d %2d\n", p.getName(), p.getPriority(), p.getArrivalTime(),
														p.getExpectedTime(), p.getStartTime(), p.getFinishTime());
		}
		System.out.println("Not completed processes:");
		for(Process p : list)
		{
			if(p.getFinishTime() != 0)
			{
				continue;
			}

			System.out.printf("%s %d %6.3f %6.3f %2d %2d\n", p.getName(), p.getPriority(), p.getArrivalTime(),
														p.getExpectedTime(), p.getStartTime(), p.getFinishTime());
		}
	}
	
   /**
     * This sets up a string value for all the process objects
     *
     */
	public void introduceProcess() 
	{
        String content = "";
        for (int idx = 0; idx < processList.size(); idx++) 
		{
            content += processList.get(idx).toString();
        }
        // displayProcess(content);	// for testing purposes
        oneSimulation = "\n" + content + "\n"; // adds to simulation's OVERALL STRING REPRESENTATION
        
		System.out.println(oneSimulation);
    }
	
	/**
     * This returns an array with the statistics information
     *
     * @return averages
     */
    public float[] getStatistics() 
	{
        float[] averages = { averageWaitingTime, averageResponseTime, averageTurnaroundTime, throughput};
        return averages;
    }

   /**
     * This processes the average statistics for one simulation of this
     * algorithm
     *
     * @param numProcesses the number of processes that started(were processed)
     * during simulation
     * @return 'averages' The string representing the averages to be attached to
     * FCFS's OVERALL STRING REPRESENTATION
     */
    public String getStringOfAverages(int numProcesses) 
	{
        String averages = "";
        float totalWaitingTime = 0;
        float totalResponseTime = 0;
        float totalTurnaroundTime = 0;
        float waitingTime = 0;

        // generates the averages for each required statistic
        for (int idx = 0; idx < numProcesses; idx++) 
		{
            waitingTime = processesDone.get(idx).getWaitingTime();
            if (waitingTime < 0) 
			{
                System.out.println("negative!");
            }

            totalWaitingTime += waitingTime;
            totalResponseTime += processesDone.get(idx).getResponseTime();
            totalTurnaroundTime += processesDone.get(idx).getTurnaroundTime();
        }

        System.out.println();

        averageWaitingTime = totalWaitingTime / numProcesses;
        averageResponseTime = totalResponseTime / numProcesses;
        averageTurnaroundTime = totalTurnaroundTime / numProcesses;

        averages += "\nThe average Waiting time was: " + averageWaitingTime;
        averages += "\nThe average Response time was: " + averageResponseTime;
        averages += "\nThe average Turnaround time was: " + averageTurnaroundTime + "\n\n";

        return averages;
    }

	private void sortProcessesByFinishTime(ArrayList<Process> list)
	{
		Collections.sort(list, new ProcessComparator(3));
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
	 	
	public class ProcessComparator implements Comparator<Process> 
	{
		final private int selector;
		
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

				case 3:
					return Float.compare(p1.getFinishTime(), p2.getFinishTime());

				default:
					return 0;
			}
		}
	}
}
