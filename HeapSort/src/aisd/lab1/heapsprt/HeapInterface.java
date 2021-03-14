package aisd.lab1.heapsprt;

/**
 * Interface that describes heap data structure
 *
 * @author Maciej Dragun
 */
public interface HeapInterface <T extends Comparable<T>>{
	void put(T item);
	T pop();
}
