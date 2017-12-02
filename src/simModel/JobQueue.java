package simModel;

import java.util.ArrayList;
import java.util.List;

public class JobQueue {

	List<Call> queue = new ArrayList<>();

	public void spInsertJobQue(Call call) {
		queue.add(call);
	}

	public Call spRemoveQue() {
		if (queue.size() != 0) {
			return queue.remove(0);
		} else {
			return null;
		}
	}

	public int size() {
		return queue.size();
	}

}
