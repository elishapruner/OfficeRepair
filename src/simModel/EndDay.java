package simModel;

import simulationModelling.ScheduledAction;

public class EndDay extends ScheduledAction {
	
	OfficeRepair model;

	public EndDay(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() { // GAComment: Please integrate into a DVP.
		return (double) (960 + 1440*(Math.floor(((int) model.getClock()) / 1440)));
	}

	@Override
	protected void actionEvent() {
		// GAComment: the following does not reflect the 2D nature of the R.Employee defined in the CM
		double numEmployeeT12 = (double) model.numEmployeesT12;
		double numEmployeeALL = (double) model.numEmployeesALL;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees.get(i).size(); j++) {
				Employee e = model.rEmployees.get(i).get(j);
				e.lunchTaken = false;
			}
		}
        // GAComment:  Why should fixed costs by integer values?
		//             See comments in CM, the following computation is not required and the averageDailyCost can be 
		//             computed at the end of the run in the Output class.
		model.output.fixedTotalCost = (int) ((8.0 * numEmployeeT12 * Constants.EMP_T12_HOURLY_WAGE) + (8.0 * numEmployeeALL * Constants.EMP_ALL_HOURLY_WAGE) * (Math.floor(((int) model.getClock()) / 1440)));
		
		model.output.averageDailyCost = (model.output.fixedTotalCost + model.output.overtimeCost) / (Math.floor(((int) model.getClock()) / 1440));

	}
	
}

