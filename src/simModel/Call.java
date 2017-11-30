package simModel;

public class Call {

	
	// Denotes the type of Equipment that must be repaired. 
		enum equipmentType { E1000, E2000, E3000, E4000};
		equipmentType uType1;  // Type of equipment
		
		// Denotes the quality of service provided to the customer. Directly affects the response time.
		enum serviceType { BASIC, PREMIUM};
		serviceType uType2;  // Type of service
		
		// Time that the call was entered into the system.
		double timeIn;	
			
		 @Override
		    public String toString() {
		        return String.format(uType1.name(), uType2.name(), "[timeIn: %s]");
		    }

}
