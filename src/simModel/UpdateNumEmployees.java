package simModel;

import simulationModelling.ScheduledAction;

public class UpdateNumEmployees extends ScheduledAction {
	
	OfficeRepair model; 
	
	public UpdateNumEmployees (OfficeRepair model) {
		this.model = model;
	}

	@Override
	protected double timeSequence() {
<<<<<<< HEAD
		return model.getClock() + 240;
=======
		return model.getClock() + 60;
>>>>>>> 5ffd60256a4a159a054cfd7545a7082f03bcb90b
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
