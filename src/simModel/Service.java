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
		// GAComment: the ABCmod paradigm used identifiers for SET entities.  Using object references
		//            does not reflect the model.  Should be passing two ids, one for employee type and the other
		//            for employee id.
		this.e = emp;
		this.empType = empType;
	}

	public void startingEvent() {
		e.status = Employee.StatusValues.SERVICING_CALL;
	}

	protected double duration() {
		double serviceTime = 0;
        // GAComment:  Parameterize the the RVP.  
		// Should be model.rvp.uServiceTime(icCall.equipmentType)
		if (e.call.equipmentType == EquipmentTypes.TYPE1000) {
			serviceTime = model.rvp.uServiceTime1000();
		} else if (e.call.equipmentType == EquipmentTypes.TYPE2000) {
			serviceTime = model.rvp.uServiceTime2000();
		} else if (e.call.equipmentType == EquipmentTypes.TYPE3000) {
			serviceTime = model.rvp.uServiceTime3000();
		} else if (e.call.equipmentType == EquipmentTypes.TYPE4000) {
			serviceTime = model.rvp.uServiceTime4000();
		}

		return (serviceTime);
	}

	protected void terminatingEvent() {
		if ((e.call.equipmentType == EquipmentTypes.TYPE1000) || (e.call.equipmentType == EquipmentTypes.TYPE2000)) {
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
		
		if ((e.call.equipmentType == EquipmentTypes.TYPE3000) || (e.call.equipmentType == EquipmentTypes.TYPE4000)) {
			model.output.totalNumberT34Contracts += 1;
			if (e.call.serviceType == ServiceTypes.PREMIUM) {
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

		e.status = Employee.StatusValues.READY_FOR_CALL;

	}
}
