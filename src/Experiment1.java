
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
		
		int initNumEmpT12 = 5;
		int initNumEmpAll = 10;
		
		int[][] runOutput = new int[NUMRUNS][2];
		
		for (int i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, initNumEmpT12, initNumEmpAll);
			officeRepair.runSimulation();
			officeRepair.setTimef(endTime);
			runOutput[i][0] = officeRepair.numEmployeesT12;
			runOutput[i][1] = officeRepair.numEmployeesALL;
			System.out.println("Terminated " + (i + 1) + " cases");
		}
		
		for (int i = 0; i < NUMRUNS; i++) {
			System.out.println("(" + (i+1) + ") \tnumEmployeesT12: " + runOutput[i][0] + ", numEmployeesAll: " + runOutput[i][1]);
		}
		

	}
}
