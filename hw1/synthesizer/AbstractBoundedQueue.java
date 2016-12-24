package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T>{

	protected int fillCount;
    protected int capacity;
    
    //can avoid declaring here
    //public abstract T peek();
    //public abstract T dequeue();
    //public abstract void enqueue(T x);

    public int capacity(){
    	return capacity;
    }
    public int fillCount(){
    	return fillCount;
    }
    
}