package simModel;

import simModel.Call.EquipmentTypes;
import simulationModelling.ScheduledAction;

class Call_Recieved4000 extends ScheduledAction {
	OfficeRepair model;

	public Call_Recieved4000(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return model.rvp.DuCallArrival4000(); 
	}

	public void actionEvent() {
		// WArrival Action Sequence SCS
		Call icCall = new Call();
		
		icCall.equipmentType = EquipmentTypes.E4000;
		icCall.serviceType = model.rvp.uServiceType(EquipmentTypes.E4000); // uServiceType needs to be
												
		//icCall.equipmentType = model.rvp.uEquipType();
		icCall.timeIn = model.getClock();
		// Jobs icJobs = new Job();

		// if icCall is of EquipmentType E1000 or E2000 and its of ServiceType
		// Basic then place in the queue Job_1000_2000_B else place in the queue
		// Job_1000_2000_P
		if (icCall.serviceType == Call.ServiceTypes.BASIC){
			model.jobs[Constants.Job_3000_4000_B].spInsertJobQue(icCall);
		} else { 	
			model.jobs[Constants.Job_3000_4000_P].spInsertJobQue(icCall);
			
		}
	}
}
