package simModel;

class UDPs 
{
	OfficeRepair model;  // for accessing the clock
	
	// Constructor
	protected UDPs (OfficeRepair model) { 
		this.model = model; 
		}
	

//CanStartLunch()
//Returns True as long as long as an employee can start lunch. 

	protected  boolean CanStartLunch() {
		boolean returnValue = false;  
		// GAComment: The following is not consistant with the CM.  Need to use UDP to reflect ABCmod paradigm.
		// Use UDPs as specified in the comments in the CM.
		
		// GAComment: The following is not consistant with the CM.  Need to use UDP to reflect ABCmod paradigm.
		// Use UDPs as specified in the comments in the CM.
		
		if ( (int) model.getClock()%1440 > 210 ) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < model.rEmployees[i].length; j++) {
					if( model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL &&
						model.rEmployees[i][j].lunchTaken == false ){
						returnValue = true ; 
					}
					
				}
				
			}
		}
		
		return returnValue ; 
		
	}
	
//GetEmployeeForLunch()
//Returns the two identifiers <EMP_TYPE, EMP_ID> from the Employees resource set for an employee who is eligible to take lunch.

		
	protected  int[] GetEmployeeForLunch () {
		//This method will return an integer array with the sole purpose of being
		// able to return 2 values, both an employee type and an employee Id, so that the model
		// is respected. This int[] is the java implementation for creating a procedure that returns two values
		// **Note -- this reflects the behaviour in the model as described by: 
		//	<etypeId, empId, jobQueueIdent> ← UDP.GetEmployeeForCall()
		
		int[] empIdentifers = new int[2] ; 
		
		for (int i = 0; i < 2; i++) {			
			for (int j = 0; j < model.rEmployees[i].length; j++) {
				if( model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL &&
						model.rEmployees[i][j].lunchTaken == false ){
						empIdentifers[0] = i ;
						empIdentifers[1] = j ; 
					}
				
			}
		}
		
		return empIdentifers;
	}
	

//ReadyToTakeCall() 
/*Returns true if there is an employee available and a compatible call in 
 * the a queue that the employee can service. In other words , if there is a non-empty job queue
 *  (i.e. has a call in it) and an employee who can service this call, return true. 
 *  Otherwise return false. 
 */
	
	protected boolean ReadyToTakeCall() {
		boolean returnValue = false ;
		
	for (int i = 0; i < 2; i++) {
			for (int j = 0; j < model.rEmployees[i].length; j++) {
				if(model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL){
					
					if(i == Constants.EMPLOYEE_ALL){
						if ((model.qJobs[Constants.Job_3000_4000_P].size() > 0 ) ||
						   (model.qJobs[Constants.Job_3000_4000_B].size() > 0 ) ||
						   (model.qJobs[Constants.Job_1000_2000_P].size() > 0 ) ||
						   (model.qJobs[Constants.Job_1000_2000_B].size() > 0) ){
							   returnValue = true ; 
						   }
					}else{
						if ((model.qJobs[Constants.Job_1000_2000_P].size() > 0 ) ||
						    (model.qJobs[Constants.Job_1000_2000_B].size() > 0) ){
									   returnValue = true ; 
								   }
					}
					
				}
				
//			Employee e = model.rEmployees[i][j];
//			
//			if (i == Constants.EMPLOYEE_T12) {
//				if (e.status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_1000_2000_P].size() > 0 || model.qJobs[Constants.Job_1000_2000_B].size() > 0)) {
//					returnValue = true;
//					emp = e;
//					this.empType = "T12";
//					return (returnValue);
//				}
//			} else {
//				if (e.status == Employee.StatusValues.READY_FOR_CALL && (model.qJobs[Constants.Job_3000_4000_P].size() > 0
//						|| model.qJobs[Constants.Job_3000_4000_B].size() > 0)) {
//					returnValue = true;
//					emp = e;
//					this.empType = "ALL";
//					return (returnValue);
//				}
//			}
		}
	}
		
		
		
		return returnValue ; 
	}
	
	
//GetEmployeeForCall()
/*Returns the two identifiers <EMP_TYPE,EMP_ID> for Employees and the identifier for Jobs. 
 * This gives an employee and the queue which contains the next call to be serviced. 
 * This procedure implements the dispatcher rules, sending employees to jobs in the specified priority. 
 * SEE HLCM - Simplications and Assumptions for priority.
 */



	
	

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
