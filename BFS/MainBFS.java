
import java.util.Arrays;
import java.util.Scanner;

public class MainBFS {
    static int nodesVisited;
    static int nodesCreated;
    public static void main(String[] args) {
        System.out.println("Give size");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] left;
        int[] right;
        left = new int[n];
        right = new int[n];
        for (int i=0;i<n;i++){
            System.out.println("Give time for person number "+(i+1)+" : ");
            left[i] = sc.nextInt();
        }
        for (int i=0;i<n;i++){
            right[i] = 0;
        }
        
        Node node = new Node(left,right);
        BFSsearch bfs = new BFSsearch(node);
        bfs.searchMachine();
        System.out.println("_____________________________");
        for (int i=0;i<BFSsearch.open.size();i++){
            System.out.println(Arrays.toString(BFSsearch.open.get(i).getLeft())+"|----------------------|"+Arrays.toString(BFSsearch.open.get(i).getRight()));
            System.out.println("Estimation to reach goal : "+BFSsearch.open.get(i).getEstimate());
            System.out.println("Sum of paths : "+BFSsearch.open.get(i).sumPaths);
        }
        System.out.println("Nodes visited : "+nodesVisited);
        System.out.println("Nodes created : "+nodesCreated);
        System.out.println("_____________________________");
        
        
        
    }
    
}
