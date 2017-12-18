package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Received2000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Received2000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return model.rvp.DuCallArrival2000();
	}

	public void actionEvent() {
		Call icCall = new Call();
		
		icCall.equipmentType = EquipmentTypes.TYPE2000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.TYPE2000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs.get(Constants.Job_1000_2000_B).add(icCall);
		} else { 	
			model.qJobs.get(Constants.Job_1000_2000_P).add(icCall);
		}
	}
}
