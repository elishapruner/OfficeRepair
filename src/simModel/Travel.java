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

		for (Employee e : model.rEmployees[Constants.EMPLOYEE_T12]) {
			if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_1000_2000_P].size() > 0
					|| model.qJobs[Constants.Job_1000_2000_B].size() > 0)) {
				returnValue = true;
				emp = e;
				this.empType = "T12";
				return (returnValue);
			}
		}

		for (Employee e : model.rEmployees[Constants.EMPLOYEE_ALL]) {
			if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_3000_4000_P].size() > 0
					|| model.qJobs[Constants.Job_3000_4000_B].size() > 0)) {
				returnValue = true;
				emp = e;
				this.empType = "ALL";
				return (returnValue);
			}
		}

		return (returnValue);
	}

	public void startingEvent() {
		if (empType == "T12") {
			if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
				emp.call = model.qJobs[Constants.Job_1000_2000_P].spRemoveQue();
			} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
				emp.call = model.qJobs[Constants.Job_1000_2000_B].spRemoveQue();
			}
		} else {// all
			if (model.qJobs[Constants.Job_3000_4000_P].size() > 0) {
				emp.call = model.qJobs[Constants.Job_3000_4000_P].spRemoveQue();
			} else if (model.qJobs[Constants.Job_3000_4000_B].size() > 0) {
				emp.call = model.qJobs[Constants.Job_3000_4000_B].spRemoveQue();
			}
			if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
				emp.call = model.qJobs[Constants.Job_1000_2000_P].spRemoveQue();
			} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
				emp.call = model.qJobs[Constants.Job_1000_2000_B].spRemoveQue();
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
