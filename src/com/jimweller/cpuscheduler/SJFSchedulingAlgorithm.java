/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class SJFSchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    private Vector<Process> jobs;
	private boolean  preemptive;
    SJFSchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs = new Vector<Process>();
	preemptive = false;
	activeJob = null;
        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
       
        // Fill in this method
        /*------------------------------------------------------------*/
	jobs.add(p);
	int min = 0;
        Process temp = null;
        for(int i = 0; i < jobs.size() - 1; ++i){
        min = i;
                for(int j = i+1; j < jobs.size(); ++j){
                if(jobs.get(j).getBurstTime() < jobs.get(min).getBurstTime()
		|| (jobs.get(j).getBurstTime() == jobs.get(min).getBurstTime() && jobs.get(j).getPID() < jobs.get(min).getPID()))
                min = j;

                }
        temp = jobs.get(min);
        jobs.set(min,jobs.get(i));
        jobs.set(i,temp);
        }


        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------**/
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

        // Remove the next lines to start your implementation
        
        // Fill in this method
if(preemptive){
	if(jobs.size() <= 0)
	return activeJob;
	if(activeJob == null || activeJob.getBurstTime() > jobs.firstElement().getBurstTime() || (activeJob.getBurstTime() == jobs.firstElement().getBurstTime() && activeJob.getPID() > jobs.firstElement().getPID()))
	return jobs.firstElement();

	}else{
	if(isJobFinished()){
	activeJob = jobs.firstElement();
	return jobs.firstElement();
	
	}else	
	return activeJob;
	}
	return activeJob;
       /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Shortest Job First";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Remove the next lines to start your implementation
        
        // Fill in this method
        /*------------------------------------------------------------*/
	return preemptive;


        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v  Value to assign to preemptive.
     */
    public void setPreemptive(boolean  v){
        // Remove the next lines to start your implementation
       
        // Fill in this method
        /*------------------------------------------------------------*/
	preemptive = v;


        /*------------------------------------------------------------*/
    }
    
}
