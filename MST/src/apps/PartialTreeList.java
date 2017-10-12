package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() throws NoSuchElementException
    {/* COMPLETE THIS METHOD */
       Node node = null;
       if(rear == null) {
            throw new NoSuchElementException();
         }
         else
         {
            size =size-1;
            if(rear.next == rear)
            {
                node = rear;
                rear = null;
                return node.tree;
            }
            else
            {
                node = rear.next;
                rear.next = rear.next.next;
                return node.tree;
            }
        }
    }
  

    public PartialTree removeTreeContaining(Vertex vertex) throws NoSuchElementException
    {/* COMPLETE THIS METHOD */
               boolean found = false;
                Node storeTree = null;
                PartialTree output = null;
                
       
                if(rear == null)  {
                    throw new NoSuchElementException();
                }
              
                Node pointer = rear.next, prev = rear;
              
                int i;
                i= 0;
                output = removeT(i, vertex, pointer, found, output, prev);
      
                return output;
    }
  
    private boolean removeTraverse( boolean found, PartialTree Trees,Vertex vertex)
    {
        while(vertex != null)
        {
            if(vertex == Trees.getRoot()){
                found = true;
                return true;
            }
            if(vertex.equals(vertex.parent))
            {
                return false;
            }
      
            vertex = vertex.parent;
        }
        return false;
    }
          
    private PartialTree removeT(int i,Vertex vertex,Node temp, boolean found,PartialTree out,Node prev) {
        do
         {
             if(removeTraverse(found, temp.tree,vertex) == true)
             {
                 if(temp != rear) {
                    
                	 out = temp.tree;
                     
                     prev.next = temp.next;
                   
                     size= size-1;
                   
                     return out;
  	 
                 }
                   else  {
                	   out = temp.tree;
                       
                       prev.next = rear.next;
                     
                       rear = prev;
                     
                       size=size-1;
                     
                       return out;
                 }
     
             }
                prev = temp;
        
                temp = temp.next;
     
                i++;
               
         }while(i < size);
      
        return out;
    }
   
  
  
    
    
    
    
    
    
    
    
    
    
    
    
    public int size() {
       return size;
    }
  

    public Iterator<PartialTree> iterator() {
       return new PartialTreeListIterator(this);
    }
  
    private class PartialTreeListIterator implements Iterator<PartialTree> {
      
       private PartialTreeList.Node ptr;
       private int rest;
      
       public PartialTreeListIterator(PartialTreeList target) {
           rest = target.size;
           ptr = rest > 0 ? target.rear.next : null;
       }
      
       public PartialTree next()
       throws NoSuchElementException {
           if (rest <= 0) {
               throw new NoSuchElementException();
           }
           PartialTree ret = ptr.tree;
           ptr = ptr.next;
           rest--;
           return ret;
       }
      
       public boolean hasNext() {
           return rest != 0;
       }
      
       public void remove()
       throws UnsupportedOperationException {
           throw new UnsupportedOperationException();
       }
      
    }
}