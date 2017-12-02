package simModel;

import cern.jet.random.engine.RandomSeedGenerator;

public class Seeds 
{
	/*
	 * Call Arrival
	 * --------------
	 * Exponential distribution
	 * Determined from calls per hour for each equipment type table
	 * Four call arrival streams, one for each equipment type
	 */
	protected int callArrival1000;  
	protected int callArrival2000;  
	protected int callArrival3000;  
	protected int callArrival4000;  
	
	/*
	 * Service Time
	 * ------------
	 * Gaussian distribution
	 * Determined from mean and standard deviation data files:
	 *   - Type1000.dat, Type2000.dat, Type3000.dat, Type4000.dat
	 */
	protected int serviceTime1000;
	protected int serviceTime2000;
	protected int serviceTime3000;
	protected int serviceTime4000;
	
	/*
	 * Service Type
	 * ------------
	 * Basic or Premium service 
	 * Determined from the percentage of basic and premium contracts per equipment type table  
	 */
	protected int serviceType;
	
	/*
	 * Travel Time
	 * -----------
	 * Triangular distribution
	 *   - Mean time of 30 minutes
	 *   - Minimum time of 10 minutes to reach a location
	 *   - Maximum time of 45 minutes
	 */
	protected int travelTime;

	
	public Seeds (RandomSeedGenerator rsg)
	{
		callArrival1000 = rsg.nextSeed();
		callArrival2000 = rsg.nextSeed();
		callArrival3000 = rsg.nextSeed();
		callArrival4000 = rsg.nextSeed();
		
		serviceTime1000 = rsg.nextSeed();
		serviceTime2000 = rsg.nextSeed();
		serviceTime3000 = rsg.nextSeed();
		serviceTime4000 = rsg.nextSeed();
		
		serviceType = rsg.nextSeed();
		travelTime = rsg.nextSeed();
	}
}