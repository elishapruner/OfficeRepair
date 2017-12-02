package simModel;

import simModel.Call.EquipmentTypes;
import simModel.Call.ServiceTypes;
import simulationModelling.SequelActivity;

public class Service extends SequelActivity {

	OfficeRepair model; // for referencing the model
	Employee e;

	public Service(OfficeRepair model, Employee emp) {
		this.model = model;
		this.e = emp;
	}

	public void startingEvent() {
		e.Status = Employee.StatusValues.SERVICING_CALL;
	}

	protected double duration() {
		double serviceTime = 0;
		
		if (e.call.equipmentType.equals((EquipmentTypes.E1000))) {
			serviceTime = model.rvp.uServiceTime1000();
		} else if (e.call.equipmentType.equals((EquipmentTypes.E2000))) {
			serviceTime = model.rvp.uServiceTime2000();
		} else if (e.call.equipmentType.equals((EquipmentTypes.E3000))) {
			serviceTime = model.rvp.uServiceTime3000();
		} else if (e.call.equipmentType.equals((EquipmentTypes.E4000))) {
			serviceTime = model.rvp.uServiceTime4000();
		}
		
		return (serviceTime);
	}

	protected void terminatingEvent() {
		if (e.call.serviceType.equals(ServiceTypes.PREMIUM)) {
			if ((int) model.getClock() - (int) e.call.timeIn <= 180) {
				model.output.contractsT12satisfied += 1;
			}
		} else {
			if ((int) model.getClock() - (int) e.call.timeIn <= 1440) {
				model.output.contractsT12satisfied += 1;
			}
		}
		e.Status = Employee.StatusValues.READY_FOR_CALL;

	}

}
