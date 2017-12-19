package simModel;

class UDPs 
{
	OfficeRepair model;  // for accessing the clock
	
	// Constructor
	protected UDPs (OfficeRepair model) { 
		this.model = model; 
		}
	
	protected Employee GetEmployeeForLunch () {
		Employee currEmployee;
		
		for (int i = 0; i < model.numEmployeesT12 + model.numEmployeesALL; i++) {
			if (i < model.numEmployeesT12) {
				currEmployee = model.rEmployees.get(Constants.EMPLOYEE_T12).get(i);
				if (currEmployee.lunchTaken == false) {
					return currEmployee;
				}
			} else {
				i = i - model.numEmployeesT12;
				currEmployee = model.rEmployees.get(Constants.EMPLOYEE_ALL).get(i);
				if (currEmployee.lunchTaken == false) {
					return currEmployee;
				}
			}
		}
		
		for (int i = 0; i < model.numEmployeesALL; i++) {
			
		}
		
		int EMP_TYPE = Constants.EMPLOYEE_T12;
		int EMP_ID = 0;
		
		return model.rEmployees.get(EMP_TYPE).get(EMP_ID);
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
