package simModel;

public class DVPs {

	OfficeRepair model;  // for accessing the clock
	
	// Constructor
	protected DVPs (OfficeRepair model) { 
		this.model = model; 
		}
	
	protected double EndDay() {
		double nextTime = 0.0 ; 
		if(model.getClock() == 0.0){
			nextTime = 960.0  ; 
		}else{
			nextTime = (double) (nextTime + 1440*(Math.floor(((int) model.getClock()) / 1440))) ;
		}
		
		return nextTime ;
		
	}
	
	
	
	
}
