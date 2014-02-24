
/**
 * Write a description of class Process here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Process {

    // instance variables - replace the example below with your own
    private float arrivalTime;//between 0 and 99
    private float expectedTime;//between 0.1 and 10
    private int priority;//1, 2, 3. or 4
    private String name;
    private int startTime;
    private int finishTime;
    private float timeRemaining;
    private boolean processStarted;

    /**
     * Constructor for objects of class Process
     *
     * @param arrivalTime
     * @param expectedTime
     * @param priority
     * @param name
     */
    public Process(float arrivalTime, float expectedTime, int priority, String name) {
        this.arrivalTime = arrivalTime;
        this.expectedTime = expectedTime;
        this.priority = priority;
        this.name = "[" + name + "]";
        startTime = 0;
        finishTime = 0;
        timeRemaining = expectedTime;
        processStarted = false;

    }

    /**
     * This returns value of waiting time
     *
     * @return 
     */
    public float getWaitingTime() {
        float waitingTime = startTime - arrivalTime; 
        
        return waitingTime; 
    }


    /**
     * This returns value of process started
     *
     * @return 
     */
    public boolean getProcessStarted() {
        return processStarted;
    }

    /**
     * This returns the value of the response time
     *
     * @return 
     */
    public int getResponseTime() {
        int responseTime = finishTime - startTime; 
        if (responseTime < 0){
            //pritn something and break here
            System.out.println("ResponseTime is negative");
        }
        
        return responseTime;
    }

    /**
     * This returns the value of the response time
     *
     */
    public float getTurnaroundTime() {
        return (float) finishTime - arrivalTime;
    }

    /**
     * This returns the arrival time value
     *
     * @return value of arrivalTime
     */
    public float getArrivalTime() {
        // put your code here
        return arrivalTime;
    }

    /**
     * This returns the expected time value
     *
     * @return value of arrivalTime
     */
    public float getExpectedTime() {
        // put your code here
        return expectedTime;
    }

    /**
     * This returns the priority value
     *
     * @return value of priority
     */
    public int getPriority() {
        // put your code here
        return priority;
    }

    /**
     * This returns the priority value
     *
     * @return value of priority
     */
    public int getStartTime() {
        // put your code here
        return startTime;
    }

    /**
     * This returns the priority value
     *
     * @return value of priority
     */
    public int getFinishTime() {
        // put your code here
        return finishTime;
    }

    /**
     * This sets the start time value
     *
     *
     * @param startTime
     */
    public void setStartTime(int startTime) {
        // put your code here
        this.startTime = startTime;
        processStarted = true;
    }

    /**
     * This sets the finish time value
     *
     * @param finishTime
     */
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * This sets the arrival time value
     *
     * @param arrivalTime
     */
    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * This sets the expected time value
     *
     * @param expectedTime
     */
    public void setExpectedTime(float expectedTime) {
        this.expectedTime = expectedTime;
    }

    /**
     * This sets the priority value
     *
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * This returns the name of the process, example: [1-a]
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * This returns the time remaining value of the process
     *
     * @return
     */
    public float getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * This returns the time remaining value of the process
     *
     */
    public void runProcess() {
        timeRemaining -= 1;
    }

    /**
     * This returns a string representation of the Process object
     *
     * @return processString is a string made up of the values in the object
     */
    @Override
    public String toString() {
        String processString;
        processString = name + "\tArrival time is:  " + arrivalTime
                + ",\tExpected time is:  " + expectedTime
                + ",\tPriority is:  " + priority + "\n";

        return processString;
    }

}
