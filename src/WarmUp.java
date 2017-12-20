// File: OfficeWarm.java
// Description:

import cern.jet.random.engine.*;
import outputAnalysis.WelchAverage;
import simModel.OfficeRepair;
import simModel.Seeds;

public class WarmUp {
	private static OfficeRepair officeRepair;
	private static double[][] satisfactionLevelT12;
	private static double[][] satisfactionLevelT34;
	private static double[][] satisfactionLevelAll;
	private static double[][] averageDailyCost;

	/** main method **/
	public static void main(String[] args)
	{
		int NUMRUNS = 10;
		Seeds[] sds = new Seeds[NUMRUNS];
		
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for(int i=0 ; i<NUMRUNS ; i++)
			sds[i] = new Seeds(rsg);
		
		double intervalStart, intervalEnd;  // start and end times of current interval
		double intervalLength = 60.0;   // 
		int numIntervals = 60;            // Total 60 week observation interval
		
		satisfactionLevelT12 = new double[NUMRUNS][numIntervals];
		satisfactionLevelT34 = new double[NUMRUNS][numIntervals];
		satisfactionLevelAll = new double[NUMRUNS][numIntervals];
		averageDailyCost = new double[NUMRUNS][numIntervals];

		for (int numEmpT12 = 8; numEmpT12 <= 8; numEmpT12++) {
			for (int numEmpAll = 8; numEmpAll <= 8; numEmpAll++) {
				System.out.println("Number of Employees T12: " + numEmpT12);
				System.out.println("Number of Employees ALL: " + numEmpAll);
				for(int i= 0 ; i < NUMRUNS ; i++) {
					// For computing warmup, compute average over intervalLength for numIntervals
					// Setup the simulation object
					
					officeRepair = new OfficeRepair(0, sds[i], true, numEmpT12, numEmpAll);
					// Loop for the all intervals
					for( int interval=0 ; interval<numIntervals ; interval++) {
						// Run the simulation for an interval
						intervalStart = interval*intervalLength;
						intervalEnd = intervalStart+intervalLength;
						
						officeRepair.setTimef(intervalEnd);
						officeRepair.runSimulation();
						
						// compute scalar output
						satisfactionLevelT12[i][interval]= officeRepair.getSatisfactionLevelT12() ; // save in matrix
						satisfactionLevelT34[i][interval]= officeRepair.getSatisfactionLevelT34(); // save in matrix
						satisfactionLevelAll[i][interval]= officeRepair.getSatisfactionLevelAll() ; // save in matrix
						averageDailyCost[i][interval]= officeRepair.getAverageDailyCost(); // save in matrix
					}
				}
				int [] wSizeSatisfactionLevelT12 = { 0, 1, 3, 5 };
				int [] wSizeSatisfactionLevelT34 = { 0, 1, 3, 5 };
				int [] wSizeSatisfactionLevelAll = { 0, 1, 3, 5 };
				int [] wSizeAverageDailyCost = { 0, 1, 3, 5 };
				
				WelchAverage welchAvgSatisfactionLevelT12 = new WelchAverage(satisfactionLevelT12, wSizeSatisfactionLevelT12);
				WelchAverage welchAvgStatisfactionLevelT34 = new WelchAverage(satisfactionLevelT34, wSizeSatisfactionLevelT34);
				WelchAverage welchAvgSatisfactionLevelAll = new WelchAverage(satisfactionLevelT12, wSizeSatisfactionLevelAll);
				WelchAverage welchAvgAverageDailyCost = new WelchAverage(satisfactionLevelT34, wSizeAverageDailyCost);
				
				System.out.println("Average Satisfaction Level T12");
				printWelchOutputMatrix(welchAvgSatisfactionLevelT12.getWelchAvgOutput(), wSizeSatisfactionLevelT12, 1);
				System.out.println("Average Satisfaction Level T34");
				printWelchOutputMatrix(welchAvgStatisfactionLevelT34.getWelchAvgOutput(), wSizeSatisfactionLevelT34, 1);
				System.out.println("Average Satisfaction Level All");
				printWelchOutputMatrix(welchAvgSatisfactionLevelAll.getWelchAvgOutput(), wSizeSatisfactionLevelAll, 1);
				System.out.println("Average Daily Cost");
				printWelchOutputMatrix(welchAvgAverageDailyCost.getWelchAvgOutput(), wSizeAverageDailyCost, 1);
			}
		}
	}

	private static void  printWelchOutputMatrix(double[][] m, int [] w, double intervalLength)
	{
		int ix, jx;
		// Print out the window Sizes
		System.out.print("t,");
		for(ix=0; ix < w.length-1; ix++) System.out.print("w = "+w[ix]+",");
		System.out.println("w = "+w[ix]); // Last one
		// Let's output the data
		for(jx = 0 ; jx < m[0].length ; jx++)  // print rows as columns
		{
			System.out.print( ((jx+1)*intervalLength)+", ");
			for(ix = 0 ; ix < m.length ; ix++) // columns becomes rows
			{
				if(jx < m[ix].length)  System.out.print(m[ix][jx]); // rows have different lengths, assumes row 0 is longest		         
				if(ix != m.length-1 && jx < m[ix+1].length) System.out.print(", "); // more to come
				else if(jx<m[ix].length) System.out.println();   // Assumes that all rows decrease in length
			}
		}
	}
}
