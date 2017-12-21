// File: 
// Description:

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
	public static final int[] NUM_EMP_ARR = { 12, 13 };
	public static final double WEEK = 7.0 * 24.0 * 60; // 7 day week, 24 hours/day, 60 minutes / hour
	public static final double WARM_UP_PERIOD = 70 * 24; // 60 day WarmUp, see WarmUp for more details

	// Some arrays to collect experimental data
	// one output data set per end time for each case
	public static double[][] satisfactionT12Mat = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] satisfactionT34Mat = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] satisfactionAllMat = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	public static double[][] averageDailyCostMat = new double[NUM_EMP_ARR.length][NUMRUNS]; 
	
	// For output analysis
	static final double CONF_LEVEL = 0.95;

	/** main method **/
	public static void main(String[] args) 
	{
		double startTime = 0.0; // Observation interval starts at t = 0
		double endTime; // End time, rhs of observation interval - to be
						// determined experimentally
		Seeds[] sds = new Seeds[NUM_EMP_ARR.length*NUMRUNS];  // To be able to run with and without CRNs
		boolean useCRNsFlag = true;  // set to false to not use CRNs.
		
		int numEmpT12; // parameter
		int numEmpAll; // parameter

		OfficeRepair officeRepair; // reference to simulation model object
		// Lets get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUM_EMP_ARR.length*NUMRUNS; i++) 
			sds[i] = new Seeds(rsg);
		
		for (int ixNEmp = 0; ixNEmp < NUM_EMP_ARR.length; ixNEmp++) 
		{
			numEmpT12 = NUM_EMP_ARR[ixNEmp];
			numEmpAll = 13;
			
			System.out.println("Number of Employees of type T12 = " + numEmpT12);
			System.out.println("Number of Employees of type ALL = " + numEmpAll);
			
			endTime = NUM_WEEKS * WEEK;
			System.out.println("End Time = " + NUM_WEEKS + " weeks ("+ endTime + " hours), TimeStamp: "+ new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			for (int i = 0; i < NUMRUNS; i++) 
			{
				if(useCRNsFlag) officeRepair = new OfficeRepair(startTime, sds[i], false, numEmpT12, numEmpAll);
				else officeRepair = new OfficeRepair(startTime, sds[i+NUMRUNS*ixNEmp], false, numEmpT12, numEmpAll);
				officeRepair.setTimef(WARM_UP_PERIOD);
				officeRepair.runSimulation();

				officeRepair.setTimef(endTime);
				officeRepair.runSimulation();
				
				// Save the results in the arrays
				satisfactionT12Mat[ixNEmp][i] = officeRepair.getSatisfactionLevelT12();
				satisfactionT34Mat[ixNEmp][i] = officeRepair.getSatisfactionLevelT34();
				satisfactionAllMat[ixNEmp][i] = officeRepair.getSatisfactionLevelT12();
				averageDailyCostMat[ixNEmp][i] = officeRepair.getAverageDailyCost();
			}
		}
		displayTable1();
		displayTable2();
	}

	/*------------ Display the confidence intervals for various number of simulations --------------*/
	private static void displayTable1() 
	{
		double[] satisfactionT12Diff = new double[satisfactionT12Mat[0].length];
		double[] satisfactionT34Diff = new double[satisfactionT34Mat[0].length];

		printALine(1); // -----------------------------------------------------------------------------------------
		System.out.printf("|        |   Satisfaction Level T12               ||        Satisfaction Level T34          |\n");
		printALine(1); // -----------------------------------------------------------------------------------------
		System.out.printf("|  Run   |");
		for (int cnt = 0; cnt < 2; cnt++) 
		{
			for (int ix2 = 0; ix2 < NUM_EMP_ARR.length; ix2++)
				System.out.printf("%6d      |", NUM_EMP_ARR[ix2]);
			System.out.printf(" Difference   ||");
		}
		System.out.printf("\n");
		printALine(2); // -----------------------------------------------------------------------------------------
		// Print out the output and differences, fills in arrays of differences
		// for confidence intervals
		for (int runNum = 1; runNum <= NUMRUNS; runNum++) 
		{
			satisfactionT12Diff[runNum - 1] = (satisfactionT12Mat[1][runNum - 1] - satisfactionT12Mat[0][runNum - 1]);
			satisfactionT34Diff[runNum - 1] = (satisfactionT34Mat[1][runNum - 1] - satisfactionT34Mat[0][runNum - 1]);
			System.out.printf("|%8d|", runNum);
			System.out.printf("%12.3f| %12.3f| %12.3f||",
					satisfactionT12Mat[0][runNum - 1],
					satisfactionT12Mat[1][runNum - 1],
					satisfactionT12Diff[runNum - 1]);
			System.out.printf("%12.3f| %12.3f| %12.3f|",
					satisfactionT34Mat[0][runNum - 1],
					satisfactionT34Mat[1][runNum - 1], 
					satisfactionT34Diff[runNum - 1]);
			System.out.printf("\n");
		}
		printALine(2); // -----------------------------------------------------------------------------------------
		ConfidenceInterval ciSatisfactionT12Diff = new ConfidenceInterval(satisfactionT12Diff, CONF_LEVEL);
		ConfidenceInterval ciSatisfactionT34Diff = new ConfidenceInterval(satisfactionT34Diff, CONF_LEVEL);
		System.out.printf("  ybar(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionT12Diff.getPointEstimate(),ciSatisfactionT34Diff.getPointEstimate());
		System.out.printf("     s(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionT12Diff.getStdDev(),ciSatisfactionT34Diff.getStdDev());
		System.out.printf("  zeta(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionT12Diff.getZeta(), ciSatisfactionT34Diff.getZeta());
		System.out.printf("CI Min(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionT12Diff.getCfMin(), ciSatisfactionT34Diff.getCfMin());
		System.out.printf("CI Max(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionT12Diff.getCfMax(), ciSatisfactionT34Diff.getCfMax());
		printALine(2); // -----------------------------------------------------------------------------------------
	}
	
	/*------------ Display the confidence intervals for various number of simulations --------------*/
	private static void displayTable2() 
	{
		double[] satisfactionAllDiff = new double[satisfactionAllMat[0].length];
		double[] averageDailyCostDiff = new double[averageDailyCostMat[0].length];

		printALine(1); // -----------------------------------------------------------------------------------------
		System.out.printf("|        |   Satisfaction Level ALL               ||            Average Daily Cost          |\n");
		printALine(1); // -----------------------------------------------------------------------------------------
		System.out.printf("|  Run   |");
		for (int cnt = 0; cnt < 2; cnt++) 
		{
			for (int ix2 = 0; ix2 < NUM_EMP_ARR.length; ix2++)
				System.out.printf("%6d      |", NUM_EMP_ARR[ix2]);
			System.out.printf(" Difference   ||");
		}
		System.out.printf("\n");
		printALine(2); // -----------------------------------------------------------------------------------------
		// Print out the output and differences, fills in arrays of differences
		// for confidence intervals
		for (int runNum = 1; runNum <= NUMRUNS; runNum++) 
		{
			satisfactionAllDiff[runNum - 1] = (satisfactionAllMat[1][runNum - 1] - satisfactionAllMat[0][runNum - 1]);
			averageDailyCostDiff[runNum - 1] = (averageDailyCostMat[1][runNum - 1] - averageDailyCostMat[0][runNum - 1]);
			System.out.printf("|%8d|", runNum);
			System.out.printf("%12.3f| %12.3f| %12.3f||",
					satisfactionAllMat[0][runNum - 1],
					satisfactionAllMat[1][runNum - 1],
					satisfactionAllDiff[runNum - 1]);
			System.out.printf("%12.3f| %12.3f| %12.3f|",
					averageDailyCostMat[0][runNum - 1],
					averageDailyCostMat[1][runNum - 1], 
					averageDailyCostDiff[runNum - 1]);
			System.out.printf("\n");
		}
		printALine(2); // -----------------------------------------------------------------------------------------
		ConfidenceInterval ciSatisfactionAllDiff = new ConfidenceInterval(satisfactionAllDiff, CONF_LEVEL);
		ConfidenceInterval ciAverageDailyCostDiff = new ConfidenceInterval(averageDailyCostDiff, CONF_LEVEL);
		System.out.printf("  ybar(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionAllDiff.getPointEstimate(),ciAverageDailyCostDiff.getPointEstimate());
		System.out.printf("     s(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionAllDiff.getStdDev(),ciAverageDailyCostDiff.getStdDev());
		System.out.printf("  zeta(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionAllDiff.getZeta(), ciAverageDailyCostDiff.getZeta());
		System.out.printf("CI Min(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionAllDiff.getCfMin(), ciAverageDailyCostDiff.getCfMin());
		System.out.printf("CI Max(n)| %39.3f|| %39.3f|\n",
				ciSatisfactionAllDiff.getCfMax(), ciAverageDailyCostDiff.getCfMax());
		printALine(2); // -----------------------------------------------------------------------------------------
	}

	private static void printALine(int numLines) 
	{
		for (int i = 0; i < numLines; i++)
			System.out.printf("+--------+------------+-------------+-------------++------------+-------------+-------------+\n");
	}
}
