package simModel;

import simulationModelling.ScheduledAction;

public class EndDay extends ScheduledAction {
	
	OfficeRepair model;

	public EndDay(OfficeRepair model) {
		this.model = model;
	}

	protected boolean precondition(OfficeRepair simModel) {
		boolean returnValue = false;

		if ((((int) model.getClock()) % 1440) % 960 == 0)
			returnValue = true;

		return (returnValue);
	}

	public double timeSequence() {
		return model.getClock() + 1; 
	}

	@Override
	protected void actionEvent() {
		double numEmployeeT12 = (double) model.numEmployeesT12;
		double numEmployeeALL = (double) model.numEmployeesALL;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees.get(i).size(); j++) {
				Employee e = model.rEmployees.get(i).get(j);
				e.lunchTaken = false;
			}
		}

		model.output.fixedTotalCost = (int) ((8.0 * numEmployeeT12 * Constants.EMP_T12_HOURLY_WAGE) + (8.0 * numEmployeeALL * Constants.EMP_ALL_HOURLY_WAGE) * (Math.floor(((int) model.getClock()) / 1440)));
		
		model.output.averageDailyCost = (model.output.fixedTotalCost + model.output.overtimeCost) / (Math.floor(((int) model.getClock()) / 1440));
		
//		updateNumEmployees();

	}
	
	private void updateNumEmployees() {
		if (model.output.getSatisfactionLevelT12() < 0.85) {
			model.numEmployeesT12 += 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_T12).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
			System.out.println("Added 1 employee to EmployeeT12");
		} else {
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_T12).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).remove(emp_id);
		}
		
		if (model.output.getSatisfactionLevelT34() < 0.85) {
			model.numEmployeesALL += 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_ALL).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
			System.out.println("Added 1 employee to EmployeeALL");
		} else {
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_ALL).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).remove(emp_id);
		}
	}
	
}

