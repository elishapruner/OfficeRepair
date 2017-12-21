
// File: 
// Description:

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cern.jet.random.engine.*;
import outputAnalysis.ConfidenceInterval;
import simModel.OfficeRepair;
import simModel.Seeds;

public class Experiment2 {
	private static OfficeRepair officeRepair;
	
	// Some experimental constants
	public static final int NUMRUNS = 1000; // for exploring number of runs
	public static final int[] NUM_WEEKS_ARR = { 1, 2 };
	public static final int[] NUM_EMP_ARR = { 11, 12, 13 };
	public static final double WEEK = 7.0 * 24.0 * 60; // 7 day week, 24 hours/day, 60 minutes/hour
	public static final double WARM_UP_PERIOD = 30 * 60; // 30 hour warm up period, see WarmUp.java

	// Some arrays to collect experimental data
	public static double[][][] satisfactionLevelT12 = new double[NUM_EMP_ARR.length][NUM_WEEKS_ARR.length][NUMRUNS]; 
	public static double[][][] satisfactionLevelT34 = new double[NUM_EMP_ARR.length][NUM_WEEKS_ARR.length][NUMRUNS]; 
	public static double[][][] satisfactionLevelAll = new double[NUM_EMP_ARR.length][NUM_WEEKS_ARR.length][NUMRUNS]; 
	public static double[][][] averageDailyCost = new double[NUM_EMP_ARR.length][NUM_WEEKS_ARR.length][NUMRUNS]; 

	// For output analysis
	static final double CONF_LEVEL = 0.9;
	static final int[] NUM_RUNS_ARRAY = { 20, 30, 40, 60, 80, 100, 1000, 10000 };

	/** main method **/
	public static void main(String[] args) {
		double startTime = 0.0; // Observation interval starts at t = 0
		double endTime; // End time, rhs of obserservation interval - to be determined experimentally
		Seeds[] sds = new Seeds[NUMRUNS];
		
		System.out.println("--- Experiment 2 Output --- ");
		
		int numEmpT12; // parameter
		int numEmpAll; // parameter

		// Lets get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);

		for (int ixNEmp = 0; ixNEmp < NUM_EMP_ARR.length; ixNEmp++) {
			numEmpT12 = NUM_EMP_ARR[ixNEmp];
			numEmpAll = 13;
			System.out.println("Number of Employees of type T12 = " + numEmpT12);
			System.out.println("Number of Employees of type ALL = " + numEmpAll);
			
			for (int ixNWeeks = 0; ixNWeeks < NUM_WEEKS_ARR.length; ixNWeeks++) {
				endTime = NUM_WEEKS_ARR[ixNWeeks] * WEEK;
				System.out.println("End Time = " + NUM_WEEKS_ARR[ixNWeeks] + " weeks (" + endTime + " hours), TimeStamp: " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
				for (int i = 0; i < NUMRUNS; i++) {
					officeRepair = new OfficeRepair(startTime, sds[i], false, numEmpT12, numEmpAll);
					officeRepair.setTimef(WARM_UP_PERIOD);
					officeRepair.runSimulation();
					officeRepair.setTimef(endTime);
					officeRepair.runSimulation();
					
					// Save the results in the arrays
					satisfactionLevelT12[ixNEmp][ixNWeeks][i] = officeRepair.getSatisfactionLevelT12();
					satisfactionLevelT34[ixNEmp][ixNWeeks][i] = officeRepair.getSatisfactionLevelT34();
					satisfactionLevelAll[ixNEmp][ixNWeeks][i] = officeRepair.getSatisfactionLevelAll();
					averageDailyCost[ixNEmp][ixNWeeks][i] = officeRepair.getAverageDailyCost();
				}
			}
		}
		displayTable(satisfactionLevelT12, satisfactionLevelT34, satisfactionLevelAll, averageDailyCost);
	}

	/*------------ Display the confidence intervals for various number of simulations --------------*/
	private static void displayTable(double[][][] satisfactionLevelT12, double[][][] satisfactionLevelT34, double[][][] satisfactionLevelAll, double[][][] averageDailyCost) {
		for (int ix1 = 0; ix1 < NUM_EMP_ARR.length; ix1++) {
			printALine(1); // -----------------------------------------------------------------------------------------
			System.out.printf(					"|                                                    Number of employees %d                                                 |\n",					NUM_EMP_ARR[ix1]);
			printALine(1); // -----------------------------------------------------------------------------------------
			System.out.printf("    tf:  |");
			for (int ix2 = 0; ix2 < NUM_WEEKS_ARR.length; ix2++)				System.out.printf("              %d weeks              |", NUM_WEEKS_ARR[ix2]);
			System.out.printf("\n");
			printALine(2); // -----------------------------------------------------------------------------------------
			System.out.printf("    n    |");
			for (int ix2 = 0; ix2 < NUM_WEEKS_ARR.length; ix2++)
				System.out.printf(" yb(n)      s(n)    z(n)  z(n)/yb(n)|");
			System.out.printf("\n");
			printALine(2); // -----------------------------------------------------------------------------------------
			System.out.printf(					"|                                          Satisfaction Level T12                                           |\n");
			printALine(1); // -----------------------------------------------------------------------------------------
			printCFIntervals(satisfactionLevelT12[ix1]);
			printALine(2); // -----------------------------------------------------------------------------------------
			System.out.printf(					"|                                           Satisfaction Level T34                                             |\n");
			printALine(1); // -----------------------------------------------------------------------------------------
			printCFIntervals(satisfactionLevelT34[ix1]);
			printALine(2); // -----------------------------------------------------------------------------------------
			System.out.printf(					"|                                          Satisfaction Level All                                           |\n");
			printALine(1); // -----------------------------------------------------------------------------------------
			printCFIntervals(satisfactionLevelAll[ix1]);
			printALine(2); // -----------------------------------------------------------------------------------------
			System.out.printf(					"|                                           Average Daily Cost                                            |\n");
			printALine(1); // -----------------------------------------------------------------------------------------
			printCFIntervals(averageDailyCost[ix1]);
			printALine(2); // -----------------------------------------------------------------------------------------
		}
	}

	private static void printALine(int numLines) {
		for (int i = 0; i < numLines; i++)
			System.out.printf(
					"+--------+------------------------------------+------------------------------------+------------------------------------+\n");
	}

	private static void printCFIntervals(double[][] output) {
		int numRuns;

		for (int ix1 = 0; ix1 < NUM_RUNS_ARRAY.length; ix1++) {
			numRuns = NUM_RUNS_ARRAY[ix1];
			System.out.printf("%8d |", numRuns);
			for (int ix2 = 0; ix2 < NUM_WEEKS_ARR.length; ix2++) {
				double[] values = new double[numRuns];
				for (int ix3 = 0; ix3 < numRuns; ix3++)
					values[ix3] = output[ix2][ix3];
				ConfidenceInterval ci = new ConfidenceInterval(values, CONF_LEVEL);
				System.out.printf("%8.3f %8.3f %8.3f %8.4f |", ci.getPointEstimate(), ci.getStdDev(), ci.getZeta(),
						ci.getZeta() / ci.getPointEstimate());
			}
			System.out.printf("\n");
		}
	}
}
