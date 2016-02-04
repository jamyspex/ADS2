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

    public E front() throws ArrayQueueException {
        
        if(size == 0) throw new ArrayQueueException("Queue empty");

        return Q[front];
    }
	
    public void enqueue(E element) throws ArrayQueueException {

        if(size == n) throw new ArrayQueueException("Queue Overflow");

        Q[rear] = element;
        size++;
        rear = (rear + 1)%n;

    }

    
    public E dequeue() throws ArrayQueueException {
    
        if(size == 0) throw new ArrayQueueException("Queue Underflow");

        E temp = Q[front];
   
        size--;

        front = (front + 1)%n;

        return temp;
    
    }
    
    public String toString(){

        String s = "[";

        if(size != 0) s = s + Q[front].toString();

        if(size > 1)
        {
            int count = 1;

            for(int i = (front + 1)%n; count < size; i=(i+1)%n)
            {
                s = s + "," + Q[i].toString();
                count++;
            }
        }

        return s + "]";
    }
}
	
