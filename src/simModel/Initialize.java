package simModel;

import simulationModelling.ScheduledAction;

class Initialise extends ScheduledAction
{
	OfficeRepair model;
	
	// Constructor
	protected Initialise(OfficeRepair model) { this.model = model; }

	double [] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0;  // set index to first entry.
	protected double timeSequence() 
	{
		return ts[tsix++];  // only invoked at t=0
	}

	protected void actionEvent() 
	{
		int employeetype;
		for(employeetype = Constants.Employee_T12 ; employeetype <= Constants.Employee_ALL; employeetype++);
		int employeeid;
		for(employeeid = 0;employeeid < 50; employeeid++);
		
		model.rEmployees[employeetype][employeeid] = new Employee();
		model.rEmployees[employeetype][employeeid].Status = Employee.StatusValues.READY_FOR_CALL;
		
		// System initialization
                // Add initialization instructions 
	}
	

}
