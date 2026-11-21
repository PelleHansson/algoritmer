import java.io.*;
import java.util.*;

public class indset {
    // static class Edge {
    //     Node u;
    //     Node v;
    //     Edge(Node u, Node v){
    //         this.u = u;
    //         this.v = v;

    //    }
    // // }
    // static class V {
     
    //     //List<Edge> edges = new ArrayList<>();
    //     //Set<V> neighbors = new HashSet<>();
    //     V () {
           
    //     }
        
    //     int degree() {
    //         int deg = 0;
    //         for (boolean b : neighbors) {
    //             if (b) deg++;
    //         }
    //         return deg;
    //     }
    //     // Set<V> N() {
    //     //     Set<V> neighborSet = new HashSet<>(neighbors);
    //     //     neighborSet.add(this);
    //     //     return neighborSet;
    //     // }
    // }
    static Set<Integer> minus(Set<Integer> a, Set<Integer> b) {   // a − b
        Set<Integer> r = new HashSet<>(a);
        r.removeAll(b);
        return r;
    }
    static Set<Integer> minus(Set<Integer> a, Integer b) {   // a − b
        Set<Integer> r = new HashSet<>(a);
        r.remove(b);
        return r;
    }

     static int degree(int v, Set<Integer> g) {
        int d = 0;
        for (int w : edges.get(v)) if (g.contains(w)) d++;
        return d;
    }
    Set<Integer> N(int v) {
        Set<Integer> neighborSet = new HashSet<>(edges.get(v));
        neighborSet.add(v);
        return neighborSet;
    }

 
   
   int R0( Set<Integer> g) {
        if (g.isEmpty()) return 0;
        int u = -1; int max = -1;
        for (int v : g) {
            int d = degree(v, g);

            if (d == 0) return 1 + R0(minus(g, v));
            if( d > max) max = v;            
        }
        return Math.max(1 + R0(minus(g, N(u))), R0(minus(g, u)));
    }
  

    // static V[] g;
    //static Set<Integer> g;
    static Map<Integer, Set<Integer>> edges = new HashMap<>();
  


   // static List<Edge> allEdges = new ArrayList<>();
    static Random r = new Random();

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File(args[0]));
        int n = in.nextInt();

        //graph = new boolean[n][n + 1]; // 0 index is used for if in the independent set
        // V[] g = new V[n];
        Set<Integer> g = new HashSet<>();
        for (int i = 0; i < n; i++){
            Set<Integer> neighbors = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int hasEdge = in.nextInt();
                if (hasEdge == 1) {
                    neighbors.add(j);
                }                                                  
            g.add(i);
            edges.put(i, neighbors);    
        } 
        in.close();

    }
}