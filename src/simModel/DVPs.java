package simModel;


public class DVPs {
	OfficeRepair model;  // for accessing the clock
   
	public enum Location {
        Temp1, Employee, Call}
	// Constructor
	protected DVPs(OfficeRepair model) { this.model = model; }

    /**
     * @param origin
     * @param destination
     * @return time which cost from origin to destination
     */
//    public double calculateTime(Location origin, Location destination) {
//        double distance = this.model.udp.distance(origin, destination);
//        double TravelSpeed = 20; 
//        double time = (distance / TravelSpeed) * 60; 
//        // ( miles / miles per hour) * minutes
//        return time;
//    }
	
}

	
	// Translate deterministic value procedures into methods
        /* -------------------------------------------------
	                       Example
	protected double getEmpNum()  // for getting next value of EmpNum(t)
	{
	   double nextTime;
	   if(model.clock == 0.0) nextTime = 90.0;
	   else if(model.clock == 90.0) nextTime = 210.0;
	   else if(model.clock == 210.0) nextTime = 420.0;
	   else if(model.clock == 420.0) nextTime = 540.0;
	   else nextTime = -1.0;  // stop scheduling
	   return(nextTime);
	}
	------------------------------------------------------------*/

  

