package simModel;

import simulationModelling.ConditionalAction;

public class EndDay extends ConditionalAction {

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
		return 0;
	}

	public void actionEvent() {
		double numEmployeeT12 = (double) model.numEmployeesT12;
		double numEmployeeALL = (double) model.numEmployeesALL;

		for (Employee e : model.rEmployees[Constants.EMPLOYEE_T12]) {
			e.LunchTaken = false;
		}
		
		for (Employee e : model.rEmployees[Constants.EMPLOYEE_ALL]) {
			e.LunchTaken = false;
		}

		model.output.fixedTotalCost = (int) ((8.0 * numEmployeeT12 * Constants.EMP_T12_HOURLY_WAGE) + (8.0 * numEmployeeALL * Constants.EMP_ALL_HOURLY_WAGE) * (Math.floor(((int) model.getClock()) / 1440)));
		
		model.output.averageDailyCost = (model.output.fixedTotalCost + model.output.overtimeCost) / (Math.floor(((int) model.getClock()) / 1440));
	}
}
