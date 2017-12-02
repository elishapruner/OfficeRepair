package simModel;

import simModel.Call.EquipmentTypes;
import simModel.Call.ServiceTypes;
import simulationModelling.SequelActivity;

public class Service extends SequelActivity {

	OfficeRepair model; // for referencing the model
	Employee e;
	String empType;

	public Service (OfficeRepair model, Employee emp, String empType) {
		this.model = model;
		this.e = emp;
		this.empType = empType;
	}

	public void startingEvent() {
		e.Status = Employee.StatusValues.SERVICING_CALL;
	}

	protected double duration() {
		double serviceTime = 0;

		if (e.call.equipmentType == EquipmentTypes.E1000) {
			serviceTime = model.rvp.uServiceTime1000();
		} else if (e.call.equipmentType == EquipmentTypes.E2000) {
			serviceTime = model.rvp.uServiceTime2000();
		} else if (e.call.equipmentType == EquipmentTypes.E3000) {
			serviceTime = model.rvp.uServiceTime3000();
		} else if (e.call.equipmentType == EquipmentTypes.E4000) {
			serviceTime = model.rvp.uServiceTime4000();
		}

		return (serviceTime);
	}

	protected void terminatingEvent() {
		if ((e.call.equipmentType == EquipmentTypes.E1000) || (e.call.equipmentType == EquipmentTypes.E2000)) {
			model.output.totalNumberT12Contracts += 1;
			if (e.call.serviceType == ServiceTypes.PREMIUM) {
				if ((int) model.getClock() - (int) e.call.timeIn <= 180) {
					model.output.contractsT12satisfied += 1;
				}
			} else {
				if ((int) model.getClock() - (int) e.call.timeIn <= 1440) {
					model.output.contractsT12satisfied += 1;
				}
			}
		}

		if ((e.call.equipmentType == EquipmentTypes.E3000)|| (e.call.equipmentType == EquipmentTypes.E4000)) {
			model.output.totalNumberT34Contracts += 1;
			if (e.call.serviceType.equals(ServiceTypes.PREMIUM)) {
				if ((int) model.getClock() - (int) e.call.timeIn <= 180) {
					model.output.contractsT34satisfied += 1;
				}
			} else {
				if ((int) model.getClock() - (int) e.call.timeIn <= 1440) {
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

		e.Status = Employee.StatusValues.READY_FOR_CALL;

	}

}
