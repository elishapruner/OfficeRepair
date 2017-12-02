package simModel;

import cern.jet.random.Exponential;
import cern.jet.random.Normal;
import cern.jet.random.engine.MersenneTwister;
import dataModelling.TriangularVariate;
import simModel.Call.EquipmentTypes;
import simModel.Call.ServiceTypes;

class RVPs 
{
	OfficeRepair model; // for accessing the clock
    // Data Models - i.e. random veriate generators for distributions
	// are created using Colt classes, define 
	// reference variables here and create the objects in the
	// constructor with seeds

	// Constructor
	protected RVPs(OfficeRepair model, Seeds sd) 
	{ 
		this.model = model; 
		// Set up distribution functions		
		double meanCallArr1000 = getMean1000();
		callArrival1000 = new Exponential(1.0/meanCallArr1000, new MersenneTwister(sd.callArrival1000));
		
		double meanCallArr2000 = getMean2000();
		callArrival2000 = new Exponential(1.0/meanCallArr2000, new MersenneTwister(sd.callArrival2000));
		
		double meanCallArr3000 = getMean3000();
		callArrival3000 = new Exponential(1.0/meanCallArr3000, new MersenneTwister(sd.callArrival3000));
		
		double meanCallArr4000 = getMean4000();
		callArrival4000 = new Exponential(1.0/meanCallArr4000, new MersenneTwister(sd.callArrival4000));
		
		serviceTime1000 =  new Normal(meanSrvTm1000, stdDevSrvTm1000, new MersenneTwister(sd.serviceTime1000));
		serviceTime2000 =  new Normal(meanSrvTm2000, stdDevSrvTm2000, new MersenneTwister(sd.serviceTime2000));
		serviceTime3000 =  new Normal(meanSrvTm3000, stdDevSrvTm3000, new MersenneTwister(sd.serviceTime3000));
		serviceTime4000 =  new Normal(meanSrvTm4000, stdDevSrvTm4000, new MersenneTwister(sd.serviceTime4000));
		
		serviceType = new MersenneTwister(sd.serviceType);
		travelTime = new TriangularVariate(minTravelTm, meanTravelTm, maxTravelTm, new MersenneTwister(sd.travelTime));
	}
	
	/* Random Variate Procedure for Arrivals */
	private Exponential callArrival1000;  
	private Exponential callArrival2000;  
	private Exponential callArrival3000;  
	private Exponential callArrival4000; 
	
	private Normal serviceTime1000;
	private Normal serviceTime2000;
	private Normal serviceTime3000;
	private Normal serviceTime4000;
	
	private MersenneTwister serviceType;
	private TriangularVariate travelTime;
	
	
	private double meanSrvTm1000 = 14.79;
	private double stdDevSrvTm1000 = 5.85;
	
	private double meanSrvTm2000 = 44.36;
	private double stdDevSrvTm2000 = 15.06;
	
	private double meanSrvTm3000 = 60.27;
	private double stdDevSrvTm3000 = 27.40;
	
	private double meanSrvTm4000 = 130.17;
	private double stdDevSrvTm4000 = 29.84;
	
	private double minTravelTm = 10;
	private double maxTravelTm = 45;
	private double meanTravelTm = 30;
	
	private double percentBasicContracts1000 = 0.35;
	private double percentBasicContracts2000 = 0.35;
	private double percentBasicContracts3000 = 0.25;
	private double percentBasicContracts4000 = 0.15;


	protected double DuCallArrival1000()  // for getting next value of duInput
	{
	    double nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double DuCallArrival2000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival2000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double DuCallArrival3000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival3000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double DuCallArrival4000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival4000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	
	protected ServiceTypes uServiceType(EquipmentTypes equipType)  // for getting next value of duInput
	{
		double randNum = serviceType.nextDouble();
		ServiceTypes serviceType = ServiceTypes.BASIC;
		
		if (equipType.equals(EquipmentTypes.E1000)) {
			if (randNum < percentBasicContracts1000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType.equals(EquipmentTypes.E2000)) {
			if (randNum < percentBasicContracts2000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType.equals(EquipmentTypes.E3000)) {
			if (randNum < percentBasicContracts3000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType.equals(EquipmentTypes.E4000))
			if (randNum < percentBasicContracts4000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		return serviceType;
	}
	
	protected double uServiceTime1000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double uServiceTime2000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double uServiceTime3000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double uServiceTime4000()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	protected double uTravelTime()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = callArrival1000.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr + model.getClock());
	}
	
	
	private double getMean1000() {
		int timeCategory = (int)(model.getClock() / 60) % 24;
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 7; break;
		case 1: mean = 12; break;
		case 2: mean = 10; break;
		case 3: mean = 7; break;
		case 4: mean = 5; break;
		case 5: mean = 4; break;
		case 6: mean = 5; break;
		case 7: mean = 4; break;
		case 8: mean = 3; break;
		default: mean = 0; break;
		}
		
		return mean;
	}
	
	private double getMean2000() {
		int timeCategory = (int)(model.getClock() / 60) % 24;
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 8; break;
		case 1: mean = 11; break;
		case 2: mean = 8; break;
		case 3: mean = 9; break;
		case 4: mean = 6; break;
		case 5: mean = 4; break;
		case 6: mean = 3; break;
		case 7: mean = 3; break;
		case 8: mean = 2; break;
		default: mean = 0; break;
		}
		
		return mean;
	}
	
	private double getMean3000() {
		int timeCategory = (int)(model.getClock() / 60) % 24;
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 5; break;
		case 1: mean = 6; break;
		case 2: mean = 5; break;
		case 3: mean = 4; break;
		case 4: mean = 3; break;
		case 5: mean = 3; break;
		case 6: mean = 2; break;
		case 7: mean = 2; break;
		case 8: mean = 1; break;
		default: mean = 0; break;
		}
		
		return mean;
	}
	
	private double getMean4000() {
		int timeCategory = (int)(model.getClock() / 60) % 24;
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 2; break;
		case 1: mean = 3; break;
		case 2: mean = 4; break;
		case 3: mean = 3; break;
		case 4: mean = 2; break;
		case 5: mean = 1; break;
		case 6: mean = 1; break;
		case 7: mean = 1; break;
		case 8: mean = 1; break;
		default: mean = 0; break;
		}
		
		return mean;
	}

}
