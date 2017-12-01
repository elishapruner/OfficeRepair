// File: Experiment.java
// Description:

import cern.jet.random.engine.*;
import simModel.*;

// Main Method: Experiments
//
public class Experiment1 {
	   public static void main(String[] args)
	   {
	       int i, NUMRUNS = 30;
	       double startTime=0.0, endTime=660.0;
	       Seeds[] sds = new Seeds[NUMRUNS];
	       OfficeRepair mname;  // Simulation object

	       // Lets get a set of uncorrelated seeds
	       RandomSeedGenerator rsg = new RandomSeedGenerator();
	       for(i=0 ; i<NUMRUNS ; i++) sds[i] = new Seeds(rsg);

	       // Loop for NUMRUN simulation runs for each case
	       // Case 1
	       int numEmployeesT12 = 8 ; //Starting number of EMP_T12, see model for explanation 
	       int numEmployeesAll = 8 ; //Starting number of EMP_ALL, see model for explanation
	       
	       System.out.println(" Case 1");
	       for(i=0 ; i < NUMRUNS ; i++)
	       {
	          mname = new OfficeRepair(startTime, sds[i],numEmployeesT12,numEmployeesAll);
	          mname.runSimulation();
	          // See examples for hints on collecting output
	          // and developing code for analysis
	       }
	   }

     
}
