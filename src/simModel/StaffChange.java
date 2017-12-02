package simModel;

import simulationModelling.*;

public class StaffChange extends ScheduledAction {
	OfficeRepair model;  // reference to model object
	
	public StaffChange(OfficeRepair model) { 
		this.model = model; 
	}

	// Implementation of timeSequence
	private double[] staffChangeTimeSeq = {0, 90, 210, 420, 540, -1 };
	private int sctIx = 0;
	public double timeSequence() 
	{ 
		double nxtTime = staffChangeTimeSeq[sctIx];
		sctIx++;
		return(nxtTime); 
	}

	// SchedEmp Event
	protected void actionEvent()
	{
//	    if(model.getClock() == staffChangeTimeSeq[0]) model.rgCounter.numEmp = 2;
//	    else if(model.getClock() == staffChangeTimeSeq[1]) model.rgCounter.numEmp += model.rgCounter.addEmp;
//	    else if(model.getClock() == staffChangeTimeSeq[2]) model.rgCounter.numEmp -= model.rgCounter.addEmp;
//	    else if(model.getClock() == staffChangeTimeSeq[3]) model.rgCounter.numEmp += model.rgCounter.addEmp;
//	    else if(model.getClock() == staffChangeTimeSeq[4]) model.rgCounter.numEmp -= model.rgCounter.addEmp;
//	    else System.out.println("Invalid time to schedule employees:"+model.getClock());
	}
}
