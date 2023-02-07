import java.util.*;
class NodeComparator implements Comparator<Node>{
              
            // Overriding compare()method of Comparator 
                        // for descending order of cgpa
            public int compare(Node s1, Node s2) {
                if (s1.getAstar() > s2.getAstar())
                    return 1;
                else if (s2.getAstar() > s1.getAstar())
                    return -1;
                if (s1.getSumpaths() > s2.getSumpaths())
                    return 1;
                else if (s2.getSumpaths() > s1.getSumpaths())
                    return -1;
                return 0;
            }
        }

public class Node {
    private int[] left;
    private int[] right;
    private Node father = null;
    private int sumPaths;
    private int heuristic;
    private int astarv;
    boolean visited;
    boolean LtR = false;
    static boolean goal = false;
    private ArrayList<Node> childs = new ArrayList<>();
    private Queue<String> q = new LinkedList<>();
    private Queue<String> q1 = new LinkedList<>();
    public static PriorityQueue<Node> ΝodesToExpand;
    public static PriorityQueue<Node> closed;
    public static ArrayList<Node> ExpandedNodes = new ArrayList<>();
    static Node winner = null;
    
    
    public Node(int[] l,int [] r){
        left = l;
        right = r;
    }
    
    public Node(int[] l,int[] r,Node f,int cost){
        left = l;
        right = r;
        father = f;
        sumPaths = cost;
    }
    
     public void addChildsLtR(){//When we cross bridge from left to right
        for (int i=0;i<left.length-1;i++){
            if (left[i]!=0){
                int j=1;
                while (i+j<left.length){
                    if (left[i+j]!=0)
                        q.add(left[i]+","+i+","+left[i+j]+","+(i+j));
                    j+=1;
                }
            }
        }
        int cost;
        for(String s : q) { 
            cost = sumPaths;
            int[] leftChildAr = left.clone();
            int[] rightChildAr = right.clone();
            
            String[] nums = s.split(",");
            String num1 = nums[0];
            String pos1 = nums[1];
            String num2 = nums[2];
            String pos2 = nums[3];
            rightChildAr[Integer.parseInt(pos1)] = Integer.parseInt(num1);
            rightChildAr[Integer.parseInt(pos2)] = Integer.parseInt(num2);
            leftChildAr[Integer.parseInt(pos1)] = 0;
            leftChildAr[Integer.parseInt(pos2)] = 0;
            if (Integer.parseInt(num1)>Integer.parseInt(num2)){
                cost = cost + Integer.parseInt(num1);
            }else if (Integer.parseInt(num1)<Integer.parseInt(num2)){
                cost = cost + Integer.parseInt(num2);
            }
            
            MainAstar.nodesCreated+=1;
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = true;
            n.setAstar();
            childs.add(n);
            ΝodesToExpand.add(n);
            checkIfGoal(n);
            if (goal==true){
                winner = n;
                return;
            }
        }
        q.clear();
        for (int i=0;i<left.length;i++){
            if (left[i]!=0)
                q1.add(left[i]+","+i);
        }
        for(String s : q1){
            cost = sumPaths;
            int[] leftChildAr = left.clone();
            int[] rightChildAr = right.clone();
            String[] nums = s.split(",");
            String num1 = nums[0];
            String pos1 = nums[1];
            rightChildAr[Integer.parseInt(pos1)] = Integer.parseInt(num1);

            leftChildAr[Integer.parseInt(pos1)] = 0;
            cost = cost + Integer.parseInt(num1);
            MainAstar.nodesCreated+=1;
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = true;
            n.setAstar();
            childs.add(n);
            ΝodesToExpand.add(n);
            checkIfGoal(n);
            if (goal==true){
                winner = n;
                return;
            }
        }
        q1.clear();
    }
    public void addChildRtL(){
        
        for (int i=0;i<right.length;i++){
            if (right[i]!=0)
                q1.add(right[i]+","+i);
        }
        int cost;
        for(String s : q1){
            cost = sumPaths;
            int[] leftChildAr = left.clone();
            int[] rightChildAr = right.clone();
            String[] nums = s.split(",");
            String num1 = nums[0];
            String pos1 = nums[1];
            leftChildAr[Integer.parseInt(pos1)] = Integer.parseInt(num1);

            rightChildAr[Integer.parseInt(pos1)] = 0;
            cost = cost + Integer.parseInt(num1);
            MainAstar.nodesCreated+=1;
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = false;
            n.setAstar();
            childs.add(n);
            ΝodesToExpand.add(n);
            checkIfGoal(n);
            if (goal==true){
                winner = n;
                return;
            }
        }
        q1.clear();
        for (int i=0;i<right.length-1;i++){
            if (right[i]!=0){
                int j=1;
                while (i+j<right.length){
                    if (right[i+j]!=0)
                        q.add(right[i]+","+i+","+right[i+j]+","+(i+j));
                    j+=1;
                }
            }
        }
        
        for(String s : q) { 
            cost = sumPaths;
            int[] leftChildAr = left.clone();
            int[] rightChildAr = right.clone();
            
            String[] nums = s.split(",");
            String num1 = nums[0];
            String pos1 = nums[1];
            String num2 = nums[2];
            String pos2 = nums[3];
            leftChildAr[Integer.parseInt(pos1)] = Integer.parseInt(num1);
            leftChildAr[Integer.parseInt(pos2)] = Integer.parseInt(num2);
            if (Integer.parseInt(num1)==0 || Integer.parseInt(num2)==0 ){
                return;
            }
            rightChildAr[Integer.parseInt(pos1)] = 0;
            rightChildAr[Integer.parseInt(pos2)] = 0;
            if (Integer.parseInt(num1)>Integer.parseInt(num2)){
                cost = cost + Integer.parseInt(num1);
            }else if (Integer.parseInt(num1)<Integer.parseInt(num2)){
                cost = cost + Integer.parseInt(num2);
            }
            
            MainAstar.nodesCreated+=1;
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = false;
            n.setAstar();
            childs.add(n);
            ΝodesToExpand.add(n);
            checkIfGoal(n);
            if (goal==true){
                winner = n;
                return;
            }
                
        }
        q.clear();
        
    }
    public Node returnNextNode(){
        MainAstar.nodesVisited+=1;
        return ΝodesToExpand.poll();    
    }
    public int[] getLeft(){
        return left;
    }
    public int[] getRight(){
        return right;
    }
    public int getSumpaths(){
        return sumPaths;
    }
    
    public void checkIfGoal(Node n){
        for (int i=0;i<left.length;i++){
            if (left[i]!=0){
                return;
            }
        }
        if  (astarv <= MainAstar.cstar)
            goal=true;
    }
    public Node getFather(){
        return father;
    }
    public void setAstar(){
        Heuristic h = new Heuristic();
        int nums = 0;
            for (int j=0;j<=left.length-1;j++){
                if (left[j]!=0){
                    nums+=1;
                }
            }
            int arr[] = new int[nums];
            int pos = 0;
            for (int j=0;j<=left.length-1;j++){
                 if (left[j]!=0){

                    arr[pos] = left[j];
                    pos+=1;
                }
            }
            heuristic = h.findAndPrintMinEstimate(arr);
            astarv = heuristic + sumPaths;
    }
    public int getAstar(){
        return astarv;
    }

    
}
