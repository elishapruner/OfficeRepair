package simModel;

import simulationModelling.ScheduledAction;

public class EndDay extends ScheduledAction {
	
	OfficeRepair model;

	public EndDay(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return (double) (960 + 1440*(Math.floor(((int) model.getClock()) / 1440))) + model.getClock();
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

	}
	
}

