package simModel;

import simulationModelling.ScheduledAction;

class Call_Recieved extends ScheduledAction {
	OfficeRepair model;

	public Call_Recieved(OfficeRepair model) {
		this.model = model;
	}

	public double timeSequence() {
		return model.rvp.duCallArr(); // duCallarr needs to be created in the
										// RVP class
	}

	public void actionEvent() {
		// WArrival Action Sequence SCS
		Call icCall = new Call();
		
	//icCall.serviceType = model.rvp.uServiceType(); // uServiceType needs to be
													// created in the RVP class
		//icCall.equipmentType = model.rvp.uEquipType();
		icCall.timeIn = model.getClock();
		// Jobs icJobs = new Job();

		// if icCall is of EquipmentType E1000 or E2000 and its of ServiceType
		// Basic then place in the queue Job_1000_2000_B else place in the queue
		// Job_1000_2000_P
		
		if ( icCall.equipmentType == Call.EquipmentTypes.E1000 || icCall.equipmentType == Call.EquipmentTypes.E2000) {
			if (icCall.serviceType == Call.ServiceTypes.BASIC){
				//model.jobs[Constants.Job_1000_2000_B].spInsertJobQue(icCall);
				model.jobs[Constants.Job_1000_2000_B].spInsertJobQue(icCall);
			}
		}else { 	
			model.jobs[Constants.Job_1000_2000_P].spInsertJobQue(icCall);
			
		}

		// if icCall is of EquipmentType E3000 or E4000 and its of ServiceType
		// Basic then place in the queue Job_3000_42000_B else place in the
		// queue Job_3000_4000_P
		if (icCall.equipmentType == Call.EquipmentTypes.E3000 || icCall.equipmentType == Call.EquipmentTypes.E4000) {
			if (icCall.serviceType == Call.ServiceTypes.BASIC){
				model.jobs[Constants.Job_3000_4000_B].spInsertJobQue(icCall);
				
			}
		} else
			model.jobs[Constants.Job_3000_4000_P].spInsertJobQue(icCall);
			

	}
}
