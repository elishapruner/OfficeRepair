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
		
		// Initial SSOVs to 0 
		model.output.contractsT12satisfied = 0 ;
		model.output.contractsT34satisfied = 0 ; 
		model.output.totalNumberT12Contracts = 0 ; 
		model.output.totalNumberT34Contracts = 0 ; 	
		model.output.fixedTotalCost = 0 ;
		model.output.overtimeCost = 0 ; 
		model.output.averageDailyCost = 0.0 ; // this SSOV is a double 
		
		
		model.rEmployees[employeetype][employeeid] = new Employee();
		model.rEmployees[employeetype][employeeid].Status = Employee.StatusValues.READY_FOR_CALL;
		
		// System initialization
                // Add initialization instructions 
	}
	

}
