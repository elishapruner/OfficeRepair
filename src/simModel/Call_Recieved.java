package simModel;

import simulationModelling.ScheduledAction;

class Call_Recieved extends ScheduledAction 
{
	OfficeRepair model;
	public Call_Recieved (OfficeRepair model) {this.model=model;}

	public double timeSequence()
	{
		return model.rvp.duCallArr();				// duCallarr needs to be created in the RVP class 
	}

	public void actionEvent() 
	{
		// WArrival Action Sequence SCS
	     Call icCall = new Call();
	     icCall.uType2 = model.rvp.uServiceType();	// uServiceType needs to be created in the RVP class 
	     icCall.uType1 = model.rvp.uEquipType();
	     icCall.timeIn = model.getClock(); 
	     Jobs icJobs = new Jobs();
	     
	     // if icCall is of EquipmentType E1000 or E2000 and its of ServiceType Basic then place in the queue Job_1000_2000_B else place in the queue Job_1000_2000_P
	     if (icCall.uType1 == icCall.uType1.E1000 || icCall.uType1 == icCall.uType1.E2000)
	     	{
	    	 	if (icCall.uType2 == icCall.uType2.BASIC)
	    	 			icJobs.spInsertQueqJob_1000_2000_B(icCall);
	    	 	}
	     else icJobs.spInsertQueqJob_1000_2000_P(icCall);
	     
	     // if icCall is of EquipmentType E3000 or E4000 and its of ServiceType Basic then place in the queue Job_3000_42000_B else place in the queue Job_3000_4000_P
	     if (icCall.uType1 == icCall.uType1.E3000 || icCall.uType1 == icCall.uType1.E4000)
	     	{
	    	 	if (icCall.uType2 == icCall.uType2.BASIC)
	    	 			icJobs.spInsertQueqJob_3000_4000_B(icCall);
	    	 	}
	     else icJobs.spInsertQueqJob_3000_4000_P(icCall);
	     
	     
	     
	}
}	
