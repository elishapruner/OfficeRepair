package simModel;

public class DVPs {

	OfficeRepair model;  // for accessing the clock
	double nextTimeEndDay ; 
	double nextTimeStartLunch ; 

	// Constructor
	protected DVPs (OfficeRepair model) { 
		this.model = model; 
		}
	
	protected double EndDay() {

//		double nextTime;
		if(model.getClock() == 0.0){
			nextTimeEndDay = 960.0 ;
		}else {
			nextTimeEndDay = nextTimeEndDay  + 1440 ; 

		}
//		if (model.getClock() % 1440 == 0) {
//			System.out.println("\n\n\n/******* In our loop *******/\n\n\n");
//			nextTime = (double) 960+(1440*(Math.floor(((int) model.getClock()) / 1440)));
//		} else {
//			nextTime = -1.0;
//		}
		
		return nextTimeEndDay;
//		return (double) (1440*(Math.floor(((int) model.getClock()) / 1440)));
		
	}
	
	protected double StartLunch(){
		
		if(model.getClock() == 0.0){
			nextTimeStartLunch = 210 ;
		}else {
			nextTimeStartLunch = nextTimeStartLunch  + 1440 ; 
		}
		
		return nextTimeStartLunch ; 
		
	}
	
}
