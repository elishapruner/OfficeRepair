package simModel;

public class DVPs {

	OfficeRepair model;  // for accessing the clock
	double nextTime ; 
	
	// Constructor
	protected DVPs (OfficeRepair model) { 
		this.model = model; 
		}
	
	protected double EndDay() {
		//double nextTime  ;
		if(model.getClock() == 0.0){
			nextTime = 960.0  ; 
		}else{
			nextTime = nextTime + 1440 ; 
			//nextTime = (double) (960 + 1440*(Math.floor(((int) model.getClock()) / 1440))) ;
		}
		
		return nextTime ;
		
	}
	
	
	
	
}
