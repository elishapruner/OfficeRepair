package simModel;

import java.util.ArrayList;

public class Jobs {

	// implement the queue using an ArrayList object

	private ArrayList<Call> Jobs = new ArrayList<Call>(4);
	// Size is initialized to 0

	// getters/setters and standard procedures

	public int getN() {
		return (Jobs.size());
	}

	public void spInsertQue(Call call) {
		Jobs.add(call);
	}

	// public Jobs spRemoveQue()

	{
		// Jobs jobs = Employee.empAvailable;
		// if(conveyor.size() != 0) comp = conveyor.remove(0);
		// return(comp);

	}

}
