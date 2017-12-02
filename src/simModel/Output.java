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

	protected double getSatisfactionLevelT12() {
		return contractsT12satisfied / totalNumberT12Contracts;
	}
	
	protected double getSatisfactionLevelT34() {
		return contractsT34satisfied / totalNumberT34Contracts;
	}
	
	protected double getSatisfactionLevelAll() {
		return (contractsT12satisfied + contractsT34satisfied) / (totalNumberT12Contracts + totalNumberT34Contracts);
	}
}





//package simModel;
//
//class Output 
//{
//	OfficeRepair model;
//	
//	protected Output(OfficeRepair md) { 
//		model = md; 
//	}
//	
//    // SSOVs
//	private int contractsT12satisfied = 1;
//	private int contractsT34satisfied = 1;
//	private int totalNumberT12Contracts = 2;
//	private int totalNumberT34Contracts = 2;
//	private int numberOfDays = 5;
//	private double fixedTotalCost = 100.00;
//	private double overtimeCost = 30.00;
//	
//	
//	protected double getSatisfactionLevelT12() {
//		return contractsT12satisfied / totalNumberT12Contracts;
//	}
//	
//	protected double getSatisfactionLevelT34() {
//		return contractsT34satisfied / totalNumberT34Contracts;
//	}
//	
//	protected double getSatisfactionLevelAll() {
//		return (contractsT12satisfied + contractsT34satisfied) / (totalNumberT12Contracts + totalNumberT34Contracts);
//	}
//	
//	public double getAverageDailyCost() { 
//		return (fixedTotalCost + overtimeCost) / numberOfDays;
//	}
//}
