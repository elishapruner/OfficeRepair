
// File: Experiment.java
// Description:

import cern.jet.random.engine.*;
import simModel.*;

// Main Method: Experiments
//
public class Experiment {
	public static void main(String[] args) {
		int NUMRUNS = 1;
		double startTime = 0.0;
		Seeds[] sds = new Seeds[NUMRUNS];
		OfficeRepair officeRepair; // Simulation object

		// Lets get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);
		
		int initNumEmpT12 = 1;
		int initNumEmpAll = 1;
		double satisfaction = 0.85;
		double minSimTime = 1440;
		
		int[][] runOutput = new int[NUMRUNS][2];
		
		for (int i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, initNumEmpT12, initNumEmpAll, satisfaction, minSimTime);
			officeRepair.runSimulation();
			runOutput[i][0] = officeRepair.numEmployeesT12;
			runOutput[i][1] = officeRepair.numEmployeesALL;
			System.out.println("Terminated " + (i + 1));
		}
		
		for (int i = 0; i < NUMRUNS; i++) {
			System.out.println("numEmployeesT12: " + runOutput[i][0] + ", numEmployeesAll: " + runOutput[i][1]);
		}
		

	}
}
