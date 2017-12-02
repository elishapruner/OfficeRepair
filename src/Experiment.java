
// File: Experiment.java
// Description:

import cern.jet.random.engine.*;
import simModel.*;

// Main Method: Experiments
//
public class Experiment {
	public static void main(String[] args) {
		int i, NUMRUNS = 30;
		double startTime = 0.0;
		Seeds[] sds = new Seeds[NUMRUNS];
		OfficeRepair officeRepair; // Simulation object

		// Lets get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);
		
		// Parameters
		int numEmployeesT12 = 8; // Starting number of EMP_T12, see model for explanation
		int numEmployeesAll = 8; // Starting number of EMP_ALL, see model for explanation
		
		for (i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, numEmployeesT12, numEmployeesAll);
			officeRepair.runSimulation();
			System.out.println("Terminated " + (i + 1) + ", " + officeRepair.getSatisfactionLevelAll());
		}
	}
}
