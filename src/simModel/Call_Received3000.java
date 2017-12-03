package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Received3000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Received3000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		double time = model.rvp.DuCallArrival3000(); 
		System.out.println("Call 3000: " + time);
		return time;
	}

	public void actionEvent() {
		Call icCall = new Call();
		
		icCall.equipmentType = EquipmentTypes.E3000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.E3000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs.get(Constants.Job_3000_4000_B).add(icCall);
		} else { 	
			model.qJobs.get(Constants.Job_3000_4000_P).add(icCall);
		}
	}
}
