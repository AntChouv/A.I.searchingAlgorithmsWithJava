
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ntons
 */
public class IDS {
    public static int searchNodesCounter;
    private int[] left;
    private int[] right;
    
    
    public void idsAlgorithm(ArrayList<Node> arr){
        List<Node> nodeList = arr;
        Node root = nodeList.get(0);
        
        int depth = 0;
        while(depth<=MainIDS.levelExplored && Node.goal==false){
            dfs(root,depth);
            depth++;
        }
    }
    
    private void dfs(Node root,int depth){
        Stack<Node> myStack = new Stack<>();
        root.setDepthLevel(0);
        myStack.push(root);
        
        while (myStack.isEmpty() == false){
            IDS.searchNodesCounter+=1;
            Node currentNode = myStack.pop();
            //System.out.println("Left: "+Arrays.toString(currentNode.getLeft()));
            //System.out.println("Right: "+Arrays.toString(currentNode.getRight()));
            //System.out.println(currentNode.sumPaths);
            //System.out.println("________________________________");
            currentNode.checkIfGoal();
            if(Node.goal==true){
                Node.winner = currentNode;
                return;
            }
            if (currentNode.getDepthLevel()>= depth){
                continue;
            }
            
            for (int i=currentNode.getChilds().size()-1;i>=0;i--){
                Node n = currentNode.getChilds().get(i);
                myStack.push(n);
                n.setDepthLevel(currentNode.getDepthLevel()+1);
            }
        }
    }
}
