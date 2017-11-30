package simModel;

import cern.jet.random.Exponential;
import cern.jet.random.engine.MersenneTwister;
import simModel.Call.serviceType;


class RVPs 
{
	OfficeRepair model; 
	
	// Random Variate Procedure:  CallType - type of next Call
			private double PROPE1000 = 0.37;
			private double PROPE2000 = 0.33 + PROPE1000;
			private double PROPE3000 = 0.19 + PROPE2000;
			private double PROPE4000 = 0.11 + PROPE3000;

			MersenneTwister typeRandGen;

		// Method
			public Call.equipmentType uEquipType()
			{
				double randNum = typeRandGen.nextDouble();
				Call.equipmentType type;
				if (randNum >= 0 && randNum < PROPE1000) 
					type = Call.equipmentType.E1000; 
				else if (randNum >= PROPE1000 && randNum < PROPE2000)
					type = Call.equipmentType.E2000; 
				else if (randNum >= PROPE2000 && randNum < PROPE3000)
					type = Call.equipmentType.E3000; 
				else if (randNum >= PROPE3000 && randNum < PROPE4000)
					type = Call.equipmentType.E4000; 
				else
					type = Call.equipmentType.E4000; 
				return(type);
			}

	protected RVPs(OfficeRepair model, Seeds sd) 
	{ 
		this.model = model; 
		// Set up distribution functions
		interArrDist = new Exponential(1.0/WMEAN1,  
				                       new MersenneTwister(sd.arr));
	}
	
			
			
	/* Random Variate Procedure for Arrivals */
	private Exponential interArrDist;  // Exponential distribution for interarrival times
	private final double WMEAN1=10.0;
	protected double duInput()  // for getting next value of duInput
	{
	    double nxtInterArr;

        nxtInterArr = interArrDist.nextDouble();
	    // Note that interarrival time is added to current
	    // clock value to get the next arrival time.
	    return(nxtInterArr+model.getClock());
	}

	/** !! **/
	public double duCallArr() {
		// TODO Auto-generated method stub
		return 0;
	}

	/** !! **/
	public serviceType uServiceType() {
		// TODO Auto-generated method stub
		return null;
	}
}

