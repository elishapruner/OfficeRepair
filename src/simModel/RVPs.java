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
		interArrDist1000 = new Exponential(1.0/(getMean1000()), new MersenneTwister(seed.callArrival1000));
		interArrDist2000 = new Exponential(1.0/(getMean2000()), new MersenneTwister(seed.callArrival2000));
		interArrDist3000 = new Exponential(1.0/(getMean3000()), new MersenneTwister(seed.callArrival3000));
		interArrDist4000 = new Exponential(1.0/(getMean4000()), new MersenneTwister(seed.callArrival4000));
		
		serviceTime1000 =  new Normal(meanSrvTm1000, stdDevSrvTm1000, new MersenneTwister(sd.serviceTime1000));
		serviceTime2000 =  new Normal(meanSrvTm2000, stdDevSrvTm2000, new MersenneTwister(sd.serviceTime2000));
		serviceTime3000 =  new Normal(meanSrvTm3000, stdDevSrvTm3000, new MersenneTwister(sd.serviceTime3000));
		serviceTime4000 =  new Normal(meanSrvTm4000, stdDevSrvTm4000, new MersenneTwister(sd.serviceTime4000));
		
		serviceType = new MersenneTwister(sd.serviceType);
		travelTime = new TriangularVariate(minTravelTm, meanTravelTm, maxTravelTm, new MersenneTwister(sd.travelTime));
	}
	
	/* Random Variate Procedure for Arrivals */
	private Exponential interArrDist1000;  
	private Exponential interArrDist2000;  
	private Exponential interArrDist3000;  
	private Exponential interArrDist4000; 
	
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
		
		if (equipType == EquipmentTypes.TYPE1000) {
			if (randNum < percentBasicContracts1000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.TYPE2000) {
			if (randNum < percentBasicContracts2000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.TYPE3000) {
			if (randNum < percentBasicContracts3000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		} else if (equipType == EquipmentTypes.TYPE4000)
			if (randNum < percentBasicContracts4000) {
				serviceType = ServiceTypes.BASIC;
			} else {
				serviceType = ServiceTypes.PREMIUM;
			}
		return serviceType;
	}
	
	
	protected double uServiceTime(EquipmentTypes equipType) {
		double serviceTime = 0;
		
		if (equipType == EquipmentTypes.TYPE1000) {
			serviceTime =  serviceTime1000.nextDouble();
		} else if (equipType == EquipmentTypes.TYPE2000) {
			serviceTime =  serviceTime2000.nextDouble();
		} else if (equipType == EquipmentTypes.TYPE3000) {
			serviceTime =  serviceTime3000.nextDouble();
		} else if (equipType == EquipmentTypes.TYPE4000) {
			serviceTime =  serviceTime4000.nextDouble();
		}
		
		return serviceTime;
	}
	
	
	protected double uTravelTime() {
	    return travelTime.next();
	}
	
	
	protected double DuCallArrival1000() {
		  double nxtArrival;
		  double mean = getMean1000();
//		  System.out.println("**MODEL_LOGIC_CHECK");
//		  System.out.println("Clock: "+model.getClock()+"Clock of Day: "+(((int) model.getClock()) % 1440));
		  double currTime = model.getClock() % 1440;
		  if (((int) currTime) > 540) {
//			  System.out.println("?***RUNNING_NEXT_ARRIVAL");
			  double jumpTime = 1440 - currTime;
			  nxtArrival = model.getClock() + jumpTime ;
		  } else {
			  nxtArrival = model.getClock() + interArrDist1000.nextDouble(1.0/mean);
		  }
		  
	      return(nxtArrival);
	}
	
	protected double DuCallArrival2000() {
		  double nxtArrival;
		  double mean = getMean2000();
		  
		  double currTime = model.getClock() % 1440;
		  if (((int) currTime) > 540) {
			  //System.out.println("?***RUNNING_NEXT_ARRIVAL");
			  double jumpTime = 1440 - currTime;
			  nxtArrival = model.getClock() + jumpTime ;
		  } else {
			  nxtArrival = model.getClock() + interArrDist2000.nextDouble(1.0/mean);
		  }
		  
	      return(nxtArrival);
	}
	
	protected double DuCallArrival3000() {
		  double nxtArrival;
		  double mean = getMean3000();
		  
		  double currTime = model.getClock() % 1440;
		  if (((int) currTime) > 540) {
			  //System.out.println("?***RUNNING_NEXT_ARRIVAL");
			  double jumpTime = 1440 - currTime;
			  nxtArrival = model.getClock() + jumpTime ;
		  } else {
			  nxtArrival = model.getClock() + interArrDist3000.nextDouble(1.0/mean);
		  }
	      return(nxtArrival);
	}
	
	protected double DuCallArrival4000() {
		  double nxtArrival;
		  double mean = getMean4000();
		  
		  double currTime = model.getClock() % 1440;
		  if (((int) currTime) > 540) {
//			  System.out.println("?***RUNNING_NEXT_ARRIVAL");
			  double jumpTime = 1440 - currTime;
			  nxtArrival = model.getClock() + jumpTime ;
		  } else {
			  nxtArrival = model.getClock() + interArrDist4000.nextDouble(1.0/mean);
		  }
	      return(nxtArrival);
	}
	
	
	private double getMean1000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;

		switch (timeCategory) {
		case 0: mean = 60.0 / 7.0; 	break;
		case 1: mean = 60.0 / 12.0;	break;
		case 2: mean = 60.0 / 10.0; break;
		case 3: mean = 60.0 / 7.0; 	break;
		case 4: mean = 60.0 / 5.0; 	break;
		case 5: mean = 60.0 / 4.0; 	break;
		case 6: mean = 60.0 / 5.0; 	break;
		case 7: mean = 60.0 / 4.0; 	break;
		case 8: mean = 60.0 / 3.0; 	break;
		default: mean = 1;			break;
		}
		
		return mean;
	}
	
	private double getMean2000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 60.0 / 8.0; 	break;
		case 1: mean = 60.0 / 11.0; break;
		case 2: mean = 60.0 / 8.0; 	break;
		case 3: mean = 60.0 / 9.0; 	break;
		case 4: mean = 60.0 / 6.0; 	break;
		case 5: mean = 60.0 / 4.0; 	break;
		case 6: mean = 60.0 / 3.0; 	break;
		case 7: mean = 60.0 / 3.0; 	break;
		case 8: mean = 60.0 / 2.0; 	break;
		default: mean = 1; 			break;
		}
		
		return mean;
	}
	
	private double getMean3000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 60.0 / 5.0; 	break;
		case 1: mean = 60.0 / 6.0; 	break;
		case 2: mean = 60.0 / 5.0; 	break;
		case 3: mean = 60.0 / 4.0; 	break;
		case 4: mean = 60.0 / 3.0; 	break;
		case 5: mean = 60.0 / 3.0; 	break;
		case 6: mean = 60.0 / 2.0; 	break;
		case 7: mean = 60.0 / 2.0; 	break;
		case 8: mean = 60.0 / 1.0; 	break;
		default: mean = 1; 			break;
		}
		
		return mean;
	}
	
	private double getMean4000() {
		int timeCategory = (int)((model.getClock() % 1440) / 60);
		double mean;
		
		switch (timeCategory) {
		case 0: mean = 60.0 / 2.0; 	break;
		case 1: mean = 60.0 / 3.0; 	break;
		case 2: mean = 60.0 / 4.0; 	break;
		case 3: mean = 60.0 / 3.0; 	break;
		case 4: mean = 60.0 / 2.0; 	break;
		case 5: mean = 60.0 / 1.0; 	break;
		case 6: mean = 60.0 / 1.0; 	break;
		case 7: mean = 60.0 / 1.0; 	break;
		case 8: mean = 60.0 / 1.0; 	break;
		default: mean = 1; 			break;
		}
		
		return mean;
	}

}
