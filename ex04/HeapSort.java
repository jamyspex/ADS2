import java.util.*;
import java.io.*;

public class HeapSort {
    
    public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File(args[0]));
		ArrayList<String> data = new ArrayList<String>();
		
		// read all words into ArrayList
		while (sc.hasNext()) data.add(sc.next());

		// create heap big enough for all the words
		Heap<String> heap = new Heap<>(data.size());

		// add all the items from the ArrayList to the Heap
		for(String s : data)
		{
			heap.insert(s);
		}

		// call removeMin until the heap is empty
		// and print the element 
		while(!heap.isEmpty())
		{
			System.out.println(heap.removeMin());
		}
    }
}
//
// takes a filename from the command line
// and outputs that file sorted, one word per line
//
