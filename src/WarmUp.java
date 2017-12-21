// File: OfficeWarm.java
// Description:

import cern.jet.random.engine.*;
import outputAnalysis.WelchAverage;
import simModel.OfficeRepair;
import simModel.Seeds;

public class WarmUp {
	private static OfficeRepair officeRepair;
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
		double intervalLength = 60.0 * 24;   // 
		int numIntervals = 100;            // Total 60 week observation interval
		
		satisfactionLevelAll = new double[NUMRUNS][numIntervals];
		averageDailyCost = new double[NUMRUNS][numIntervals];
		
		// Parameters
		int numEmpT12 = 10;
		int numEmpAll = 10;

		System.out.println("Number of Employees T12: " + numEmpT12);
		System.out.println("Number of Employees ALL: " + numEmpAll);
		for(int i= 0 ; i < NUMRUNS ; i++) {
			// For computing warmup, compute average over intervalLength for numIntervals
			// Setup the simulation object
			
			officeRepair = new OfficeRepair(0, sds[i], false, numEmpT12, numEmpAll);
			// Loop for the all intervals
			for( int interval=0 ; interval<numIntervals ; interval++) {
				// Run the simulation for an interval
				intervalStart = interval*intervalLength;
				intervalEnd = intervalStart+intervalLength;
				
				officeRepair.setTimef(intervalEnd);
				officeRepair.runSimulation();
				
				// compute scalar output
				satisfactionLevelAll[i][interval]= officeRepair.getSatisfactionLevelAll() ; // save in matrix
				averageDailyCost[i][interval]= officeRepair.getAverageDailyCost(); // save in matrix
			}
		}
		int [] wSizeSatisfactionLevelAll = { 0, 1, 3 };
		int [] wSizeAverageDailyCost = { 0, 1, 3 };
		
		WelchAverage welchAvgSatisfactionLevelAll = new WelchAverage(satisfactionLevelAll, wSizeSatisfactionLevelAll);
		WelchAverage welchAvgAverageDailyCost = new WelchAverage(averageDailyCost, wSizeAverageDailyCost);
		
		System.out.println("Average Satisfaction Level All");
		printWelchOutputMatrix(welchAvgSatisfactionLevelAll.getWelchAvgOutput(), wSizeSatisfactionLevelAll, 1);
		System.out.println("Average Daily Cost");
		printWelchOutputMatrix(welchAvgAverageDailyCost.getWelchAvgOutput(), wSizeAverageDailyCost, 1);
		System.out.close();
	}

	private static void  printWelchOutputMatrix(double[][] m, int [] w, double intervalLength)
	{
		int ix, jx;
		// Print out the window Sizes
		System.out.print("t,");
		for(ix=0; ix < w.length-1; ix++) 
			System.out.print("w = "+w[ix]+",");
		System.out.println("w = "+w[ix]); // Last one
		// Let's output the data
		for(jx = 0 ; jx < m[0].length ; jx++)  // print rows as columns
		{
			System.out.print( ((jx+1)*intervalLength)+", ");
			for(ix = 0 ; ix < m.length ; ix++) // columns becomes rows
			{
				if(jx < m[ix].length)  
					System.out.print(m[ix][jx]); // rows have different lengths, assumes row 0 is longest		         
				if(ix != m.length-1 && jx < m[ix+1].length) 
					System.out.print(", "); // more to come
				else if(jx<m[ix].length) 
					System.out.println();   // Assumes that all rows decrease in length
			}
		}
	}
}
