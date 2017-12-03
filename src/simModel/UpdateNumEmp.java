package simModel;

import simulationModelling.*;

public class UpdateNumEmp extends ScheduledAction {
	OfficeRepair model;  // reference to model object
	
	public UpdateNumEmp(OfficeRepair model) { 
		this.model = model; 
	}
	
	@Override
	public double timeSequence() { 
		return model.getClock() + 1; 
	}

	// SchedEmp Event
	@Override
	protected void actionEvent()
	{
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
