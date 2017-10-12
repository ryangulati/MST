package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
		/* COMPLETE THIS METHOD */
			   PartialTree T; PartialTreeList m = new PartialTreeList();
			   int counter;
			   Vertex[] vertex= graph.vertices;
			   boolean[] visited = new boolean[vertex.length];
			   PartialTree.Arc arc = null;
			   counter= 0;
				int i =0; 
			   while(i < vertex.length)
			   {
			        T = new PartialTree(vertex[i]);
			        Vertex.Neighbor x = vertex[i].neighbors;
			    
			       visited[i] = true;
			       System.out.println(vertex[i]);
			     
			       MinHeap<PartialTree.Arc> Partial = T.getArcs(); 
			      
			      
			       while(x!= null){
			       arc = new PartialTree.Arc(vertex[i], x.vertex,x.weight);
			       Partial.insert(arc);
			       Partial.siftDown(counter);
			       x = x.next;
			       }
			       if(visited[i] == true)
			       {
			           m.append(T);
			       }
			       counter++;

			       i++;  
			   }
			   
			  
			       return m;
			  
			}
			   /**
			   * Executes the algorithm on a graph, starting with the initial partial tree list
			   *
			   * @param ptlist Initial partial tree list
			   * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
			   */
			   public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist)
			   {/* COMPLETE THIS METHOD */
			       ArrayList<PartialTree.Arc> list = new ArrayList();  
			      
			       while(ptlist.size()>1)
			       {
			          
			           PartialTree PT = ptlist.remove(); 
			      
			           PartialTree.Arc PQ = null;
			          
			           PartialTree.Arc val = PT.getArcs().getMin();
			          
			           Vertex v1 = val.v1;    
			           Vertex v2 = val.v2;
			      
			           checkingV2(v1,v2,PT,PQ,val);
			         
			            PQ = PT.getArcs().deleteMin();
			           System.out.println(PQ +" "+"is a component of the MST");
			      
			           PartialTree PT2 = ptlist.removeTreeContaining(PQ.v2);       
			          
			            PT.merge(PT2);
			            list.add(PQ);
			            ptlist.append(PT);
			          

			       }
			          
			       return list;
			   }
			   private static boolean compareMethod(Vertex v2, PartialTree PTX)
			   {
			        while(v2 != null)
			        {
			            if(PTX.getRoot() == v2)
			                return true;
			            
			            if(v2.equals(v2.parent)) { 
			                return false;
			        }
			            v2 = v2.parent;
			        }
			        return false;
			    }
			   private static void checkingV2( Vertex v1,Vertex v2, PartialTree PTX,PartialTree.Arc PQX, PartialTree.Arc storeArc){
			    while(compareMethod(v2, PTX) != false) {
			       PQX = PTX.getArcs().deleteMin();    
			       storeArc = PTX.getArcs().getMin();
			       v2 = storeArc.v2;v1 = storeArc.v1;
			       }
			   }
			  
			}

