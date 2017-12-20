package simModel;

import java.util.ArrayList;

import simulationModelling.ScheduledAction;

class Initialise extends ScheduledAction {
	OfficeRepair model;

	// Constructor
	protected Initialise(OfficeRepair model) {
		this.model = model;
	}

	double[] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0; // set index to first entry.

	protected double timeSequence() {
		return ts[tsix++]; // only invoked at t=0
	}

	@SuppressWarnings("unchecked")
	protected void actionEvent() {
		// Initial SSOVs to 0
		model.output.contractsT12satisfied = 0;
		model.output.contractsT34satisfied = 0;
		model.output.totalNumberT12Contracts = 0;
		model.output.totalNumberT34Contracts = 0;
		//model.output.fixedTotalCost = 0.0;
		model.output.overtimeCost = 0.0;
		//model.output.averageDailyCost = 0.0; // this SSOV is a double


		// Initialize rEmployees
		model.rEmployees = new Employee[2][];
		model.rEmployees[Constants.EMPLOYEE_T12] = new Employee[model.numEmployeesT12]; 
		model.rEmployees[Constants.EMPLOYEE_ALL] = new Employee[model.numEmployeesALL]; 
		
		for (int emp_id = 0; emp_id < model.numEmployeesT12; emp_id++) {
			model.rEmployees[Constants.EMPLOYEE_T12][emp_id] = new Employee();
			model.rEmployees[Constants.EMPLOYEE_T12][emp_id].status = Employee.StatusValues.READY_FOR_CALL;
		}
		for (int emp_id = 0; emp_id < model.numEmployeesALL; emp_id++) {
			model.rEmployees[Constants.EMPLOYEE_ALL][emp_id] = new Employee();
			model.rEmployees[Constants.EMPLOYEE_ALL][emp_id].status = Employee.StatusValues.READY_FOR_CALL;
		}

		
		// Initialize qJobs
		model.qJobs = new ArrayList[4];
		for (int i = 0; i < 4; i++) {
			model.qJobs[i] = new ArrayList<Call>();
		}
	}

}
