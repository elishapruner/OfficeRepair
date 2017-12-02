package simModel;

import java.util.ArrayList;
import java.util.List;

public class JobQueue {
	
	List<Call> queue = new ArrayList<>() ; ; 
	
	public JobQueue(){
		
	}
	
	
	public void spInsertJobQue(Call call) {
		queue.add(call) ; 
		
		
	}
	
	public Call spRemoveQue() {
		if(queue.size() != 0){
			return queue.remove(0) ; 
			 
		}else{
			return null ; 
		}
		
	}
	
	
	
//	public int getNqJob_1000_2000_P() { return(qJob_1000_2000_P.size()); }
//	
//	public void spInsertQueqJob_1000_2000_P(Call call) { qJob_1000_2000_P.add(call); }
//	
//	public Call spRemoveQueqJob_1000_2000_B() 
//	{ 
//		Call call = Employee.NO_CALL;
//		if(qJob_1000_2000_B.size() != 0) call = qJob_1000_2000_B.remove(0);
//		return(call);	
//	}

}
