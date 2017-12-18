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
		// GAComment: E1000 Shoud be TYPE1000
		icCall.equipmentType = EquipmentTypes.E1000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.E1000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs.get(Constants.Job_1000_2000_B).add(icCall);
		} else { 	
			model.qJobs.get(Constants.Job_1000_2000_P).add(icCall);
		}
	}
}
