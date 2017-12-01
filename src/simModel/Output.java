package simModel;

public class Output {
	OfficeRepair model;

	protected Output(OfficeRepair md) { model = md; }
    // Use OutputSequence class to define Trajectory and Sample Sequences
    // Trajectory Sequences

    // Sample Sequences

    // DSOVs available in the OutputSequence objects
    // If seperate methods required to process Trajectory or Sample
    // Sequences - add them here

    // SSOVs
	
	int contractsT12satisfied ; 
	int totalNumberT12Contracts;
	int contractsT34satisfied;
	int totalNumberT34Contracts;
	// Removed Number of Days
	// When ever you need it just do 
	//  current_time // (i.e. integer division) 1440 (length of day)
	int fixedTotalCost ; 
	int overtimeCost ;
	double averageDailyCost; 

    
}
