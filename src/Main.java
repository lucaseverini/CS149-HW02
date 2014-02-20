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
        ArrayList<Process> processArrayList = new ArrayList<>();
        ProcessGenerator newProcesses = new ProcessGenerator(10);
        processArrayList = newProcesses.generateProcesses();
		
		System.out.println("Commit Test...");
    }
}
