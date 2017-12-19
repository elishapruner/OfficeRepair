package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Received3000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Received3000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return model.rvp.DuCallArrival3000();
	}

	public void actionEvent() {
		Call icCall = new Call();
		
		icCall.equipmentType = EquipmentTypes.TYPE3000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.TYPE3000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs[Constants.Job_3000_4000_B].add(icCall);
		} else { 	
			model.qJobs[Constants.Job_3000_4000_P].add(icCall);
		}
	}
}
