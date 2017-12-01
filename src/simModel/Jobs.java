package simModel;

import java.util.ArrayList;
import java.util.List;

public class Jobs{
	
		// Implement the queue using an ArrayList object
//			public ArrayList<Call>[] Jobs = new ArrayList<Call>[4] ;
			List<Call> qJob_1000_2000_P = new ArrayList<>() ;
			List<Call> qJob_1000_2000_B = new ArrayList<>() ; 
			List<Call> qJob_3000_4000_P = new ArrayList<>() ; 
			List<Call> qJob_3000_4000_B = new ArrayList<>() ; 
			
			
			
			//Jobs[0] = qJob_1000_2000_B ; 
						
		//qJob_1000_2000_B,qJob_1000_2000_P,Job_3000_4000_B,qJob_3000_4000_P = new ArrayList<Call>();  // Size is initialised to 0
			
		// getters/setters and standard procedures for each queue job_1000_2000_B
			public int getNqJob_1000_2000_B() { return(qJob_1000_2000_B.size()); }
			
			public void spInsertQueqJob_1000_2000_B(Call call) { qJob_1000_2000_B.add(call); }
			
			public Call spRemoveQue() 
			{ 
				Call call = Employee.NO_CALL;
				if(qJob_1000_2000_B.size() != 0) call = qJob_1000_2000_B.remove(0);
				return(call);	
			}
			
		// getters/setters and standard procedures for each queue job_1000_2000_P
			public int getNqJob_1000_2000_P() { return(qJob_1000_2000_P.size()); }
			
			public void spInsertQueqJob_1000_2000_P(Call call) { qJob_1000_2000_P.add(call); }
			
			public Call spRemoveQueqJob_1000_2000_B() 
			{ 
				Call call = Employee.NO_CALL;
				if(qJob_1000_2000_B.size() != 0) call = qJob_1000_2000_B.remove(0);
				return(call);	
			}
			
		// getters/setters and standard procedures for each queue job_3000_4000_B
			public int getJob_3000_4000_B() { return(qJob_3000_4000_B.size()); }
			
			public void spInsertQueqJob_3000_4000_B(Call call) { qJob_3000_4000_B.add(call); }
			
			public Call spRemoveQueJob_3000_4000_B() 
			{ 
				Call call = Employee.NO_CALL;
				if(qJob_3000_4000_B.size() != 0) call = qJob_3000_4000_B.remove(0);
				return(call);	
			}
			
		// getters/setters and standard procedures for each queue job_3000_4000_P
			public int getNqJob_3000_4000_P() { return(qJob_3000_4000_P.size()); }
			
			public void spInsertQueqJob_3000_4000_P(Call call) { qJob_3000_4000_P.add(call); }
			
			public Call spRemoveQueJob_3000_4000_P() 
			{ 
				Call call = Employee.NO_CALL;
				if(qJob_3000_4000_P.size() != 0) call = qJob_3000_4000_P.remove(0);
				return(call);	
			}
			
	}


