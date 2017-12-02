package simModel;

import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class OfficeRepair extends AOSimulationModel
{
	// Constants available from Constants class
	Constants constants = new Constants() ; 
	/* Parameter */
        // Define the parameters
	int numEmployeesT12 ;
	int numEmployeesALL ; 
	

	/*-------------Entity Data Structures-------------------*/
	//Array of Employees with two indices : emp_type and emp_id 
	protected Employee[][] rEmployees;
	protected JobQueue[] jobs = new JobQueue[4] ; 
	

	/* Group and Queue Entities */
	// Define the reference variables to the various 
	// entities with scope Set and Unary
	// Objects can be created here or in the initialize Action

	/* input Variables */
	// Define any independent input variables here
	
	// References to RVP and DVP objects
	protected RVPs rvp;  // Reference to rvp object - object created in constructor
	protected DVPs dvp = new DVPs(this);  // Reference to dvp object
	protected UDPs udp = new UDPs(this);  

	// Output object
	protected Output output = new Output(this);
	
	// Output values - define the public methods that return values required for experimentation.
	public double getSatisfactionLevelT12() { return output.getSatisfactionLevelT12(); }
	public double getSatisfactionLevelT34() { return output.getSatisfactionLevelT34(); }
	public double getSatisfactionLevelAll() { return output.getSatisfactionLevelAll(); }
	public double getAverageDailyCost() { return output.averageDailyCost; }

	// Constructor
	public OfficeRepair(double t0time, Seeds sd, boolean traceFlag, int numEmployeesT12, int numEmployeesALL)
	{
		// Turn trancing on if traceFlag is true
		this.traceFlag = traceFlag;
		
		// initialize parameters here
		// Initialized in the experiment
		
		// Create RVP object with given seed
		rvp = new RVPs(this,sd);
		this.numEmployeesT12 = numEmployeesT12 ; 
		this.numEmployeesALL = numEmployeesALL ; 
		
		rEmployees = new Employee[2][] ;
		rEmployees[Constants.Employee_T12] = new Employee[numEmployeesT12] ;
		rEmployees[Constants.Employee_ALL] = new Employee[numEmployeesALL] ; 

		// rgCounter and qCustLine objects created in initialize Action
		
		// initialize the simulation model
		initAOSimulModel(t0time);    

		     // Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init);  // Should always be first one scheduled.
		// Schedule other scheduled actions and activities here
		// Schedule other scheduled actions
		Call_Recieved1000 callReceived1000 = new Call_Recieved1000(this);
		Call_Recieved2000 callReceived2000 = new Call_Recieved2000(this);
		Call_Recieved3000 callReceived3000 = new Call_Recieved3000(this);
		Call_Recieved4000 callReceived4000 = new Call_Recieved4000(this);
		scheduleAction(callReceived1000);
		scheduleAction(callReceived2000);
		scheduleAction(callReceived3000);
		scheduleAction(callReceived4000);
	}

	/************  implementation of Data Modules***********/	
	boolean traceFlag = false;
	/*
	 * Testing preconditions
	 */
	
	protected void testPreconditions(Behaviour behObj)
	{
		reschedule (behObj);
		// Check preconditions of Conditional Activities

		// Check preconditions of interruptions in Extended Activities
	}
	
	public void eventOccured()
	{
		//this.showSBL();
		// Can add other debug code to monitor the status of the system
		// See examples for suggestions on setup logging

		// Setup an updateTrjSequences() method in the Output class
		// and call here if you have Trajectory Sets
		// updateTrjSequences() 
		
		if (traceFlag)
		{
			 System.out.println("Clock: " + getClock() + ", contractsT12Satisfied " + output.contractsT12satisfied +
					 ", contractsT34Satisfifed " + output.contractsT34satisfied +
					 "\n\nSatisfactionT12: "+ output.getSatisfactionLevelT12() +
					 ", SatisfactionT34: " + output.getSatisfactionLevelT34() +
					 ", SatifactionLevelALl" + output.getSatisfactionLevelAll()); 
			 showSBL();			
		}
	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct)
	{
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}	

}


