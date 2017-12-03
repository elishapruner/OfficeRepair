package simModel;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfQueues<T> {

	List<T> queue = new ArrayList<T>();

	public void spInsertJobQue(T item) {
		queue.add(item);
	}

	public T spRemoveQue() {
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
