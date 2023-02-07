import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MainAstar {
    public static int cstar;
    public static int nodesVisited;
    public static int nodesCreated;
    public static void main(String[] args) {
         System.out.println("Give size");
        Scanner sc = new Scanner(System.in);
        int siz = sc.nextInt();
        int[] left;
        int[] right;
        left = new int[siz];
        right = new int[siz];
        for (int i=0;i<siz;i++){
            System.out.println("Give time for person number "+(i+1)+" : ");
            left[i] = sc.nextInt();
        }
        for (int i=0;i<siz;i++){
            right[i] = 0;
        }
        Heuristic h = new Heuristic();
        cstar = h.findAndPrintMinEstimate(left);
        System.out.println("Η πιο σύντομη διαδρομή είναι "+cstar);
        Node.ΝodesToExpand = new  PriorityQueue<>(new NodeComparator());
        Node.closed = new  PriorityQueue<>(new NodeComparator());
        AstarSearch ucs = new AstarSearch(left,right);
        ucs.findOptimal();
        Node n = Node.winner;
        ArrayList<Node> wn = new ArrayList<>();
        wn.add(n);
        while (n.getFather()!=null){
            n = n.getFather();
            wn.add(n);
        }
        System.out.println("________________________________");
        for (int i=wn.size()-1;i>=0;i--){
            System.out.println(Arrays.toString(wn.get(i).getLeft())+"|----------------------|"+Arrays.toString(wn.get(i).getRight()));
            System.out.println(wn.get(i).getSumpaths());
        };
        System.out.println(nodesCreated +" Created");
        System.out.println(nodesVisited +" Visited");
        System.out.println("________________________________");
    }
    
}