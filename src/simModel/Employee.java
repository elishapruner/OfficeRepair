package simModel;

public class Employee {

	// Number of employees - this attribute is an input variable
	int NumEmployees;
	
	//Represents the current status of the employee.
	public static enum StatusValues {READY_FOR_CALL, TAKING_LUNCH, SERVICING_CALL};
	public StatusValues Status;
	
	//Whether or not the employee has taken lunch that day
	boolean LunchTaken;
	
}
