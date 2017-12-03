package simModel;

import simulationModelling.ConditionalActivity;

public class Travel extends ConditionalActivity {
	OfficeRepair model; // for referencing the model
	Employee emp;
	String empType = "none";

	public Travel(OfficeRepair model) {
		this.model = model;
	}

	protected boolean precondition(OfficeRepair simModel) {
		boolean returnValue = false;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees.get(i).size(); j++) {
				Employee e = model.rEmployees.get(i).get(j);
				
				if (i == Constants.EMPLOYEE_T12) {
					if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs.get(Constants.Job_1000_2000_P).size() > 0
							|| model.qJobs.get(Constants.Job_1000_2000_B).size() > 0)) {
						returnValue = true;
						emp = e;
						this.empType = "T12";
						return (returnValue);
					}
				} else {
					if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs.get(Constants.Job_3000_4000_P).size() > 0
							|| model.qJobs.get(Constants.Job_3000_4000_B).size() > 0)) {
						returnValue = true;
						emp = e;
						this.empType = "ALL";
						return (returnValue);
					}
				}
			}
		}

		return (returnValue);
	}

	public void startingEvent() {
		if (empType == "T12") {
			if (model.qJobs.get(Constants.Job_1000_2000_P).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_1000_2000_P).remove(0);
			} else if (model.qJobs.get(Constants.Job_1000_2000_B).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_1000_2000_B).remove(0);
			}
		} else {// all
			if (model.qJobs.get(Constants.Job_3000_4000_P).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_3000_4000_P).remove(0);
			} else if (model.qJobs.get(Constants.Job_3000_4000_B).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_3000_4000_B).remove(0);
			}
			if (model.qJobs.get(Constants.Job_1000_2000_P).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_1000_2000_P).remove(0);
			} else if (model.qJobs.get(Constants.Job_1000_2000_B).size() > 0) {
				emp.call = model.qJobs.get(Constants.Job_1000_2000_B).remove(0);
			}
		}
		emp.Status = Employee.StatusValues.SERVICING_CALL;

	}

	protected double duration() {
		return (model.rvp.uTravelTime());
	}

	protected void terminatingEvent() {
		Service s = new Service(model, emp, empType);
		model.spStart(s);
	}
}
