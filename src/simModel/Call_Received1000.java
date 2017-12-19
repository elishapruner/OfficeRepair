package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Received1000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Received1000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return  model.rvp.DuCallArrival1000();
	}

	public void actionEvent() {
		Call icCall = new Call();
		// GAComment: E1000 Should be TYPE1000
		icCall.equipmentType = EquipmentTypes.TYPE1000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.TYPE1000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs[Constants.Job_1000_2000_B].add(icCall);
		} else { 	
			model.qJobs[Constants.Job_1000_2000_P].add(icCall);
		}
	}
}
