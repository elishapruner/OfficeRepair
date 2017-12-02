package simModel;

public class Travel {
    OfficeRepair model;  // for referencing the model
    Employee emp;
    String empType = "none";
    
	
    public Travel(OfficeRepair model, Employee emp) { this.model = model;}
    
    protected boolean precondition(OfficeRepair simModel)
    {
            boolean returnValue = false;

            for(Employee e: model.rEmployees[model.constants.Employee_T12]){
                if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.constants.job_1000_2000_P].size() > 0 || model.constants.job_1000_2000_B].size() > 0)){
                    returnValue = true;
                    this.empType = "T12";
                    return(returnValue);
                }
            }
            
            for(Employee e: model.rEmployees[model.constants.Employee_ALL]){
                if (e.Status == Employee.StatusValues.READY_FOR_CALL && (model.constants.job_3000_4000_P].size() > 0 || model.constants.job_3000_4000_B].size() > 0)){
                    returnValue = true;
                    this.empType = "ALL";
                    return(returnValue);
                }
            }

            return(returnValue);
    }
    
    
    public void startingEvent() 
    {
        if (empType == "T12"){
            if(model.jobs[model.constants.job_1000_2000_P].size() > 0){
                emp.call = model.jobs[model.constants.job_1000_2000_P].remove();
            }else if(model.jobs[model.constants.job_1000_2000_B].size() > 0){
                emp.call = model.jobs[model.constants.job_1000_2000_B].remove();
            }
        }else{//all
            if(model.jobs[model.constants.job_3000_4000_P].size() > 0){
                emp.call = model.jobs[model.constants.job_3000_4000_P].remove();
            }else if(model.jobs[model.constants.job_3000_4000_B].size() > 0){
                emp.call = model.jobs[model.constants.job_3000_4000_B].remove();
            }
            if(model.jobs[model.constants.job_1000_2000_P].size() > 0){
                emp.call = model.jobs[model.constants.job_1000_2000_P].remove();
            }else if(model.jobs[model.constants.job_1000_2000_B].size() > 0){
                emp.call = model.jobs[model.constants.job_1000_2000_B].remove();
            }
        }
        emp.Status = Employee.StatusValues.SERVICING_CALL;
            
    }

    protected double duration() 
    {
            //ToDo: RVP for this procedure
            return (model.rvp.uTravelTime());
    }

    protected void terminatingEvent() 
    {
        //ToDo: instantiate service event
        Service s = new Service(model, emp); //Not correct

    }
}
