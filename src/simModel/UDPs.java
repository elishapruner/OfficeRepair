package simModel;

class UDPs 
{
	OfficeRepair model;  // for accessing the clock
	
	// Constructor
	protected UDPs (OfficeRepair model) { 
		this.model = model; 
		}
	
	protected Employee GetEmployeeForLunch () {
		Employee employeeGoingToLunch = new Employee();
		
		for (int i = 0; i < 2; i++) {			
			for (int j = 0; j < model.rEmployees[i].length; j++) {
				Employee currEmployee = model.rEmployees[i][j];
				if (currEmployee.lunchTaken == false) {
					employeeGoingToLunch = currEmployee;
				}
			}
		}
		
		return employeeGoingToLunch;
	}

	// Translate User Defined Procedures into methods
    /*-------------------------------------------------
	                       Example
	    protected int ClerkReadyToCheckOut()
        {
        	int num = 0;
        	Clerk checker;
        	while(num < model.NumClerks)
        	{
        		checker = model.Clerks[num];
        		if((checker.currentstatus == Clerk.status.READYCHECKOUT)  && checker.list.size() != 0)
        		{return num;}
        		num +=1;
        	}
        	return -1;
        }
	------------------------------------------------------------*/
	
	
}
