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

	protected void actionEvent() {
		// Initial SSOVs to 0
		model.output.contractsT12satisfied = 0;
		model.output.contractsT34satisfied = 0;
		model.output.totalNumberT12Contracts = 0;
		model.output.totalNumberT34Contracts = 0;
		model.output.fixedTotalCost = 0;
		model.output.overtimeCost = 0;
		model.output.averageDailyCost = 0.0; // this SSOV is a double

		// GAComment: The following does not reflect the 2D nature of the SEt of employees as specified in the CM
		// Arrays of objects better reflects SET. See the comments in OfficeRepair class.
		ArrayList<Employee> rEmployee_T12 = new ArrayList<Employee>();
		ArrayList<Employee> rEmployee_ALL = new ArrayList<Employee>();		
		model.rEmployees.add(rEmployee_T12);
		model.rEmployees.add(rEmployee_ALL);
		
		for (int emp_id = 0; emp_id < model.numEmployeesT12; emp_id++) {
			model.rEmployees.get(Constants.EMPLOYEE_T12).add(new Employee());
			model.rEmployees.get(Constants.EMPLOYEE_T12).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;

		}
		for (int emp_id = 0; emp_id < model.numEmployeesALL; emp_id++) {
			model.rEmployees.get(Constants.EMPLOYEE_ALL).add(new Employee());
			model.rEmployees.get(Constants.EMPLOYEE_ALL).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
		}
		
		// GAComment: See comments in OfficeRepair class
		// Can use ArrayList[Constants.Job_1000_2000_P] = new ArrayList<Call>();
		// Much better reflection of the SET concept of the CM.
		ArrayList<Call> qJob_1000_2000_P = new ArrayList<Call>();
		ArrayList<Call> qJob_1000_2000_B = new ArrayList<Call>();
		ArrayList<Call> qJob_3000_4000_P = new ArrayList<Call>();
		ArrayList<Call> qJob_3000_4000_B = new ArrayList<Call>();
		
		model.qJobs.add(qJob_1000_2000_P);
		model.qJobs.add(qJob_1000_2000_B);
		model.qJobs.add(qJob_3000_4000_P);
		model.qJobs.add(qJob_3000_4000_B);
	}

}
