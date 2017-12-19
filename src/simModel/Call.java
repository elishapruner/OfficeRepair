package simModel;

public class Call {

	// Denotes the type of Equipment that must be repaired.
	public static enum EquipmentTypes {
		TYPE1000, TYPE2000, TYPE3000, TYPE4000
	};

	EquipmentTypes equipmentType; // Type of equipment

	// Denotes the quality of service provided to the customer. Directly affects the response time.
	public static enum ServiceTypes {
		BASIC, PREMIUM
	};

	ServiceTypes serviceType; // Type of service

	// Time that the call was entered into the system.
	double timeIn;
	
}
