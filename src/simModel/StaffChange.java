package simModel;

import simulationModelling.*;

public class StaffChange extends ScheduledAction {
	OfficeRepair model;  // reference to model object
	
	public StaffChange(OfficeRepair model) { 
		this.model = model; 
	}
	
	@Override
	protected double timeSequence() {
		// TODO Auto-generated method stub
		return 0;
	}

	// SchedEmp Event
	@Override
	protected void actionEvent()
	{
		if ((((int) model.getClock()) % 1440) % 960 == 0) {
			if (model.output.getSatisfactionLevelT12() < 0.85) {
				model.numEmployeesT12 += 1;
				System.out.println("Added 1 employee to EmployeeT12");
			}
			
			if (model.output.getSatisfactionLevelT34() < 0.85) {
				model.numEmployeesALL += 1;
				System.out.println("Added 1 employee to EmployeeALL");
			}
		}
	}

}
