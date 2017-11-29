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
	     icJobs.spInsertQue(icCall);
	     
	     
	}
}	
