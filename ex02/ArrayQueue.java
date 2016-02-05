import java.util.*;
import java.io.*;

public class ArrayQueue<E> {

    public static final int CAPACITY = 10;  // default queue capacity
    private E[] Q;                          // E array used to implement the queue  
    private int n;                          // actual capacity of queue
    private int front;                      // index for the top of queue
    private int rear;                       // rear of the queue
    private int size;                       // size of the queue
    
	
    public ArrayQueue(){this(CAPACITY);}

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity){
	n = capacity;
	Q = (E[]) new Object [n]; 
	front = rear = size = 0;
    }
    //
    // NOTE: java does not allow creation of array with parametrised type!
    //
	
    public int size(){return size;}
	 
    public boolean isEmpty(){return size == 0;}

    // allows the front of the queue to be viewed without removing the element 
    public E front() throws ArrayQueueException {
        
        // if size is 0 or less the queue is empty so throw exception
        if(size <= 0) throw new ArrayQueueException("Queue empty");

        return Q[front];
    }
	
    // add element to end of queue 
    public void enqueue(E element) throws ArrayQueueException {

        // if size is greater than or equal to n (capacity) no new elements can 
        // be added so throw ArrayQueueException
        if(size >= n) throw new ArrayQueueException("Queue Overflow");

        // add element at rear of queue
        Q[rear] = element;

        // increse size by one
        size++;

        // increase rear by 1 or loop back to 0 if rear is at capacity 
        rear = (rear + 1)%n;

    }

    // remove element from front of queue    
    public E dequeue() throws ArrayQueueException {
    
        // if size is 0 or less the queue is empty so throw exception
        if(size == 0) throw new ArrayQueueException("Queue Underflow");

        // get the first element of the ArrayQueue 
        E temp = Q[front];
   
        // decrement size by 1
        size--;

        // increase front by 1 or loop back to 0 if front is at capacity
        front = (front + 1)%n;

        return temp;
    
    }
    
    public String toString(){

        String s = "[";

        // Q isn't empty so append the first element to s
        if(size != 0) s = s + Q[front].toString();

        // if queue has more than one element loop from first to last element
        if(size > 1)
        {
            // first element already appended so set count to 1 not 0
            int count = 1;

            // set i to front + 1 as first element already appended while
            // also taking into account circular nature of array
            // use count to keep track of number of elements appended
            // increment i in circular fashion
            for(int i = (front + 1)%n; count < size; i=(i+1)%n)
            {
                // append comma and ith element to s and increment count
                s = s + "," + Q[i].toString();
                count++;
            }
        }

        // return s with closing bracket appended 
        return s + "]";
    }
}
	
