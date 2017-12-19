package simModel;

import simModel.Call.EquipmentTypes;
import simModel.Call.ServiceTypes;
import simulationModelling.SequelActivity;

public class Service extends SequelActivity {

	OfficeRepair model; // for referencing the model
	Employee e;
	String empType;

	public Service (OfficeRepair model, Employee emp) {
		this.model = model;
		this.e = emp;
	}

	public void startingEvent() {
		e.status = Employee.StatusValues.SERVICING_CALL;
	}

	protected double duration() {
		return model.rvp.uServiceTime(e.icCall.equipmentType);
	}

	protected void terminatingEvent() {
		if ((e.icCall.equipmentType == EquipmentTypes.TYPE1000) || (e.icCall.equipmentType == EquipmentTypes.TYPE2000)) {
			model.output.totalNumberT12Contracts += 1;
			if (e.icCall.serviceType == ServiceTypes.PREMIUM) {
				if ((int) model.getClock() - (int) e.icCall.timeIn <= 180) {
					model.output.contractsT12satisfied += 1;
				}
			} else {
				if ((int) model.getClock() - (int) e.icCall.timeIn <= 1440) {
					model.output.contractsT12satisfied += 1;
				}
			}
		} 
		
		if ((e.icCall.equipmentType == EquipmentTypes.TYPE3000) || (e.icCall.equipmentType == EquipmentTypes.TYPE4000)) {
			model.output.totalNumberT34Contracts += 1;
			if (e.icCall.serviceType == ServiceTypes.PREMIUM) {
				if ((int) model.getClock() - (int) e.icCall.timeIn <= 180) {
					model.output.contractsT34satisfied += 1;
				}
			} else {
				if ((int) model.getClock() - (int) e.icCall.timeIn <= 1440) {
					model.output.contractsT34satisfied += 1;
				}
			}
		}

		if ((model.getClock() % 1440) > 570) {
			if (empType.equals("T12")) {
				model.output.overtimeCost += ((int) (model.getClock() % 1440) - 570) * Constants.EMP_T12_OVERTIME_WAGE;
			} else {
				model.output.overtimeCost += ((int) (model.getClock() % 1440) - 570) * Constants.EMP_ALL_OVERTIME_WAGE;
			}

		}

		e.status = Employee.StatusValues.READY_FOR_CALL;

	}
}
