package simModel;

import simulationModelling.*;

class Lunch extends ConditionalActivity 
{
	private Employee e;
	OfficeRepair model;  // for referencing the model
	
	public Lunch(OfficeRepair model) { 
		this.model = model; 
	}
	
	// GAComment: the precondition method must be static to allow testing before instantiating the object.
	protected boolean precondition(OfficeRepair simModel) {
		boolean returnValue = false;
		// GAComment: The following is not consistant with the CM.  Need to use UDP to reflect ABCmod paradigm.
		// Use UDPs as specified in the comments in the CM.
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees.get(i).size(); j++) {
				e = model.rEmployees.get(i).get(j);
				
				if (i == Constants.EMPLOYEE_T12) {
					if (e.status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
				    	returnValue = true;
				} else {
					if (e.status == Employee.StatusValues.READY_FOR_CALL && ((int)model.getClock())%1440 > 720 )   
				    	returnValue = true;
				}
			}
		}
	    
		return (returnValue);
	}

	public void startingEvent() {
		e.status = Employee.StatusValues.TAKING_LUNCH;
	}

	protected double duration() {
		return model.getClock() + 60;
	}

	protected void terminatingEvent() {
		e.status = Employee.StatusValues.READY_FOR_CALL;
		e.lunchTaken = true;
			
	}
	
	// Add UPDs specific to activity here
	// static int emptypeid_pre, empid_pre; // for passing to object after instantiation.
	// int emptypeid, empid; // object identifiers
	// static boolean udpCanStartLunch()
	// {  
	//    boolean found = false;
	//    for (int typeix ...; typeix < ... && !found ...)
	//        for (int idix ...
	//            if(model.rEmpoyee[typeix][idix].status == ....
	//            {
	//                emptypeid_pre = typeix;
	//                empid_pre = idix;
	//                found = true;
	//            }
	//    return(found);
	
	
	//   void udpGetEmpoyeeForLunch() { emptypeid = emptypeid_pre; empid = empid_pre; }

}
