package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Recieved1000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Recieved1000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return model.rvp.DuCallArrival1000(); 
	}

	public void actionEvent() {
		Call icCall = new Call();
		
		icCall.equipmentType = EquipmentTypes.E1000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.E1000); 
		icCall.timeIn = model.getClock();

		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.qJobs[Constants.Job_1000_2000_B].spInsertJobQue(icCall);
		} else { 	
			model.qJobs[Constants.Job_1000_2000_P].spInsertJobQue(icCall);
		}
	}
}
