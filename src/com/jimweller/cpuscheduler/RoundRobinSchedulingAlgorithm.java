/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    /** the time slice each process gets */
    private int quantum;
    private Vector<Process> jobs;
    private int time;
RoundRobinSchedulingAlgorithm() {
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs = new Vector<Process>();
	activeJob = null;
	quantum = 10;	
	time = 0;
        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);
	Collections.sort(jobs, new Comparator<Process>() {
	@Override
	public int compare(Process p1, Process p2){
	return (int) (p1.getPID() - p2.getPID());
	}

        /*------------------------------------------------------------*/
    });

}
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        
        // Fill in this method
        /*------------------------------------------------------------*/
	return jobs.remove(p);


        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
        // Fill in this method
        /*------------------------------------------------------------*/
	if(jobs.size() <= 0 || jobs.firstElement().getArrivalTime() > currentTime)
	return null; //filter out if jobs vector dne... or no elements arrived

	if(activeJob == null)
	{
	activeJob = jobs.firstElement();
	time = (int) currentTime;
	return activeJob;
	}
	if(currentTime == time + quantum || activeJob.isFinished())
	{
	time = (int) currentTime;
	int jobSize = 0;
	boolean tempBool = false;
	while(jobSize < jobs.size() && !tempBool){
	if(activeJob.getPID() >= jobs.get(jobSize).getPID())
	jobSize++;
	else
	tempBool = true;
	}
	if(jobSize >= jobs.size()){
	activeJob = jobs.get(0);
	return activeJob;
	}
	
	activeJob = jobs.get(jobSize);
	}
return activeJob;


        /*------------------------------------------------------------*/
    }

    public String getName() {
        return "Round Robin";
    }
    
}

