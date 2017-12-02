package simModel;

import static simModel.Call.serviceType.PREMIUM;

public class Service {
    
    OfficeRepair model;  // for referencing the model
    Employee e;
	
    public Service(OfficeRepair model, Employee emp) { this.model = model; this.e = emp;}
    
    public void startingEvent() 
    {
            e.Status = Employee.StatusValues.SERVICING_CALL;
    }

    protected double duration() 
    {
            //ToDo: RVP for this procedure
            return (model.rvp.duCallTime());
    }

    protected void terminatingEvent() 
    {
            if(e.call.uType2 == PREMIUM){
                if ((int)model.getClock() - (int)e.call.timeIn <=180){
                    model.output.contractsT12satisfied = model.output.contractsT12satisfied++;
                }
            }else{
                if ((int)model.getClock() - (int)e.call.timeIn <=1440){
                    model.output.contractsT12satisfied = model.output.contractsT12satisfied++;
                }
            }
            e.Status = Employee.StatusValues.READY_FOR_CALL;

    }

}
