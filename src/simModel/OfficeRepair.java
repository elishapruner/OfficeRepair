package simModel;

import java.util.ArrayList;

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
	public int numEmployeesT12;
	public int numEmployeesALL;
	double satisfactionLevel;
	double minSimTime;  // GAComment: see comment in CM.

	/*-------------Entity Data Structures-------------------*/
	// GAComment: Please use real arrays rather than Array lists for representing Sets. that is:
	// Employee rEmployees[][];
	// ArrayList<Call> qJobs[];
	protected ArrayList<ArrayList<Employee>> rEmployees;
	protected ArrayList<ArrayList<Call>> qJobs;

	// References to RVP and DVP objects
	protected RVPs rvp; // Reference to rvp object - object created in constructor

	// Output object
	protected Output output = new Output(this);

	// Output values - define the public methods that return values required for experimentation.
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
	public OfficeRepair(double t0time, Seeds sd, boolean traceFlag, int addEmployeesT12, int addEmployeesALL, double satisfaction, double minTime) {
		// Turn trancing on if traceFlag is true
		this.traceFlag = traceFlag;
		
		// Parameters
		numEmployeesT12 = addEmployeesT12;
		numEmployeesALL = addEmployeesALL;
		satisfactionLevel = satisfaction;
		minSimTime = minTime;

		// Create RVP object with given seed
		rvp = new RVPs(this, sd);

		rEmployees = new ArrayList<>(); // GAComment: rRemployees = new Employee[2][numEmployeesT12+numEmpoyeesAll]; Need to instantiate objects in Initialise
		qJobs = new ArrayList<>();  // GAComment: quJobs = new ArrayList[4];  // Need to instantiate objects in Initialise

		// initialize the simulation model
		initAOSimulModel(t0time);

		// Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init); // Should always be first one scheduled.
		
		UpdateNumEmployees updateNumEmp = new UpdateNumEmployees(this);
		scheduleAction(updateNumEmp);
		
		EndDay endDayAction = new EndDay(this);
		scheduleAction(endDayAction);

		Call_Received1000 callReceived1000 = new Call_Received1000(this);
		Call_Received2000 callReceived2000 = new Call_Received2000(this);
		Call_Received3000 callReceived3000 = new Call_Received3000(this);
		Call_Received4000 callReceived4000 = new Call_Received4000(this);

		scheduleAction(callReceived1000);
		scheduleAction(callReceived2000);
		scheduleAction(callReceived3000);
		scheduleAction(callReceived4000);
	}

	/************ implementation of Data Modules ***********/
	boolean traceFlag = false;
	/*
	 * Testing preconditions
	 */

	protected void testPreconditions(Behaviour behObj) {
		reschedule(behObj);

		// GAComment: Should instantiate the objects only if precondition is true.
		//            Need to repeat testing of preconditions until all are false.  
		//            May have multiple activites instatiated (e.g. Lunch).
		// Preconditions should be static methods.
		// Check Activity Preconditions
		Travel travel = new Travel(this);
		// GAComment:  Travel.precondition(this) == true
		if (travel.precondition(this) == true) {
			travel.startingEvent();
			scheduleActivity(travel);
		}
		// GAComment:  Lunch.precondition(this) == true
		Lunch lunch = new Lunch(this);
		if (lunch.precondition(this) == true) {
			lunch.startingEvent();
			scheduleActivity(lunch);
		}
	}

	public void eventOccured() {
		if (traceFlag) {
			System.out.println("Clock: " + getClock());

			System.out.println("Number of T12 Employees: " + numEmployeesT12 + ", Number of ALL Employees: "
					+ numEmployeesALL);

			System.out.println("T12Satisfied: " + output.contractsT12satisfied + ", totalT12: "
					+ output.totalNumberT12Contracts + ", T34Satisfifed " + output.contractsT34satisfied
					+ ", totalT34: " + output.totalNumberT34Contracts);

			System.out.println("SatisfactionLevelT12: " + (Math.round(output.getSatisfactionLevelT12() * 100))
					+ "%, SatisfactionLevelT34: " + (Math.round(output.getSatisfactionLevelT34() * 100))
					+ "%, SatisfactionLevelAll: " + (Math.round((output.getSatisfactionLevelAll() * 100))) + "%");
			
			System.out.println("----------------------------------------------------------------------------");

			showSBL();
		}
	}
	
	public boolean implicitStopCondition() {
		return getClock() >= minSimTime && output.getSatisfactionLevelT12() > satisfactionLevel && output.getSatisfactionLevelT34() > satisfactionLevel;
	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct) {
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}

}
