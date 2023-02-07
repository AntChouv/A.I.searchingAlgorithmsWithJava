
import java.util.*;

public class Node {
    private int[] left;
    private int[] right;
    private Node father = null;
    private int depthLevel;
    int sumPaths;
    boolean LtR = false;
    static boolean goal = false;
    private ArrayList<Node> childs = new ArrayList<>();
    private Queue<String> q = new LinkedList<>();
    private Queue<String> q1 = new LinkedList<>();
    public static ArrayList<Node> ΝodesToExpand = new ArrayList<>();
    static Node winner = null;
    
    public Node(int[] l,int [] r){
        left = l;
        right = r;
        this.depthLevel = 0;
    }
    
    public Node(int[] l,int[] r,Node f,int cost){
        left = l;
        right = r;
        father = f;
        sumPaths = cost;
        this.depthLevel = 0;
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
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = true;
            childs.add(n);
            ΝodesToExpand.add(n);
            
        }
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
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = true;
            childs.add(n);
            ΝodesToExpand.add(n);
        }
        
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
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = false;
            childs.add(n);
            ΝodesToExpand.add(n);
        }
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
            Node n = new Node(leftChildAr,rightChildAr,this,cost);
            n.LtR = false;
            childs.add(n);
            ΝodesToExpand.add(n);
            
        }
    }
    public Node returnNextNode(){
        int min = ΝodesToExpand.get(ΝodesToExpand.size()-1).sumPaths;
        int minplace = ΝodesToExpand.size()-1;
        for (int i=0;i<ΝodesToExpand.size();i++){
            if ((min>ΝodesToExpand.get(i).sumPaths)){
                min = ΝodesToExpand.get(i).sumPaths;
                minplace = i;
            }        
        }
        
        return ΝodesToExpand.get(minplace);    
    }
    public int[] getLeft(){
        return left;
    }
    public int[] getRight(){
        return right;
    }
    
    public void checkIfGoal(){
        for (int i=0;i<left.length;i++){
            if (left[i]!=0){
                return;
            }
        }
        goal=true;
    }
    public Node getFather(){
        return father;
    }
    public void setDepthLevel(int d){
        depthLevel = d;
    }
    
    public int getDepthLevel(){
        return depthLevel;
    }
    public List<Node> getChilds(){
        return childs;
    }   
}
