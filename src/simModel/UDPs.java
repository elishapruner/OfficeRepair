package simModel;

import simModel.Call.EquipmentTypes;
import simModel.Call.ServiceTypes;
import simulationModelling.ScheduledActivity;

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
	

//StartIdleEmpLunch()
/* UDP starts a new lunch activity  for every employee 
 * with status of READY_FOR_CALL (i.e. idle),
 */
	
protected void StartIdleEmpLunch() {
System.out.println("/****LUNCH_CHECK**** CLock"+((int) model.getClock())+"\n\t ");
	for (int i = 0; i < 2; i++) {
		for (int j = 0; j < model.rEmployees[i].length; j++) {
			System.out.println("EmpBefore: " +i+" " +j+ " "+model.rEmployees[i][j].status);
			if( model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL &&
				model.rEmployees[i][j].lunchTaken == false ){
				
				Lunch lunch = new Lunch(model) ;
				model.scheduleActivity(lunch) ;
				
				
			}
			
		}
		
	}
	System.out.println("UDP_LUNCH_SBL");
				model.showSBL(); 
				System.out.println("************?");
}

	
//GetEmployeeForLunch()
//Returns the two identifiers <EMP_TYPE, EMP_ID> from the Employees resource set for an employee who is eligible to take lunch.

		
	protected  int[] GetEmployeeForLunch () {
		//This method will return an integer array with the sole purpose of being
		// able to return 2 values, both an employee type and an employee Id, so that the model
		// is respected. This int[] is the java implementation for creating a procedure that returns two values
		// **Note -- this reflects the behaviour in the model as described by: 
		//	<etypeId, empId> ← UDP.GetEmployeeForLunch()
		
		int[] empIdentifers = new int[2] ; 
		
		for (int i = 0; i < 2; i++) {			
			for (int j = 0; j < model.rEmployees[i].length; j++) {
//				System.out.println("/****LUNCH_CHECK****\n\t "+i+" " +j+ " "+model.rEmployees[i][j].status);
				if( model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL &&
						model.rEmployees[i][j].lunchTaken == false ){
						empIdentifers[0] = i ;
						empIdentifers[1] = j ; 
//						System.out.println("Assigned empoyee "+i+" " +j+ " ");
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
	// checks if there is any call in the system and an there is an apprioriate available employee to respond to it
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

protected int[] GetEmployeeForCall() {
	//int[] is implemented here so that the UDP can return multiple valus as described in the model 
	//The implmented UDP will be able to capture the same behaviour as :
	// 		<etypeId, empId, jobQueueId> <-- UDP.GetEmpoyeeForCall() 
	int [] empIdentAndJobIdent = new int[3] ; 
	
	boolean availableEMP_T12 = false ; 
	boolean availableEMP_ALL = false ; 
	int[]	employeeT12Ident = new int[2]  ;
	int[] 	employeeALLIdent = new int[2] ;
	
	for (int i = 0; i < 2; i++) {			
		for (int j = 0; j < model.rEmployees[i].length; j++) {
			if( model.rEmployees[i][j].status == Employee.StatusValues.READY_FOR_CALL){
				if(i == Constants.EMPLOYEE_T12){
					availableEMP_T12 = true ; 
					employeeT12Ident[0] = i ;
					employeeT12Ident[1] = j ; 
				}else{
					availableEMP_ALL = true ; 
					employeeALLIdent[0] = i ;
					employeeALLIdent[1] = j ; 
				}
			}		
		}
	}
	
	// Check if there is a premium call of type 3000 or 4000 (highest priority) 
	// and if there is an EMP_ALL to service the call 
	if (model.qJobs[Constants.Job_3000_4000_P].size() > 0 && availableEMP_ALL){
			// if there is call and an employee is available and service
			empIdentAndJobIdent[0] = employeeALLIdent[0] ; // Assign value to etypeId
			empIdentAndJobIdent[1] = employeeALLIdent[1] ;  // Assign value to empId
			empIdentAndJobIdent[2] = Constants.Job_3000_4000_P ;
	// If there is no Job in the Jobs.[Job_3000_4000] or there is no employee who can service it
	// check the next queue based on the priority 
	}else if (model.qJobs[Constants.Job_1000_2000_P].size() > 0 && (availableEMP_ALL || availableEMP_T12) ){
			if(availableEMP_T12){
				empIdentAndJobIdent[0] = employeeT12Ident[0]  ; // Assign value to etypeId
				empIdentAndJobIdent[1] = employeeT12Ident[1] ;  // Assign value to empId
				empIdentAndJobIdent[2] = Constants.Job_1000_2000_P ;
			}else if (availableEMP_ALL){
				empIdentAndJobIdent[0] = employeeALLIdent[0] ; // Assign value to etypeId
				empIdentAndJobIdent[1] = employeeALLIdent[1] ;  // Assign value to empId
				empIdentAndJobIdent[2] = Constants.Job_1000_2000_P ;
			}	
	// Again if there is no jobs in the queue or there is no employee to service it 
	// check the next job queue
	}else if(model.qJobs[Constants.Job_3000_4000_B].size() > 0 && (availableEMP_ALL) ){
				
					empIdentAndJobIdent[0] = employeeALLIdent[0] ;// Assign value to etypeId
					empIdentAndJobIdent[1] = employeeALLIdent[1] ;  // Assign value to empId
					empIdentAndJobIdent[2] = Constants.Job_3000_4000_B ;
	// Again if there is no jobs in the queue or there is no employee to service it 
	// check the next job queue
					
	}else if(model.qJobs[Constants.Job_1000_2000_B].size() > 0 && (availableEMP_ALL || availableEMP_T12) ){
		if(availableEMP_T12){
			empIdentAndJobIdent[0] = employeeT12Ident[0]  ; // Assign value to etypeId
			empIdentAndJobIdent[1] = employeeT12Ident[1] ;  // Assign value to empId
			empIdentAndJobIdent[2] = Constants.Job_1000_2000_B ;
		}else if(availableEMP_ALL){
			empIdentAndJobIdent[0] = employeeALLIdent[0] ; // Assign value to etypeId
			empIdentAndJobIdent[1] = employeeALLIdent[1] ;  // Assign value to empId
			empIdentAndJobIdent[2] = Constants.Job_1000_2000_B ;
		
		}		
	}
		
	return empIdentAndJobIdent ; 
}
	
	
//	if (empType == "T12") {
//		if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_1000_2000_P].remove(0);
//		} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_1000_2000_B].remove(0);
//		}
//	} else {// all
//		if (model.qJobs[Constants.Job_3000_4000_P].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_3000_4000_P].remove(0);
//		} else if (model.qJobs[Constants.Job_3000_4000_B].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_3000_4000_B].remove(0);
//		} else if (model.qJobs[Constants.Job_1000_2000_P].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_1000_2000_P].remove(0);
//		} else if (model.qJobs[Constants.Job_1000_2000_B].size() > 0) {
//			emp.icCall = model.qJobs[Constants.Job_1000_2000_B].remove(0);
//		}
//	}
	
	

//UpdateContractSSOVs(iC.Call)
/*Updates all the SSOV output variables that describe number of contracts and number of contracts satisfied. 
 * Specifically, it updates SSOV.contractsT12satisfied, SSOV.totalNumberT12Contracts, 
 * SSOV.contractsT34satisfied, SSOV.totalNumberT34Contracts.
 */

protected void UpdateContractSSOVs(Call icCall){
	
	if ((icCall.equipmentType == EquipmentTypes.TYPE1000) || (icCall.equipmentType == EquipmentTypes.TYPE2000)) {
		model.output.totalNumberT12Contracts += 1;
		if (icCall.serviceType == ServiceTypes.PREMIUM) {
			if ((int) model.getClock() - (int) icCall.timeIn <= 180) {
				model.output.contractsT12satisfied += 1;
			}
		} else {
			if ((int) model.getClock() - (int) icCall.timeIn <= 1440) {
				model.output.contractsT12satisfied += 1;
			}
		}
	} 

	if ((icCall.equipmentType == EquipmentTypes.TYPE3000) || (icCall.equipmentType == EquipmentTypes.TYPE4000)) {
		model.output.totalNumberT34Contracts += 1;
		if (icCall.serviceType == ServiceTypes.PREMIUM) {
			if ((int) model.getClock() - (int) icCall.timeIn <= 180) {
				model.output.contractsT34satisfied += 1;
			}
		} else {
			if ((int) model.getClock() - (int) icCall.timeIn <= 1440) {
				model.output.contractsT34satisfied += 1;
			}
		}
	}
}


//UpdateOvertimeSSOVs(etypeId)
/*Updates SSOV.overtimeCost based on the current time and the wage of the type of employee that was servicing the call.
 *  Since this UDP is called from terminating event of the service activity, 
 *  the current time will represent the time at which the call finishes. 
 */

protected void UpdateOvertimeSSOV(int etypeId){
	// mod 1440 gets individual day and 540 gives 5:00 pm of that day. 
	if ((int) model.getClock() % 1440 > 540) {
		if (etypeId == Constants.EMPLOYEE_T12) {
			model.output.overtimeCost += (( (int) (model.getClock() % 1440) - 540)/60) * Constants.EMP_T12_OVERTIME_WAGE;
		} else {
			model.output.overtimeCost += (( (int) (model.getClock() % 1440) - 540)/60) * Constants.EMP_ALL_OVERTIME_WAGE;
		}

	}
}


//ComputeServieDuration()
/*This UDP returns the service time of a call given by RVP.uSericeTime(equipType) UNLESS ….
 *  The call is of type BASIC and will not be completed before 5:30, 
 *  then The service time duration is extended to include an overnight idle time.
 */

protected double ComputerServiceDuration(Call icCall, int etypeId) {
	
	double ServiceTime = model.rvp.uServiceTime(icCall.equipmentType) ;
	
	
	if (  ((int) model.getClock() % 1440) + ServiceTime > 570  && icCall.serviceType == Call.ServiceTypes.BASIC){
		double timeTill5_30  = 570.0 - ( (model.getClock() % 1440) ) ; 
		// 870 is the time from 5:30 to 8, this is added to the service duration 
		ServiceTime = timeTill5_30  + 870.0 + (ServiceTime - timeTill5_30) ; 
		// Add 30 minutes of overtime to SSOV.overtTime
		if (etypeId == Constants.EMPLOYEE_T12) {
			model.output.overtimeCost += (30.0 * Constants.EMP_T12_OVERTIME_WAGE) ;
		} else {
			model.output.overtimeCost += (30.0 * Constants.EMP_ALL_OVERTIME_WAGE) ;
		}
	}
	
	return ServiceTime ; 
	
}










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
	
	

