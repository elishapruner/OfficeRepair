package simModel;

import simulationModelling.ScheduledAction;

public class UpdateNumEmployees extends ScheduledAction {
	
	OfficeRepair model; 
	
	public UpdateNumEmployees (OfficeRepair model) {
		this.model = model;
	}

	@Override
	protected double timeSequence() {

		return model.getClock() + 240;
		// We submitted "+ 240" , however on the github "+ 60" was currently stored ,
		// I resolved the conflcit by keeping +240 


	}

	@Override
	protected void actionEvent() {
		if (model.output.getSatisfactionLevelT34() < model.satisfactionLevel) {
			model.numEmployeesALL += 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_ALL).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
//			System.out.println("Added 1 employee to EmployeeALL");
		} else if (model.output.getSatisfactionLevelT12() < model.satisfactionLevel) {
			model.numEmployeesT12 += 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_T12).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
//			System.out.println("Added 1 employee to EmployeeT12");
		}
		
	}



}
