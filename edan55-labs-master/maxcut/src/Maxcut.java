import java.io.*;
import java.util.*;

public class Maxcut {
    static class Edge {
        Node u;
        Node v;
        int weight;
        Edge(Node u, Node v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
       }
    }
    static class Node {
        boolean inA = false;
        List<Edge> edges = new ArrayList<>();
        Node(){
       }
    }
    static int cutSum() {
        int c = 0;
        for (Edge e : allEdges){
            if(e.u.inA != e.v.inA) c += e.weight;
        }
        return c;
  }
   static void reset() {
        for (Node x : nodes) x.inA = false;
    }

    static void R(){
        for (Node node : nodes) {
           if (r.nextBoolean()) {
                node.inA = true;
           }
        }
    }

    static void S(){
        Boolean changed = true;
        while (changed) {
            changed = false;
            for (Node node : nodes){
                int weightOfChange = 0;
                for (Edge e : node.edges){
                    weightOfChange += e.u.inA == e.v.inA ? e.weight : -e.weight;
                }
                if (weightOfChange > 0) {
                    node.inA = !node.inA;
                    changed = true;
                }
            }
        } 
    }
    static void RS(){
        R();
        S();
    }

    static Node[] nodes;
    static List<Edge> allEdges = new ArrayList<>();
    static Random r = new Random();

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new File(args[0]));
        int n = in.nextInt(), m = in.nextInt();
        nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node();
        for (int i = 0; i < m; i++) {
            int uId = in.nextInt() - 1;
            int vId = in.nextInt() - 1;
            int weight = in.nextInt();

            Node u = nodes[uId], v = nodes[vId];
            Edge e = new Edge(u, v, weight);
            u.edges.add(e); v.edges.add(e);
            allEdges.add(e);
        }
        in.close();
   
        int cutWeightR = 0;
        int totalWeightR = 0;
        System.out.printf("R output:\n");
        for (int i = 0; i < 100; i++) {
            reset();
            R();
            int currentCutWeight = cutSum();
            cutWeightR = Math.max(cutWeightR, currentCutWeight);    
            totalWeightR += currentCutWeight;
            System.out.printf("%d\n", currentCutWeight);
        }
        int averageWeightR = totalWeightR / 100;
        
        int cutWeightS = 0;
        int totalWeightS = 0;
        System.out.printf("S output:\n");
        for (int i = 0; i < 100; i++) {
            reset();
            S();
            int currentCutWeight = cutSum();
            cutWeightS = Math.max(cutWeightS, currentCutWeight);
            totalWeightS += currentCutWeight;
            System.out.printf("%d\n", currentCutWeight);
        }
        int averageWeightS = totalWeightS / 100;
        int cutWeightRS = 0;
        int totalWeightRS = 0;
        System.out.printf("RS output:\n");
        for (int i = 0; i < 100; i++) {
            reset();
            RS();
            int currentCutWeight = cutSum();
            cutWeightRS = Math.max(cutWeightRS, currentCutWeight);
            totalWeightRS += currentCutWeight;

            System.out.printf("%d\n", currentCutWeight);
        }
        int averageWeightRS = totalWeightRS / 100;


        System.out.printf("cutWeightR = %d\n", cutWeightR);
        System.out.printf("cutWeightS = %d\n", cutWeightS);
        System.out.printf("cutWeightRS = %d\n", cutWeightRS);
        System.out.printf("averageWeightR = %d\n", averageWeightR);
        System.out.printf("averageWeightS = %d\n", averageWeightS);
        System.out.printf("averageWeightRS = %d\n", averageWeightRS);
    }
}