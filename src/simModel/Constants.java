package simModel;

public class Constants {
/** Constants **/
	
	/*Hourly wage paid to EMP_T12 employees*/
	protected final static double EMP_T12_HOURLY_WAGE = 26;
	
	/*Hourly wage paid to EMP_ALL employees*/
	protected final static double EMP_ALL_HOURLY_WAGE = 41;
	
	/*Overtime wage to EMP_T12*/
	protected final static double EMP_T12_OVERTIME_WAGE = 45.5;
	
	/*Overtime wage to EMP_ALL*/
	protected final static double EMP_ALL_OVERTIME_WAGE = 71.75;
	
	//Employee types
	protected final static int Employee_T12 = 0;
	protected final static int Employee_ALL = 1;
	
	//Call status
	protected final static int InProcess = 1;
	protected final static int IsDone = 0;
	
	//Indices for types of job in JobQueue
	protected final static int Job_1000_2000_P = 0 ; 
	protected final static int Job_1000_2000_B = 1 ; 
	protected final static int Job_3000_4000_P = 2 ; 
	protected final static int Job_3000_4000_B = 3 ; 
	
	
}
