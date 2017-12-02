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
		
		// Initial SSOVs to 0 
		model.output.contractsT12satisfied = 0 ;
		model.output.contractsT34satisfied = 0 ; 
		model.output.totalNumberT12Contracts = 0 ; 
		model.output.totalNumberT34Contracts = 0 ; 	
		model.output.fixedTotalCost = 0 ;
		model.output.overtimeCost = 0 ; 
		model.output.averageDailyCost = 0.0 ; // this SSOV is a double 
		
		for(int emp_id = 0;emp_id < model.numEmployeesT12; emp_id++){
			model.rEmployees[Constants.Employee_T12][emp_id] = new Employee() ; 
			model.rEmployees[Constants.Employee_T12][emp_id].Status = Employee.StatusValues.READY_FOR_CALL;
			
		}	
		for(int emp_id = 0;emp_id < model.numEmployeesALL; emp_id++){
			model.rEmployees[Constants.Employee_ALL][emp_id] = new Employee() ; 
			model.rEmployees[Constants.Employee_ALL][emp_id].Status = Employee.StatusValues.READY_FOR_CALL;	
		}	
		
		model.jobs[Constants.Job_1000_2000_P] = new JobQueue() ;
		model.jobs[Constants.Job_1000_2000_B] = new JobQueue() ;
		model.jobs[Constants.Job_1000_2000_P] = new JobQueue() ;
		model.jobs[Constants.Job_1000_2000_B] = new JobQueue() ;
		
		
		}
		// System initialization
                // Add initialization instructions 
}
	


