package simModel;

import simulationModelling.ScheduledAction;

public class EndDay {

	class Call_Recieved extends ScheduledAction 
	{
		OfficeRepair model;
		public Call_Recieved (OfficeRepair model) {this.model=model;}

		public double timeSequence()
		{
			return 0;				
		}

		public void actionEvent() 
		{
			
		}
	}
	
}

	