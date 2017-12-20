
// File: Experiment.java
// Description:

import cern.jet.random.engine.*;
import simModel.*;

// Main Method: Experiments
//
public class Experiment1 {
	public static void main(String[] args) {
		int NUMRUNS = 6;
		double startTime = 0.0;

		double endTime=(24*60)*7 ; 

		Seeds[] sds = new Seeds[NUMRUNS];
		OfficeRepair officeRepair; // Simulation object

		// Get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);
		
		int initNumEmpT12 = 7;
		int initNumEmpAll = 9;
		
		for (int i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, initNumEmpT12, initNumEmpAll);
			officeRepair.setTimef(endTime);
			officeRepair.runSimulation();
	         System.out.println("Simulation Done time to output the results");
	          // compute scalar output
	         System.out.println("\n/*****************************/");
	         System.out.println("Satisfaction LevelT12: "+(Math.round(officeRepair.getSatisfactionLevelT12() * 100)+"%"));
	         System.out.println("Satisfaction LevelT34: "+(Math.round(officeRepair.getSatisfactionLevelT34() * 100)+"%"));
	         System.out.println("Satisfaction LevelALL: "+(Math.round(officeRepair.getSatisfactionLevelAll() * 100)+"%"));
	         System.out.println("Overtime Costs: " + "$"+officeRepair.getOverTimeCost());
	         System.out.println("Average Daily Costs: "+ "$" +officeRepair.getAverageDailyCost());
	         System.out.println("/*****************************/\n");
	         System.out.println("Terminated " + (i + 1) + " cases");
	       }
			 
			
		}
		
	
}
