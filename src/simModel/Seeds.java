package simModel;

import cern.jet.random.engine.RandomSeedGenerator;

public class Seeds 
{
		
		int equipmentType;   // comment 1
		int serviceType;   // comment 2
		int travelTime;   // comment 3
		int arr;   // comment 4

		public Seeds(RandomSeedGenerator rsg)
		{
			equipmentType =rsg.nextSeed();
			serviceType =rsg.nextSeed();
			travelTime =rsg.nextSeed();
			arr =rsg.nextSeed();
		}
}

