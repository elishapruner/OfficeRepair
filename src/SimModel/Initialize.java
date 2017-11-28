package SimModel;

import simulationModelling.ScheduledAction;

public class Initialize extends ScheduledAction {
	ModelName model;
	
	// Constructor
	protected Initialize(ModelName model) { this.model = model; }

	double [] ts = { 0.0, -1.0 }; // -1.0 ends scheduling
	int tsix = 0;  // set index to first entry.
	protected double timeSequence() 
	{
		return ts[tsix++];  // only invoked at t=0
	}

	protected void actionEvent() 
	{
		// System Initialization
                // Add initialization instructions 
	}
}
