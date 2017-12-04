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
	Seeds seed;

	// Constructor
	protected RVPs(OfficeRepair model, Seeds sd) 
	{ 
		this.model = model; 
		seed = sd;
		
		// Set up distribution functions			
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
	private double meanSrvTm2000 = 44.36;
	private double meanSrvTm3000 = 60.27;
	private double meanSrvTm4000 = 130.17;
	
	private double stdDevSrvTm1000 = 5.85;
	private double stdDevSrvTm2000 = 15.06;
	private double stdDevSrvTm3000 = 27.40;
	private double stdDevSrvTm4000 = 29.84;
	
	private double minTravelTm = 10;
	private double maxTravelTm = 45;
	private double meanTravelTm = 30;
	
	private double percentBasicContracts1000 = 0.35;
	private double percentBasicContracts2000 = 0.35;
	private double percentBasicContracts3000 = 0.25;
	private double percentBasicContracts4000 = 0.15;
	
	
	protected ServiceTypes uServiceType(EquipmentTypes equipType) {
		double randNum = serviceType.nextDouble();
		ServiceTypes serviceType = ServiceTypes.BASIC;
		
		if (equipType == EquipmentTypes.E1000) {
			if (randNum < percentBasicContracts1000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.E2000) {
			if (randNum < percentBasicContracts2000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.E3000) {
			if (randNum < percentBasicContracts3000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.E4000)
			if (randNum < percentBasicContracts4000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		return serviceType;
	}
	
	protected double DuCallArrival1000() {
		if ((model.getClock() % 1440) < 480) {
			double meanCallArr1000 = getMean1000();
			callArrival1000 = new Exponential(1.0/meanCallArr1000, new MersenneTwister(seed.callArrival1000));
			double time = 60 / meanCallArr1000;
			return time + model.getClock();
		} else {
			return model.getClock() + 1440;
		}
	}
	
	protected double DuCallArrival2000() {
		if ((model.getClock() % 1440) < 480) {
			double meanCallArr2000 = getMean2000();
			callArrival2000 = new Exponential(1.0/meanCallArr2000, new MersenneTwister(seed.callArrival2000));
			double time = 60 / meanCallArr2000;
		    return time + model.getClock();
		} else {
			return model.getClock() + 1440;
		}
	}
	
	protected double DuCallArrival3000() {
		if ((model.getClock() % 1440) < 480) {
			double meanCallArr3000 = getMean3000();
			callArrival3000 = new Exponential(1.0/meanCallArr3000, new MersenneTwister(seed.callArrival3000));
			double time = 60 / meanCallArr3000;
		    return time + model.getClock();
		} else {
			return model.getClock() + 1440;
		}
	}
	
	protected double DuCallArrival4000() {
		if ((model.getClock() % 1440) < 480) {
			double meanCallArr4000 = getMean4000();
			callArrival4000 = new Exponential(1.0/meanCallArr4000, new MersenneTwister(seed.callArrival4000));
			double time = 60 / meanCallArr4000;
		    return time + model.getClock();
		} else {
			return model.getClock() + 1440;
		}
	}

	protected double uServiceTime1000() {
	    return serviceTime1000.nextDouble();
	}
	
	protected double uServiceTime2000() {
	    return serviceTime2000.nextDouble();
	}
	
	protected double uServiceTime3000() {
	    return serviceTime3000.nextDouble();
	}
	
	protected double uServiceTime4000()  {
	    return serviceTime4000.nextDouble();
	}
	
	protected double uTravelTime() {
	    return (travelTime.next());
	}
	
	
	private double getMean1000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 7; 	break;
		case 1: mean = 12; 	break;
		case 2: mean = 10; 	break;
		case 3: mean = 7; 	break;
		case 4: mean = 5; 	break;
		case 5: mean = 4; 	break;
		case 6: mean = 5; 	break;
		case 7: mean = 4; 	break;
		case 8: mean = 3; 	break;
		default: mean = 60; 	break;
		}
		
		return mean;
	}
	
	private double getMean2000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 8; 	break;
		case 1: mean = 11; 	break;
		case 2: mean = 8; 	break;
		case 3: mean = 9; 	break;
		case 4: mean = 6; 	break;
		case 5: mean = 4; 	break;
		case 6: mean = 3; 	break;
		case 7: mean = 3; 	break;
		case 8: mean = 2; 	break;
		default: mean = 60; 	break;
		}
		
		return mean;
	}
	
	private double getMean3000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 5; 	break;
		case 1: mean = 6; 	break;
		case 2: mean = 5; 	break;
		case 3: mean = 4; 	break;
		case 4: mean = 3; 	break;
		case 5: mean = 3; 	break;
		case 6: mean = 2; 	break;
		case 7: mean = 2; 	break;
		case 8: mean = 1; 	break;
		default: mean = 60; 	break;
		}
		
		return mean;
	}
	
	private double getMean4000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 2; 	break;
		case 1: mean = 3; 	break;
		case 2: mean = 4; 	break;
		case 3: mean = 3; 	break;
		case 4: mean = 2; 	break;
		case 5: mean = 1; 	break;
		case 6: mean = 1; 	break;
		case 7: mean = 1; 	break;
		case 8: mean = 1; 	break;
		default: mean = 60; 	break;
		}
		
		return mean;
	}

}
