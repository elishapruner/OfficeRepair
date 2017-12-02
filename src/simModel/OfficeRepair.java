package simModel;

import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class OfficeRepair extends AOSimulationModel {
	// Constants available from Constants class
	Constants constants = new Constants();
	/* Parameter */
	// Define the parameters
	int numEmployeesT12;
	int numEmployeesALL;

	/*-------------Entity Data Structures-------------------*/
	// Array of Employees with two indices : emp_type and emp_id
	protected Employee[][] rEmployees;
	protected JobQueue[] qJobs = new JobQueue[4];

	// References to RVP and DVP objects
	protected RVPs rvp; // Reference to rvp object - object created in constructor

	// Output object
	protected Output output = new Output(this);

	// Output values - define the public methods that return values required for
	// experimentation.
	public double getSatisfactionLevelT12() {
		return output.getSatisfactionLevelT12();
	}

	public double getSatisfactionLevelT34() {
		return output.getSatisfactionLevelT34();
	}

	public double getSatisfactionLevelAll() {
		return output.getSatisfactionLevelAll();
	}

	public double getAverageDailyCost() {
		return output.averageDailyCost;
	}

	// Constructor
	public OfficeRepair(double t0time, Seeds sd, boolean traceFlag, int addEmployeesT12, int addEmployeesALL) {
		// Turn trancing on if traceFlag is true
		this.traceFlag = traceFlag;

		// Create RVP object with given seed
		rvp = new RVPs(this, sd);
		this.numEmployeesT12 = addEmployeesT12;
		this.numEmployeesALL = addEmployeesALL;

		rEmployees = new Employee[2][];
		rEmployees[Constants.EMPLOYEE_T12] = new Employee[numEmployeesT12];
		rEmployees[Constants.EMPLOYEE_ALL] = new Employee[numEmployeesALL];

		// initialize the simulation model
		initAOSimulModel(t0time);

		// Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init); // Should always be first one scheduled.

		Call_Recieved1000 callReceived1000 = new Call_Recieved1000(this);
		Call_Recieved2000 callReceived2000 = new Call_Recieved2000(this);
		Call_Recieved3000 callReceived3000 = new Call_Recieved3000(this);
		Call_Recieved4000 callReceived4000 = new Call_Recieved4000(this);

		scheduleAction(callReceived1000);
		scheduleAction(callReceived2000);
		scheduleAction(callReceived3000);
		scheduleAction(callReceived4000);

		EndDay endDay = new EndDay(this);
		scheduleAction(endDay);
	}

	/************ implementation of Data Modules ***********/
	boolean traceFlag = false;
	/*
	 * Testing preconditions
	 */

	protected void testPreconditions(Behaviour behObj) {
		reschedule(behObj);
		// Check Activity Preconditions
		while (scanPreconditions() == true)
			/* repeat */;
	}

	private boolean scanPreconditions() {
		boolean statusChanged = false;

		Travel travel = new Travel(this);
		if (travel.precondition(this) == true) {
			travel.startingEvent();
			scheduleActivity(travel);
			statusChanged = true;
		}

		Lunch lunch = new Lunch(this);
		if (lunch.precondition(this) == true) {
			lunch.startingEvent();
			scheduleActivity(lunch);
			statusChanged = true;
		}

		return (statusChanged);
	}

	public void eventOccured() {
		if (traceFlag) {
			System.out.println("Clock: " + getClock());

			System.out.println("rEmployees[0].length: " + rEmployees[0].length + ", rEmployees[1].length: "
					+ rEmployees[1].length);

			System.out.println("T12Satisfied: " + output.contractsT12satisfied + ", totalT12: "
					+ output.totalNumberT12Contracts + ", T34Satisfifed " + output.contractsT34satisfied
					+ ", totalT34: " + output.totalNumberT34Contracts);

			System.out.println("SatisfactionLevelT12: " + (Math.round(output.getSatisfactionLevelT12() * 100))
					+ "%, SatisfactionLevelT34: " + (Math.round(output.getSatisfactionLevelT34() * 100))
					+ "%, SatisfactionLevelAll: " + (Math.round((output.getSatisfactionLevelAll() * 100))) + "%");

			showSBL();
		}
	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct) {
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}

}
