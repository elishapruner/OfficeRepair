package simModel;

import simulationModelling.ScheduledAction;

public class EndDay extends ScheduledAction {
	
	OfficeRepair model;
	
	public EndDay(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() { // GAComment: Please integrate into a DVP. 
		return model.dvp.EndDay() ; 
	}

	@Override
	protected void actionEvent() {
		// GAComment: the following does not reflect the 2D nature of the R.Employee defined in the CM
//		System.out.println("get clock from end day value ActionEvent " + ((int) model.getClock()));
//		int dayNumber = (int) model.getClock() / 1440  ;
//		
//		System.out.println("END of DAY --- " + dayNumber );
//		
		
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees[i].length; j++) {
				Employee e = model.rEmployees[i][j];
				e.lunchTaken = false;
			}
		}
        // TODO: GAComment:  Why should fixed costs by integer values?
		//             See comments in CM, the following computation is not required and the averageDailyCost can be 
		//             computed at the end of the run in the Output class.


	}
	
}

