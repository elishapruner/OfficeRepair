package simModel;

import simModel.DVPs.Location;

public class UDPs {
	OfficeRepair model;  // for accessing the clock

	// Constructor
	protected UDPs(OfficeRepair model) { this.model = model; }

//	public double distance(Location origin, Location destination) {
//		 Location next = null;
//	        int callId;
//			Jobs call = this.model.rJobs[callId];
//	        if (origin == Location.Employee) {
//	            next = Location.Call;
//	          
//	        if (origin == Location.Temp1) {
//	            next = Location.Call;
//	        }
//	        return next;
//	}
//	}
}
	        
	// Translate User Defined Procedures into methods
    /*-------------------------------------------------
	                       Example
	    protected int ClerkReadyToCheckOut()
        {
        	int num = 0;
        	Clerk checker;
        	while(num < model.NumClerks)
        	{
        		checker = model.Clerks[num];
        		if((checker.currentstatus == Clerk.status.READYCHECKOUT)  && checker.list.size() != 0)
        		{return num;}
        		num +=1;
        	}
        	return -1;
        }
	------------------------------------------------------------*/


