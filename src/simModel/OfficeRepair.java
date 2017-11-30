package simModel;

import simulationModelling.AOSimulationModel;
import simulationModelling.Behaviour;
import simulationModelling.SequelActivity;

//
// The Simulation model Class
public class OfficeRepair extends AOSimulationModel
{
	// Constants available from Constants class
	/* Parameter */
        // Define the parameters

	/*-------------Entity Data Structures-------------------*/
	
	Employee rEmployees[][]; // Array of Employees
	Jobs rJobs[];
	
	
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
	
	// Output values - define the public methods that return values
	// required for experimentation.


	// Constructor
	public OfficeRepair(double t0time, double tftime, /*define other args,*/ Seeds sd)
	{
		// initialize parameters here
		
		// Create RVP object with given seed
		rvp = new RVPs(this,sd);


		
		
		// rgCounter and qCustLine objects created in initialize Action
		
		// initialize the simulation model
		initAOSimulModel(t0time,tftime);   

		     // Schedule the first arrivals and employee scheduling
		Initialise init = new Initialise(this);
		scheduleAction(init);  // Should always be first one scheduled.
		// Schedule other scheduled actions and activities here
	}

	/************  implementation of Data Modules***********/	
	/*
	 * Testing preconditions
	 */
/**	protected double closingTime; 
	public boolean isEmployeeOut(){
		for(i=0,i<2,i++){
			for {j=0,j<rEmployees[i].length,j++}{
			if (rEmployees[i][j].Status==Employee.StatusValues.SERVICING_CALL)
					return true;
			} 
		}
		return false;
	}

	public boolean implicitStopCondition() // termination explicit
	{
		boolean retVal = false;
	
		//System.out.println("ClosingTime = " + closingTime + "currentTime = "
		//		+ getClock() + "RG.Counter.n = " + rgCounter.size());
		if (getClock() >= closingTime && this.isEmployeeOut() == false)
			retVal = true;

		//System.out.println("implicit stop condition returns " + retVal);

		return (retVal);
	}
	
	**/
	
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
	}

	// Standard Procedure to start Sequel Activities with no parameters
	protected void spStart(SequelActivity seqAct)
	{
		seqAct.startingEvent();
		scheduleActivity(seqAct);
	}	

}


