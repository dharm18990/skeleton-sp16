// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb=(T[])new Object[capacity];
        first=-1;
        last=-1;
        fillCount=0;
        this.capacity=capacity;
    }

    //circularly increase the index
    private int plusOne(int index){
        //index==rb.length

        index=index+1;
        if(index==rb.length)
            return 0;
        return index;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        if(fillCount()==0){//no items in the list
            rb[0]=x;
            first=0;
            last=0;
            fillCount++;
            return;
        }
        last=plusOne(last);
        rb[last]=x;
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }

        T t=rb[first];
        first=plusOne(first);
        fillCount--;

        if(fillCount()==0){
            first=-1;
            last=-1;
        }

        return t;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    public void print(){
        int counter=0;
        for(int i=first;counter<fillCount();i=plusOne(i)){
            System.out.print(rb[i]+"->");
            counter++;
        }
        System.out.println();
    }

    private class IteratorImpl implements Iterator{
        private int location;
        private int counter;

        public IteratorImpl(){
            location=first;
            counter=0;
        }

        @Override
        public boolean hasNext(){
            return counter<fillCount();
        }

        @Override
        public T next(){
            T t=rb[location];
            location=plusOne(location);
            counter+=1;
            return t;
        }

        @Override
        public void remove(){

        }
    }

    @Override
    public Iterator<T> iterator(){
        Iterator iterator=new IteratorImpl();
        return iterator;
    }



    // TODO: When you get to part 5, implement the needed code to support iteration.
}
