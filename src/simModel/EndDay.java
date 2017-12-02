package simModel;



import simulationModelling.ConditionalAction;
import simulationModelling.ScheduledAction;

public class EndDay extends ConditionalAction {
    
     

		OfficeRepair model;
		
        public EndDay(OfficeRepair model) { this.model = model; }
        
        protected boolean precondition(OfficeRepair simModel)
        {
                boolean returnValue = false;


            if ((((int) model.getClock())%1440) %960 == 0 )
                returnValue = true;

                return(returnValue);
        }
        public double timeSequence()
        {
                return 0;				
        }



        public void actionEvent() 
        {
        	double numEmployeeT12 = (double) model.numEmployeesT12 ;
        	double numEmployeeALL = (double) model.numEmployeesALL ;
        	
        	for(Employee e : model.rEmployees[model.constants.Employee_T12]){
        		e.LunchTaken = false ; 
        	}
        	
        	for(Employee e : model.rEmployees[model.constants.Employee_T12]){
        		e.LunchTaken = false ; ;
        	}
        	
        	model.output.fixedTotalCost = (int) ((8.0*numEmployeeT12*model.constants.EMP_T12_HOURLY_WAGE) + 
        			(8.0*numEmployeeALL*model.constants.EMP_ALL_HOURLY_WAGE)* ( Math.floor(((int) model.getClock()) / 1440 ))) ;
        	model.output.averageDailyCost = (model.output.fixedTotalCost + model.output.overtimeCost) / ( Math.floor(((int) model.getClock()) / 1440 )) ;
        
//                for(Employee e : model.rEmployees[model.constants.Employee_T12]){
//                    if(e.Status == Employee.StatusValues.SERVICING_CALL){
//                        if(e.call.serviceType == Call.ServiceTypes.PREMIUM){
//                            //end the call
//                            if ((int)model.getClock() - (int)e.call.timeIn <=180){
//                                if(e.call.equipmentType == Call.EquipmentTypes.E1000 || e.call.equipmentType == Call.EquipmentTypes.E2000)
//                                    model.output.contractsT12satisfied+=1;
//                                }else{
//                                    model.output.contractsT34satisfied+=1;
//                                }
//                            }
//
//                            //end call, Set employee status to normal
//                            e.call = null;
//                            e.Status = Employee.StatusValues.READY_FOR_CALL;
//                        }
//                    }
//                
//                for(Employee e : model.rEmployees[model.constants.Employee_ALL]){
//                    if(e.Status == Employee.StatusValues.SERVICING_CALL){
//                        if(e.call.serviceType == Call.ServiceTypes.PREMIUM){
//                            //
//                            if ((int)model.getClock() - (int) e.call.timeIn <=180){
//                                if(e.call.equipmentType == Call.EquipmentTypes.E1000 || e.call.equipmentType == Call.EquipmentTypes.E2000)
//                                    model.output.contractsT12satisfied+=1;
//                                }else{
//                                    model.output.contractsT34satisfied+=1;
//                                }
//                            }
//
//                            //end call, Set employee status to normal
//                            e.call = null;
//                            e.Status = Employee.StatusValues.READY_FOR_CALL;
//                        }
//                    }
                }
        }
	


	
