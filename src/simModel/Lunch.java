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
		
		for (Employee e : model.rEmployees[Constants.EMPLOYEE_T12]) {
			if (e.Status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
		    	returnValue = true;
		}

		for (Employee e : model.rEmployees[Constants.EMPLOYEE_ALL]) {
			if (e.Status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
		    	returnValue = true;
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
