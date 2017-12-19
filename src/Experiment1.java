
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

		double endTime=(1500) ; 

		Seeds[] sds = new Seeds[NUMRUNS];
		OfficeRepair officeRepair; // Simulation object

		// Get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);
		
		int initNumEmpT12 = 3;
		int initNumEmpAll = 3;
		
		for (int i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, initNumEmpT12, initNumEmpAll);
			officeRepair.setTimef(endTime);
			officeRepair.runSimulation();
			
			System.out.println("Terminated " + (i + 1) + " cases");
		}
		
	}
}
