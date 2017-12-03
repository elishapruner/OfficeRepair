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
		e.status = Employee.StatusValues.SERVICING_CALL;
	}

	protected double duration() {
		double serviceTime = 0;

		if (e.call.equipmentType == EquipmentTypes.E1000) {
			serviceTime = model.rvp.uServiceTime1000();
			System.out.println("Service time 1000: " + serviceTime);
		} else if (e.call.equipmentType == EquipmentTypes.E2000) {
			serviceTime = model.rvp.uServiceTime2000();
			System.out.println("Service time 2000: " + serviceTime);
		} else if (e.call.equipmentType == EquipmentTypes.E3000) {
			serviceTime = model.rvp.uServiceTime3000();
			System.out.println("Service time 3000: " + serviceTime);
		} else if (e.call.equipmentType == EquipmentTypes.E4000) {
			serviceTime = model.rvp.uServiceTime4000();
			System.out.println("Service time 4000: " + serviceTime);
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

		if ((e.call.equipmentType == EquipmentTypes.E3000) || (e.call.equipmentType == EquipmentTypes.E4000)) {
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

		e.status = Employee.StatusValues.READY_FOR_CALL;
	
		updateNumEmployees();
	}
	
	private void updateNumEmployees() {
		if (model.output.getSatisfactionLevelT34() < model.satisfactionLevel) {
			model.numEmployeesALL += 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_ALL).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_ALL).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
			System.out.println("Added 1 employee to EmployeeALL");
		} else if (model.output.getSatisfactionLevelT12() < model.satisfactionLevel) {
			model.numEmployeesT12 += 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).add(new Employee());
			
			int emp_id = model.rEmployees.get(Constants.EMPLOYEE_T12).size() - 1;
			model.rEmployees.get(Constants.EMPLOYEE_T12).get(emp_id).status = Employee.StatusValues.READY_FOR_CALL;
			System.out.println("Added 1 employee to EmployeeT12");
		}
	}

}
