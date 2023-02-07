public class AstarSearch {
    private int[] left;
    private int[] right;
    
    AstarSearch(int[] l,int[] r){
        left = l;
        right = r;
    }
    
    public void findOptimal(){
        Node father = new Node(left,right);
        father.addChildsLtR();
        Node child = father.returnNextNode();
        while (Node.goal==false){
            if (child.LtR==false){
               child.addChildsLtR();
            }
            else if (child.LtR==true){
                child.addChildRtL();
            }
            if (Node.goal!=true)
                child = child.returnNextNode();
        }
        Node.winner = child;
    }
}
