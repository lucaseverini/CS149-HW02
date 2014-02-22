/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author arashzahoory
 */
public class Main
{
    public static void main (String [] args)
    {
        // ProcessGenerator: first parameter is number of processes to generate
        // second parameter is seed number for random function.
        ProcessGenerator newProcesses = new ProcessGenerator(100, 1);
        ArrayList<Process> processArrayList = newProcesses.generateProcesses();
        
        //code for five First Come first Served process runs
        FirstComeFirstServed a = new FirstComeFirstServed(processArrayList);
		
        a.displayProcesses();	
    }
}
