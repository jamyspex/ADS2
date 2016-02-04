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
        
       ArrayQueue<E> merged = new ArrayQueue<E>(q1.size()+q2.size());
      
       while(!q1.isEmpty() || !q2.isEmpty())
       {
            try{

                if(q1.front().compareTo(q2.front()) <= 0) merged.enqueue(q1.dequeue());
                
                else merged.enqueue(q2.dequeue());
            }
            catch(ArrayQueueException ex)
            {
               
                if(q1.isEmpty()) merged.enqueue(q2.dequeue());
                
                else merged.enqueue(q1.dequeue());
                  
            }            
       }
       return merged;
	}

    public void sort(){
        
        if(Q.size()==1) return;

        Q.enqueue(merge(Q.dequeue(), Q.dequeue()));

        sort();
    }

    public void add(E element){
        ArrayQueue<E> temp = new ArrayQueue<E>(1);

        temp.enqueue(element);

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
