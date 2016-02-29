
public class Heap <E extends Comparable<E>> {
 
    private Object H[];   // contains the objects in the heap
    private int last;     // index of last element in heap
    private int capacity; // max number of elements in heap  

    public Heap(int n){
	capacity = n;
	H        = new Object[capacity+1];
	last     = 0;
    }
    //
    // create a heap with capacity n
    // NOTE: H is an array of objects
    //       must use casting when delivering the minimum
    //       See min() below.
    //       This must also be done in removeMin()
    //

    public Heap(){this(7);}
    //
    // by default, create a new heap with capacity 7
    //

    @SuppressWarnings("unchecked")
    private int compare(Object x,Object y){return ((E)x).compareTo((E)y);}
    
    public int size(){
        return last;
    }
    //
    // returns the number of elements in the heap
    //
    
    public boolean isEmpty(){
        return last == 0;
    }
    //
    // is the heap empty?
    //
    
    @SuppressWarnings("unchecked")
    public E min() throws HeapException {
	if (isEmpty()) throw new HeapException("underflow");
	return (E) H[1];
    }
    //
    // returns element with smallest key, without removal
    // NOTE: must use casting to class (E)
    //
	
    
    public void insert(E e) throws HeapException {
        
        // check heap is not full
        if(last + 1 > capacity) throw new HeapException("Heap overflow");

        // increment last and insert e
        last++;
        H[last] = e;
        

        // preform up heap bubble to restore heap property after insertion
        int index = last;
        int parent;
        
        while(index > 0)
        {
            // System.out.println("start of up heap bubble");
            // get parent index from current index
            parent = (index)/2 > 1 ? (index)/2 : 1;
            // System.out.println("index)/2: " + ((index )/2));
            // System.out.println("last: " + last);
            // System.out.println("parent: " + parent);
            // System.out.println("index: " + index);

            // if heap property is true end bubbling
            if(compare(H[index], H[parent]) >= 0) 
                {
                    // System.out.println("heap property restored");
                    return;
                }
            
            // otherwise swap parent and child and continue up the heap
            swap(index, parent);
            index = parent;
        }
    }		
    //
    // inserts e into the heap
    // throws exception if heap overflow
    //

    // method to swap the values of its arguments
    @SuppressWarnings("unchecked")
    private void swap(int i, int j)
    {
        E temp = (E) H[i];
        H[i] = H[j];
        H[j] = temp;
    }
    
    @SuppressWarnings("unchecked")
    public E removeMin() throws HeapException {

        // ensure heap isn't empty, if it is throw exception
        if(last == 0) throw new HeapException("underflow");

        // assign minimum element to temp var
        E min = (E) H[1];

        // move last element to top, set old last to null, decrement last
        H[1] = H[last];
        H[last] = null;
        last--;
        // System.out.println("before bubble: " + toString());
        // Preform down heap bubble to restore heap property

        // initialize index vars
        int index = 1;
        int child;

        // loop forever, one of breaks in loop body will end loop
        while(true){

            // get child index from intial index
            child = index * 2;

            // System.out.println("child: " + child);
            // System.out.println("last: " + last);
            // System.out.println("index: " + index);

            // if child >= size break as we have reached bottom of heap
            if(child >= last) break;

            // if right child exists check which child is larger
            if(child + 1 < last)
            {
                // if right child is larger than left child increment child index
                // this ensures heap property check is accurate
                if(compare(H[child], H[child + 1]) > 0) {
                    // System.out.println("switching to right child");
                    child++;
                }
            }

            // if heap property is restored break
            if(compare(H[index], H[child]) <= 0) {
                // System.out.println("heap restored: " + toString());
                // System.out.println("heap property restored");
                break;
            }

            // otherwise swap parent and child and move down the heap
            swap(index, child);
            index = child;

        }

	   return min;
    }
    //
    // removes and returns smallest element of the heap
    // throws exception if heap is empty
    // NOTE: must cast result to class (E)
    //       see min() above
    //

    public String toString(){

        String output = "[";

        for(int i = 1; i <= last; i++)
        {
            if(i == last) output = output + H[i].toString();
            else output = output + H[i].toString() + ",";
        }
	   
       return output + "]";
    }
    //
    // outputs the entries in H in the order H[1] to H[last]
    // in same style as used in ArrayQueue
    // 
    // 
    
}

