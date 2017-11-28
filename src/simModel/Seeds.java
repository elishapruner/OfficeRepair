package simModel;

import cern.jet.random.engine.RandomSeedGenerator;

public class Seeds 
{
		
		int equipType;   // comment 1
		int servType;   // comment 2
		int travelTime;   // comment 3
		int arr;   // comment 4

		public Seeds(RandomSeedGenerator rsg)
		{
			equipType =rsg.nextSeed();
			servType =rsg.nextSeed();
			travelTime =rsg.nextSeed();
			arr =rsg.nextSeed();
		}
}

