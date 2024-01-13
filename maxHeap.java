/*
An array of type Object sorted as a max heap with the root being the largest key
this tree stores its root at index 1 of the array not 0 for simplicity
*/
import java.util.*;
class maxHeap{

    private Object[] heap;
    private int elements;

    public maxHeap(int k){
        this.heap = new Object[k+1];
        this.elements = 0;
    }

    //Bottom up construction where lower levels are downheaped first making it O(n) time
    public maxHeap(ArrayList<Object> points, int k){
        this.heap = new Object[k+1];
        this.elements = k;
        for(int i = 1; i <= k; i++){
            this.heap[i] = points.get(i-1);
        }

        for(int i = elements; i > 0; i--){
            downHeap(i);
        }
    }

    //swaps the values at 2 given indexs of the array
    public void swap(int a, int b){
        Object temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
        return;
    }

    //downheap swaps the element at the specfied position with the larger of its 2 children until it is at the bottom or the children are smaller than the parrent
    public void downHeap(int insert){
        int next = insert*2;
 
        while(next <= elements){

            if(next == elements){
                if(heap[insert].compareTo(heap[next]) == -1){
                    swap(insert, next);
                }          
            }else if (heap[next].compareTo(heap[next+1]) == -1){
                next++;
            }

            if (heap[insert].compareTo(heap[next]) == -1){
                swap(insert,next);
            }else{
                return;
            }

            insert = next;
            next = next*2;
        }
        return;
    }
    //upHeap swaps the specfied element with its parent until it is at the root of the heap or the parent is larger 
    public void upHeap(int insert){
        int next = (int)(insert/2);

        while(1 <= next){

            if (heap[insert].compareTo(heap[next]) == 1){
                swap(insert,next);
            }else{
                return;
            }

            insert = next;
            next = (int)(next/2);
        }
        return;
    }

    //inserts element in last spot of the array then upheaps it to its proper position O(log(n))
    public boolean offer(Object p){

        if (heap.length -1 > elements){
            elements++;
            heap[elements] = p;
            upHeap(elements);
            return true;
        }

        return false; 
    }

    //returns the root and replaces it with the last elemetn in the array and downheaps O(log(n))
    public Object poll(){
        if(elements == 0){
            return null;
        }
        
        Object ans = heap[1];
        heap[1] = heap[elements];
        heap[elements] = null;
        elements = elements - 1;
        downHeap(1);
        return ans;
    }

    public Object peek(){
        if(elements == 0){
            return null;
        }
        return heap[1];
    }

    public int size(){
        return elements;
    }

    public boolean isEmpty(){
        return (elements==0);
    }

    public Object[] getQueue(){
        return heap;
    }

}