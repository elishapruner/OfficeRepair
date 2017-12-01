package simModel;

import javax.net.ssl.SSLEngineResult.Status;

import simulationModelling.*;

class Lunch extends ConditionalActivity 
{
	private Call icCall;
	OfficeRepair model;  // for referencing the model
	
	public Lunch(OfficeRepair model) { this.model = model; }
	
	protected static boolean precondition(OfficeRepair simModel)
	{
		boolean returnValue = false;
		
		
	    if (simModel.rEmployees[0][1].Status == Employee.StatusValues.READY_FOR_CALL && ((int)simModel.getClock())%1440 > 210 )   //	rEmployees[0][1] this needs to be fixed
	    	returnValue = true;
	    
		return(returnValue);
	}

	public void startingEvent() 
	{
		model.rEmployees[0][1].Status = Employee.StatusValues.TAKING_LUNCH;	//	rEmployees[0][1] this needs to be fixed
	}

	protected double duration() 
	{
		return (model.getClock() + 60);
	}

	protected void terminatingEvent() 
	{
		model.rEmployees[0][1].Status = Employee.StatusValues.READY_FOR_CALL;		//	rEmployees[0][1] this needs to be fixed
		model.rEmployees[0][1].LunchTaken = true;								//	rEmployees[0][1] this needs to be fixed
			
	}

	
}
