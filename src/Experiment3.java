// File: 
// Description:

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cern.jet.random.engine.*;
import outputAnalysis.ConfidenceInterval;
import simModel.OfficeRepair;
import simModel.Seeds;

public class Experiment3 {
	// Some experimental constants
	public static final int NUMRUNS = 30; // for exploring number of runs
	public static final int NUM_WEEKS = 20;
	public static final int[] NUM_EMP_ARR = { 10, 11 };
	public static final double WEEK = 7.0 * 24.0 * 60; // 7 day week, 24 hours/day
	public static final double WARM_UP_PERIOD = 30 * 60; // 30 hour warm up period, see WarmUp.java

	// Some arrays to collect experimental data
	// one output data set per end time for each case
	public static double[][] satisfactionLevelT12 = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] satisfactionLevelT34 = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] satisfactionLevelAll = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] averageDailyCost = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	private static PrintWriter pw ; 
	// For output analysis
	static final double CONF_LEVEL = 0.95;

	/** main method **/
	public static void main(String[] args) 
	{
		// Make some output files 
		
		File file = new File("2_Experiment2_Output_NumT12.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println("--- Experiment 2 Output --- ");
				
		
		
		
		double startTime = 0.0; // Observation interval starts at t = 0
		double endTime; // End time, rhs of observation interval - to be
						// determined experimentally
		Seeds[] sds = new Seeds[NUM_EMP_ARR.length*NUMRUNS];
		int numEmpT12; // parameter
		int numEmpAll; // parameter
		boolean useCRNsFlag = true;  // set to false to not use CRNs.
		OfficeRepair officeRepair; // reference to simulation model object
		// Lets get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUM_EMP_ARR.length*NUMRUNS; i++) sds[i] = new Seeds(rsg);
		
		for (int ixNEmp = 0; ixNEmp < NUM_EMP_ARR.length; ixNEmp++) 
		{
			numEmpT12 = NUM_EMP_ARR[ixNEmp];
			numEmpAll = NUM_EMP_ARR[ixNEmp];
			pw.println("Number of Employees of type T12 = " + numEmpT12);
			pw.println("Number of Employees of type ALL = " + numEmpAll);
			
			endTime = NUM_WEEKS * WEEK;
			pw.println("End Time = " + NUM_WEEKS + " weeks ("
					+ endTime + " hours), TimeStamp: "
					+ new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			for (int i = 0; i < NUMRUNS; i++) 
			{
				officeRepair = new OfficeRepair(startTime, sds[i+NUMRUNS*ixNEmp], false, numEmpT12, numEmpAll);
				officeRepair.setTimef(WARM_UP_PERIOD);
				officeRepair.runSimulation();
				officeRepair.setTimef(endTime);
				officeRepair.runSimulation();
				
				// Save the results in the arrays
				satisfactionLevelT12[ixNEmp][i] = officeRepair.getSatisfactionLevelT12();
				satisfactionLevelT34[ixNEmp][i] = officeRepair.getSatisfactionLevelT34();
				satisfactionLevelAll[ixNEmp][i] = officeRepair.getSatisfactionLevelAll();
				averageDailyCost[ixNEmp][i] = officeRepair.getAverageDailyCost();
			}
		}
		displayTable(satisfactionLevelT12, satisfactionLevelT34, satisfactionLevelAll, averageDailyCost);
	}

	/*------------ Display the confidence intervals for various number of simulations --------------*/
	private static void displayTable(double[][] satisfactionLevelT12, double[][] satisfactionLevelT34, double[][] satisfactionLevelAll, double[][] averageDailyCost) 
	{
		double[] satisfactionLevelT12Diff = new double[satisfactionLevelT12[0].length];
		double[] satisfactionLevelT34Diff = new double[satisfactionLevelT34[0].length];
		double[] satisfactionLevelAllDiff = new double[satisfactionLevelAll[0].length];
		double[] averageDailyCostDiff = new double[averageDailyCost[0].length];

		printALine(1); // -----------------------------------------------------------------------------------------
		pw.printf("|        |  Satisfaction Level T12    ||   Satisfaction Level T34      |        |   Satisfaction Level All    ||   Average Daily Cost      |\n");
		printALine(1); // -----------------------------------------------------------------------------------------
		pw.printf("| numEmp |             |             ||   numEmp    |             |\n");
		printALine(1); // -----------------------------------------------------------------------------------------
		pw.printf("|  Run   |");
		for (int cnt = 0; cnt < 2; cnt++) 
		{
			for (int ix2 = 0; ix2 < NUM_EMP_ARR.length; ix2++)
				pw.printf("%6d      |", NUM_EMP_ARR[ix2]);
			pw.printf(" Difference   ||");
		}
		pw.printf("\n");
		printALine(2); // -----------------------------------------------------------------------------------------
		// Print out the output and differences, fills in arrays of differences
		// for confidence intervals
		for (int runNum = 1; runNum <= NUMRUNS; runNum++) 
		{
			satisfactionLevelT12Diff[runNum - 1] = (satisfactionLevelT12[1][runNum - 1] - satisfactionLevelT12[0][runNum - 1]);
			satisfactionLevelT34Diff[runNum - 1] = (satisfactionLevelT34[1][runNum - 1] - satisfactionLevelT34[0][runNum - 1]);
			satisfactionLevelAllDiff[runNum - 1] = (satisfactionLevelAll[1][runNum - 1] - satisfactionLevelAll[0][runNum - 1]);
			averageDailyCostDiff[runNum - 1] = (averageDailyCost[1][runNum - 1] - averageDailyCost[0][runNum - 1]);
			pw.printf("|%8d|", runNum);
			pw.printf("%12.3f| %12.3f| %12.3f||",
					satisfactionLevelT12[0][runNum - 1],
					satisfactionLevelT12[1][runNum - 1],
					satisfactionLevelT12Diff[runNum - 1]);
			pw.printf("%12.3f| %12.3f| %12.3f|",
					satisfactionLevelT34[0][runNum - 1],
					satisfactionLevelT34[1][runNum - 1], 
					satisfactionLevelT34Diff[runNum - 1]);
			pw.printf("%12.3f| %12.3f| %12.3f||",
					satisfactionLevelAll[0][runNum - 1],
					satisfactionLevelAll[1][runNum - 1],
					satisfactionLevelAllDiff[runNum - 1]);
			pw.printf("%12.3f| %12.3f| %12.3f|",
					averageDailyCost[0][runNum - 1],
					averageDailyCost[1][runNum - 1], 
					averageDailyCostDiff[runNum - 1]);
			pw.printf("\n");
		}
		printALine(2); // -----------------------------------------------------------------------------------------
		ConfidenceInterval ciSatisfactionLevelT12Diff = new ConfidenceInterval(satisfactionLevelT12Diff, CONF_LEVEL);
		ConfidenceInterval ciSatisfactionLevelT34Diff = new ConfidenceInterval(satisfactionLevelT34Diff, CONF_LEVEL);
		ConfidenceInterval ciSatisfactionLevelAllDiff = new ConfidenceInterval(satisfactionLevelAllDiff, CONF_LEVEL);
		ConfidenceInterval ciAverageDailyCostDiff = new ConfidenceInterval(averageDailyCostDiff, CONF_LEVEL);
		pw.printf("  ybar(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionLevelT12Diff.getPointEstimate(), ciSatisfactionLevelT34Diff.getPointEstimate(), ciSatisfactionLevelAllDiff.getPointEstimate(), ciAverageDailyCostDiff.getPointEstimate());
		pw.printf("     s(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionLevelT12Diff.getStdDev(), ciSatisfactionLevelT34Diff.getStdDev(), ciSatisfactionLevelAllDiff.getStdDev(), ciAverageDailyCostDiff.getStdDev());
		pw.printf("  zeta(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionLevelT12Diff.getZeta(), ciSatisfactionLevelT34Diff.getZeta(), ciSatisfactionLevelAllDiff.getZeta(), ciAverageDailyCostDiff.getZeta());
		pw.printf("CI Min(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionLevelT12Diff.getCfMin(), ciSatisfactionLevelT34Diff.getCfMin(), ciSatisfactionLevelAllDiff.getCfMin(), ciAverageDailyCostDiff.getCfMin());
		pw.printf("CI Max(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionLevelT12Diff.getCfMax(), ciSatisfactionLevelT34Diff.getCfMax(), ciSatisfactionLevelAllDiff.getCfMax(), ciAverageDailyCostDiff.getCfMax());
		printALine(2); // -----------------------------------------------------------------------------------------
	}

	private static void printALine(int numLines) 
	{
		for (int i = 0; i < numLines; i++)
			pw.printf("+--------+------------+-------------+-------------++------------+-------------+-------------+\n");
	}
}
