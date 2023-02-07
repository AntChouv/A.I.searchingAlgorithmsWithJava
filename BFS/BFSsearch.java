
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ntons
 */
public class BFSsearch {
    private Node root;
    public static ArrayList<Node> open = new ArrayList<>();
    BFSsearch(Node root){
        this.root = root;
    }
    public void searchMachine(){
        open.add(root);
        root.addChildsLtR();
        Heuristic h = new Heuristic(); 
        root.setEstimate(h.findAndPrintMin(root.getLeft()));
        Node next;
        next = root.returnNextNode();
        while (Node.goal==false){
            if (next.LtR==true){
                next.addChildRtL();

            }else{
                next.addChildsLtR();
                
            }
            next = next.returnNextNode();
            
            next.checkIfGoal();
        }
        Node.winner=next;

    }
    public static ArrayList<Node> getOpened(){
        return open;
    }  
}
