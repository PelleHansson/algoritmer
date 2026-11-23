import java.io.*;
import java.util.*;

public class indset {
    static Map<Integer, Set<Integer>> copy(Map<Integer, Set<Integer>> g) {
        Map<Integer, Set<Integer>> h = new HashMap<>();
        for (var e : g.entrySet()) h.put(e.getKey(), new HashSet<>(e.getValue()));
        return h;
    }
    static Map<Integer, Set<Integer>> minus(Map<Integer, Set<Integer>> a, Set<Integer> b) {   // a − b
        Map<Integer, Set<Integer>> r = copy(a);    
        r.keySet().removeAll(b);
        for (Set<Integer> s : r.values()) s.removeAll(b);

        return r;
    }
  

    static int degree(int v, Map<Integer, Set<Integer>> g) {
        return g.get(v).size();
    }
    static Set<Integer> N(int v, Map<Integer, Set<Integer>> g) {
        Set<Integer> neighborSet = new HashSet<>(g.get(v));
        neighborSet.add(v);
        return neighborSet;
    }

 
   
   static int R0( Map<Integer, Set<Integer>> g) {
        count++;
        if (g.isEmpty()) return 0;
        int u = -1; int max = -1;
        for (int v : g.keySet()) {
            int d = degree(v, g);

            if (d == 0) return 1 + R0(minus(g, Set.of(v)));
            if( d > max) { max = d;  u = v; }           
        }
        return Math.max(1 + R0(minus(g, N(u, g))), R0(minus(g, Set.of(u))));
    }
    static int R1( Map<Integer, Set<Integer>> g) {
        count++;
        if (g.isEmpty()) return 0;
        int u = -1; int max = -1;
        for (int v : g.keySet()) {
            int d = degree(v, g);

            if (d == 0) return 1 + R1(minus(g, Set.of(v)));
            if (d == 1) return 1 + R1(minus(g, N(v, g)));
            if( d > max) { max = d;  u = v; }         
        }
        return Math.max(1 + R1(minus(g, N(u, g))), R1(minus(g, Set.of(u))));
    }
    static int R2( Map<Integer, Set<Integer>> g) {
        count++;
        if (g.isEmpty()) return 0;
        int u = -1; int max = -1;
        for (int v : g.keySet()) {
            int d = degree(v, g);

            if (d == 0) return 1 + R2(minus(g, Set.of(v)));
            if (d == 1) return 1 + R2(minus(g, N(v, g)));
            if (d == 2) {
                Iterator<Integer> neighborIterator = g.get(v).iterator();
                int a = neighborIterator.next();
                int b = neighborIterator.next();
                if (g.get(a).contains(b)) return 1 + R2(minus(g, N(v, g))); 
                else {
                    Set<Integer> zNeigh = new HashSet<>();
                    zNeigh.addAll(g.get(a));
                    zNeigh.addAll(g.get(b));
                    zNeigh.remove(v);
                    zNeigh.remove(a);
                    zNeigh.remove(b);
                    Map<Integer, Set<Integer>> g2 = minus(g, N(v, g));   
                    g2.put(v, zNeigh);                                    
                    for (int x : zNeigh) g2.get(x).add(v);                   
                    return 1 + R2(g2);
                }

            }
            if( d > max) { max = d;  u = v; }         
        }
        return Math.max(1 + R2(minus(g, N(u, g))), R2(minus(g, Set.of(u))));
    }


    static Random r = new Random();
    static long count = 0;
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File(args[0]));
        int n = in.nextInt();


        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++){
            Set<Integer> neighbors = new HashSet<>();
            for (int j = 0; j < n; j++) {
                int hasEdge = in.nextInt();
                if (hasEdge == 1) {
                    neighbors.add(j);
                }                      
            }                            
            graph.put(i, neighbors);    
        } 
        in.close();

        int result = 0;
        count = 0;
        //result = R0(graph);
        //System.out.println("R0: " + result  + ", calls: " + count + ", log: " + Math.log(count));
        //count = 0;
        result = R1(graph);
        System.out.println("R1: " + result + ", calls: " + count + ", log: " + Math.log(count));
        //result = R2(graph);
        //System.out.println("R2: " + result + ", calls: " + count + ", log: " + Math.log(count));     
    }
}