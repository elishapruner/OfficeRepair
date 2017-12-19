package simModel;

import simulationModelling.ConditionalActivity;

public class Travel extends ConditionalActivity {
	OfficeRepair model; // for referencing the model
	Employee emp;  // Need to use identifiers to find employee and there are 2 - one for type and other for id.
	String empType = "none";

	public Travel(OfficeRepair model) {
		this.model = model;
	}

	protected static boolean precondition(OfficeRepair simModel) {
		boolean returnValue = false;
		// TODO: GAComment: Does not reflec the CM.  Rule of thumb.  The SM must reflect the ABCmod paradigm.
		// Be sure to provide a proper specification of the CM and then translate to SM.
		// See comments on the Lunch Activity and adjust accordingly.
		
//		for (int i = 0; i < 2; i++) {
//			for (int j = 0; j < model.rEmployees[i].length; j++) {
//				Employee e = model.rEmployees[i][j];
//				
//				if (i == Constants.EMPLOYEE_T12) {
//					if (e.status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_1000_2000_P].size() > 0 || model.qJobs[Constants.Job_1000_2000_B].size() > 0)) {
//						returnValue = true;
//						emp = e;
//						this.empType = "T12";
//						return (returnValue);
//					}
//				} else {
//					if (e.status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_3000_4000_P].size() > 0
//							|| model.qJobs[Constants.Job_3000_4000_B].size() > 0)) {
//						returnValue = true;
//						emp = e;
//						this.empType = "ALL";
//						return (returnValue);
//					}
//				}
//			}
//		}

		return (returnValue);
	}

	public void startingEvent() {
		// GAComment: does not reflect CM.
		if (empType == "T12") {
			if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_1000_2000_P].remove(0);
			} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_1000_2000_B].remove(0);
			}
		} else {// all
			if (model.qJobs[Constants.Job_3000_4000_P].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_3000_4000_P].remove(0);
			} else if (model.qJobs[Constants.Job_3000_4000_B].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_3000_4000_B].remove(0);
			} else if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_1000_2000_P].remove(0);
			} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
				emp.icCall = model.qJobs[Constants.Job_1000_2000_B].remove(0);
			}
		}
		emp.status = Employee.StatusValues.SERVICING_CALL;

	}

	protected double duration() {
		return model.rvp.uTravelTime();
	}

	protected void terminatingEvent() {
		// TODO: GAcomment:  CM does not reflect passing of 
		Service s = new Service(model, emp);
		model.spStart(s);
	}
}
