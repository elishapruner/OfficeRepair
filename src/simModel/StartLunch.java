package simModel;

import simulationModelling.ScheduledAction;

public class StartLunch extends ScheduledAction {

	OfficeRepair model ; 
	
	public StartLunch(OfficeRepair model){
		this.model = model ; 
	}
	@Override
	protected double timeSequence() {
		// TODO Auto-generated method stub
		return model.dvp.StartLunch() ;
	}

	@Override
	protected void actionEvent() {
		System.out.println("LUNCH_UDP_EXECUTING");
		model.udp.StartIdleEmpLunch(); 
		// TODO Auto-generated method stub
		
	}

	
	
}
