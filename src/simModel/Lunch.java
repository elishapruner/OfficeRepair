package simModel;

import simulationModelling.*;

class Lunch extends ConditionalActivity 
{
	private Employee e;
	OfficeRepair model;  // for referencing the model
	
	public Lunch(OfficeRepair model) { 
		this.model = model; 
	}
	
	protected boolean precondition(OfficeRepair simModel) {
		boolean returnValue = false;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees.get(i).size(); j++) {
				Employee e = model.rEmployees.get(i).get(j);
				
				if (i == Constants.EMPLOYEE_T12) {
					if (e.Status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
				    	returnValue = true;
				} else {
					if (e.Status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
				    	returnValue = true;
				}
			}
		}
	    
		return (returnValue);
	}

	public void startingEvent() {
		e.Status = Employee.StatusValues.TAKING_LUNCH;
	}

	protected double duration() {
		return (model.getClock() + 60);
	}

	protected void terminatingEvent() {
		e.Status = Employee.StatusValues.READY_FOR_CALL;
		e.LunchTaken = true;
			
	}

}
