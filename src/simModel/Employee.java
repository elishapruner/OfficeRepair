package simModel;

public class Employee {

	// Number of employees - this attribute is an input variable
	// GAComment: the following is neither specified in the Entity structur, nor as an input in the CM.
	int NumEmployees;
	
	//Represents the current status of the employee.
	public static enum StatusValues {READY_FOR_CALL, TAKING_LUNCH, SERVICING_CALL};
	public StatusValues status;
	
	//Whether or not the employee has taken lunch that day
	boolean lunchTaken;
	public Call call;  // GAComment: not consisted with CM, attribute specified as call_Servicing
}
