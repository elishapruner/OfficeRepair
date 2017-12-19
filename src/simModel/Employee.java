package simModel;

public class Employee {
	
	// Represents the current status of the employee.
	public static enum StatusValues {
		READY_FOR_CALL, TAKING_LUNCH, SERVICING_CALL
	};
	
	// The status of the employee
	public StatusValues status;
	
	// Whether or not the employee has taken lunch that day
	boolean lunchTaken;
}
