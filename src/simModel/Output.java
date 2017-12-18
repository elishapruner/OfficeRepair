package simModel;

public class Output {
	OfficeRepair model;

	protected Output(OfficeRepair md) {
		model = md;
	}

	// SSOVs
	int contractsT12satisfied;
	int totalNumberT12Contracts;
	int contractsT34satisfied;
	int totalNumberT34Contracts;
	// GAComment: Why should first Cost variables are integers?
	int fixedTotalCost;
	int overtimeCost;
	double averageDailyCost;

	// GAcomment: Please cast to double instead of float, much better precision.
	protected double getSatisfactionLevelT12() {
		if (totalNumberT12Contracts != 0) {
			return (float)contractsT12satisfied / (float)totalNumberT12Contracts;
		}
		return 0;
	}

	protected double getSatisfactionLevelT34() {
		if (totalNumberT34Contracts != 0) {
			return (float)contractsT34satisfied / (float)totalNumberT34Contracts;
		}
		return 0;
	}

	protected double getSatisfactionLevelAll() {
		if (totalNumberT12Contracts + totalNumberT34Contracts != 0) {
			return (float)(contractsT12satisfied + contractsT34satisfied) / (float)(totalNumberT12Contracts + totalNumberT34Contracts);
		}
		return 0;
	}
	// GAcomment: why not provide method to compute the averageDailyCost at the end of the observation interval
	//            as specified in the CM Output specs.

}
