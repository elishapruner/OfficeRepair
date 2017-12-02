package simModel;

import static simModel.Call.serviceType.PREMIUM;
import simulationModelling.ScheduledAction;

public class EndDay {
    
        protected static boolean precondition(OfficeRepair simModel)
                {
                        boolean returnValue = false;


                    if (((int)simModel.getClock())%1440 == 0 )   //	rEmployees[0][1] this needs to be fixed
                        returnValue = true;

                        return(returnValue);
                }

	OfficeRepair model;
        public EndDay(OfficeRepair model) { this.model = model; }

        public double timeSequence()
        {
                return 0;				
        }



        public void actionEvent() 
        {
                for(Employee e : model.rEmployees[model.constants.Employee_T12]){
                    if(e.Status == Employee.StatusValues.SERVICING_CALL){
                        if(e.call.uType2 == PREMIUM){
                            //ToDo: end the call

                            //Set employee status to normal
                            e.call = null;
                            e.Status = Employee.StatusValues.READY_FOR_CALL;
                        }
                    }
                }
                for(Employee e : model.rEmployees[model.constants.Employee_ALL]){
                    if(e.Status == Employee.StatusValues.SERVICING_CALL){
                        if(e.call.uType2 == PREMIUM){
                            //ToDo: end the call

                            //Set employee status to normal
                            e.call = null;
                            e.Status = Employee.StatusValues.READY_FOR_CALL;
                        }
                    }
                }
        }
	
}

	
