 /*
	Developed by Luca Severini
*/

import java.util.*;

/**
 * Write a description of class HighestPriorityFirst here.
 *
 * @author Luca Severini
 * @version Feb 24 2014
 */

public class HighestPriorityFirst
{
	private final ArrayList<Process> processList;
 	private final ArrayList<Process> runningProcessList;
    private ArrayList<Process> sortedProcessList;
	private ArrayList<Process> P1ProcessList;
	private ArrayList<Process> P2ProcessList;
    private ArrayList<Process> P3ProcessList;
    private ArrayList<Process> P4ProcessList;
 
    /**
     * Constructor for objects of class HighestPriorityFirst
	 * @param processArrayList
	 */
    public HighestPriorityFirst(ArrayList<Process> processArrayList)
    {
		this.processList = processArrayList;
		this.runningProcessList = new ArrayList<>();
    }
	
	/**
	 * Simulation of preemptive HPF
	 * @param totQuanta
	 * @return
	 */
	public String simulatePreemptive(int totQuanta) 
	{
		String output = "";
				
		sortedProcessList = processList;
		sortProcessesByArrivalTime(sortedProcessList);
		
		P1ProcessList = new ArrayList<>();
		P2ProcessList = new ArrayList<>();
		P3ProcessList = new ArrayList<>();
		P4ProcessList = new ArrayList<>();

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
					currentProcess = null;
				}
			}
			else
			{
				System.out.println("no process running (idle)");
			}

			quantum++;	// increment quanta's counter
		}
		
		sortProcessesByFinishTime(sortedProcessList);
		printProcessList(sortedProcessList);
		System.out.println();

		return output;
	}
	
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
			proclist.add(process);

			int listSize = proclist.size();
			if(listSize > 1)
			{
				Process prevProcess = proclist.get(listSize - 2);
				prevProcess.setNextProcess(process);
			}
		}
		else
		{
			System.out.println("process " + process.getName() + " has an invalid priority");
		}
	}
	
	private void removeProcess(Process process)
	{
		switch(process.getPriority())
		{
			case 1:
				P1ProcessList.remove(process);	
				break;

			case 2:
				P2ProcessList.remove(process);
				break;

			case 3:
				P3ProcessList.remove(process);
				break;

			case 4:
				P4ProcessList.remove(process);
				break;
		}
	}
	
	private Process selectProcess(Process currentProcess)
	{
		int curPriority = 0;
		if(currentProcess != null)
		{
			curPriority = currentProcess.getPriority();
		}

		for(int priority = 1; priority <= 4; priority++)
		{
			switch(priority)
			{
				case 1:
					if(priority == curPriority)
					{
						return currentProcess;
					}
					else if(P1ProcessList.size() > 0)
					{
						return P1ProcessList.get(0);	
					}
					break;

				case 2:
					if(priority == curPriority)
					{
						return currentProcess;
					}
					else if(P2ProcessList.size() > 0)
					{
						return P2ProcessList.get(0);	
					}
					break;

				case 3:
					if(priority == curPriority)
					{
						return currentProcess;
					}
					else if(P3ProcessList.size() > 0)
					{
						return P3ProcessList.get(0);	
					}
					break;

				case 4:
					if(priority == curPriority)
					{
						return currentProcess;
					}
					else if(P4ProcessList.size() > 0)
					{
						return P4ProcessList.get(0);	
					}
					break;
			}
		}
	
		return null;
	}
	
	private void printProcessList(ArrayList<Process> list)
	{
		for(Process p : list)
		{
			System.out.printf("%s %d %6.3f %6.3f %2d %2d\n", p.getName(), p.getPriority(), p.getArrivalTime(),
												p.getExpectedTime(), p.getStartTime(), p.getFinishTime());
		}
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
	 
	public static void main (String [] args)
	{
		ProcessGenerator procGen = new ProcessGenerator(100, 1);
		ArrayList<Process> procs = procGen.generateProcesses();
		
		System.out.println("xx");
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
