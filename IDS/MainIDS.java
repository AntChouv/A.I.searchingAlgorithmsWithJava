
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainIDS {
    static int levelExplored = 0;
    public static void main(String[] args) {
        IDS.searchNodesCounter = 0;
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
        
        IDS idsSearchEngine = new IDS();
        
        Node n = new Node(left,right);
        Node.ΝodesToExpand.add(n);
        
        idsSearchEngine.idsAlgorithm(Node.ΝodesToExpand);
        MainIDS.levelExplored = MainIDS.levelExplored +  1;
        
        n.addChildsLtR();
        
        idsSearchEngine.idsAlgorithm(Node.ΝodesToExpand);
        ArrayList<Node> newLevelNodes = new ArrayList<>();
        while (Node.goal==false){
            int size = Node.ΝodesToExpand.size();
            for (int i=0;i<size;i++){
                if (Node.ΝodesToExpand.get(i).getChilds().isEmpty()){
                    newLevelNodes.add(Node.ΝodesToExpand.get(i));
                    if (Node.ΝodesToExpand.get(i).LtR == true){
                        Node.ΝodesToExpand.get(i).addChildRtL();
                    }else{
                        Node.ΝodesToExpand.get(i).addChildsLtR();
                    }
                }
                
            }
            MainIDS.levelExplored = MainIDS.levelExplored +  1;
            idsSearchEngine.idsAlgorithm(Node.ΝodesToExpand);
        }
        n = Node.winner;
        ArrayList<Node> wn = new ArrayList<>();
        wn.add(n);
        while (n.getFather()!=null){
            n = n.getFather();
            wn.add(n);
        }
        System.out.println("________________________________");
        for (int i=wn.size()-1;i>=0;i--){
            System.out.println(Arrays.toString(wn.get(i).getLeft())+"|----------------------|"+Arrays.toString(wn.get(i).getRight()));
            System.out.println(wn.get(i).sumPaths);
        }
        System.out.println(Node.ΝodesToExpand.size()+" Nodes created");
        System.out.println(IDS.searchNodesCounter+" Nodes visited");
        System.out.println("________________________________");
    
    }
}

