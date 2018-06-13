/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {

	
	private Vector<Process> jobs;
	private boolean preemptive;	    
    PrioritySchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/

	jobs = new Vector<Process>();
	preemptive = false;
	activeJob = null; 

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);
	int min;
	Process temp = null;
	for(int i = 0; i < jobs.size()-1; ++i){
	min = i;
	for(int j = i+1; j < jobs.size(); ++j){
	if(jobs.get(j).getPriorityWeight() < jobs.get(min).getPriorityWeight() || (jobs.get(j).getPriorityWeight() == jobs.get(min).getPriorityWeight() && jobs.get(j).getPID() < jobs.get(min).getPID()))
	min = j;
	}

	temp = jobs.get(i);
	jobs.set(i, jobs.get(min));
	jobs.set(min, temp);
	

	
	}


        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
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


    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        
        // Fill in this method
        /*------------------------------------------------------------*/
	if(preemptive){
	if(activeJob == null || activeJob.getPriorityWeight() > jobs.get(0).getPriorityWeight() || (activeJob.getPriorityWeight() == jobs.get(0).getPriorityWeight() && activeJob.getPID() > jobs.get(0).getPID()))
	return jobs.get(0);



	}else{
	if(isJobFinished())
	activeJob = jobs.get(0);



	}
	return activeJob;


        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Single-Queue Priority";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Fill in this method
        /*------------------------------------------------------------*/
	return preemptive;


        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        // Fill in this method
        /*------------------------------------------------------------*/
	preemptive = v;


        /*------------------------------------------------------------*/
    }
    
}
