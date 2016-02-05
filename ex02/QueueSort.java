import java.util.*;
import java.io.*;

public class QueueSort<E extends Comparable<E>> {

    private ArrayQueue<ArrayQueue<E>> Q;
    public static final int CAPACITY = 10;  // default queue capacity
    private int n;    
    private boolean trace;
	
    public QueueSort(){this(CAPACITY);}
	
    public QueueSort(int capacity){
	n = capacity;
	Q = new ArrayQueue<ArrayQueue<E>>(n);
    }

    private ArrayQueue<E> merge(ArrayQueue<E> q1,ArrayQueue<E> q2) throws ArrayQueueException {
        
        // create new ArrayQueue large enough to hold the two arguments
       ArrayQueue<E> merged = new ArrayQueue<E>(q1.size()+q2.size());
      
        // loop until both input ArrayQueues are empty
       while(!q1.isEmpty() || !q2.isEmpty())
       {
            // try-catch block to catch any ArrayQueueExceptions caused
            // by input queues having different lengths 
            try{

                // compare the element at the front of each queue to see
                // which should be added to the merged ArrayQueue first
                if(q1.front().compareTo(q2.front()) <= 0) merged.enqueue(q1.dequeue());
                
                else merged.enqueue(q2.dequeue());
            }
            // Catch any ArrayQueueException from calling dequeue on an empty ArrayQueue 
            catch(ArrayQueueException ex)
            {
                // If q1 is empty add the first element from q2 to merged
                if(q1.isEmpty()) merged.enqueue(q2.dequeue());
                
                // If q2 is empty add the first element from q1 to merged
                else if(q2.isEmpty()) merged.enqueue(q1.dequeue());

                // other wise throw exception as it is unrecoverable 
                else throw ex;
                  
            }            
       }

       return merged;
	}

    public void sort(){
        
        // loop until Q only contains one element e.g all merges have
        // been preformed. 
        while(Q.size() != 1){

            // dequeue the first two elements from Q and merge them to 
            // form one sorted ArrayQueue and then enqueue the sorted 
            // queue back onto Q
            Q.enqueue(merge(Q.dequeue(), Q.dequeue()));

        }
    }

    public void add(E element){

        // Create an ArrayQueue of size 1 to hold element
        ArrayQueue<E> temp = new ArrayQueue<E>(1);

        // enqueue element in the temp queue
        temp.enqueue(element);

        // enqueue the temp queue on Q
        Q.enqueue(temp);
    }
    
    public String toString(){return Q.toString();}

    public void trace(){trace = !trace;}

    public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(new File(args[0]));
	ArrayList<String> data = new ArrayList<String>();
	while (sc.hasNext()) data.add(sc.next());
	int n = data.size();
	QueueSort<String> QS = new QueueSort<String>(n);
	for (String s : data) QS.add(s);
	if (args.length > 1) QS.trace();
	QS.sort();
	System.out.println(QS);
    }
}
