/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demorunner.graph;


import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author aless
 */
public class Graph {
        
    private int node_count;
    private LinkedList<Edge>[] adjGraph;
    
    public Graph(int nodi)
    {
        node_count=nodi;
        adjGraph=new LinkedList[node_count];
        
        for(int i=0;i<node_count;i++)
            adjGraph[i]=new LinkedList<Edge>();
    }
    
    public boolean addEdge(int source,int dest,int peso)
    {
        if(!adjGraph[source].contains(dest))
        {
         OrderInsert(source,dest,peso);
         OrderInsert(dest,source,peso);
        //adjGraph[source].addFirst(new Edge(dest,peso));
       // adjGraph[dest].addFirst(new Edge(source,peso));
        return true;
        }
        
      return false;  
    }
    
    public void printGraph()
    {
        for(int i=0;i<getNode_count();i++)
        {
           System.out.print(i+": ");
            for(Edge curr : getAdjGraph()[i])
                System.out.print(curr.getTarget() + "(" + curr.getWeight() + ")" + "-> ");
            
            System.out.println();
        }
        
        
        
    }
    
    public void randomGraph(int edge_min,int edge_max)
    {
        try
        {
          
          
          if(getNode_count()==0)
              throw new GraphException("randomGraph(): Graph is not inizialized");
          
          if(edge_min>edge_max)
              throw new GraphException("randomGraph(): edge_min can't be higher than edge_max");
          
          Random random = new Random();
          int n_edge=random.nextInt(edge_max-edge_min)+edge_min;
          int source,dest,peso;
        // System.out.println("n_edge="+n_edge);
          for(int i=0; i<n_edge;i++)
          {
              source=random.nextInt(getNode_count());
              dest=random.nextInt(getNode_count());
              peso=random.nextInt(100);
            //  System.out.println("s="+source+" d=" + dest);
              if(source==dest)
                  i--;
              else
              {
                  if(!addEdge(source,dest,peso))
                      i--;
              }
          }
        }
        catch(GraphException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public void OrderInsert(int source, int dest, int peso) {

        if (getAdjGraph()[source].size() == 0) {
            getAdjGraph()[source].add(new Edge(dest,peso));
        } else if (getAdjGraph()[source].get(0).getTarget() > dest) {
            getAdjGraph()[source].add(0,new Edge(dest,peso));
        } else if (getAdjGraph()[source].get(getAdjGraph()[source].size() - 1).getTarget() < dest) {
            getAdjGraph()[source].add(getAdjGraph()[source].size(), new Edge(dest,peso));
        } else {
            int i = 0;
            while (getAdjGraph()[source].get(i).getTarget() < dest) {
                i++;
            }
            getAdjGraph()[source].add(i,new Edge(dest,peso));
        }

    }
   
   
    //=======================================================
    public class GraphException extends Exception {

    public GraphException(String message) {
        super(message);
    }
   }
   //======================================================= 

    /**
     * @return the node_count
     */
    public int getNode_count() {
        return node_count;
    }

    /**
     * @return the adjGraph
     */
    public LinkedList<Edge>[] getAdjGraph() {
        return adjGraph;
    }
}
