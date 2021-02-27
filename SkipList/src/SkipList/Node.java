package SkipList;

public class Node {
    private int value;
    public int leval;
    public Node[] next;

    public Node(int leval,int value){
        this.value = value;
        this.leval = leval;
        this.next = new Node[leval+1];
    }

    public int getLeval(){
        return leval;
    }

    public Node next(int leval){
        return this.next[leval];
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setNext(int level,Node n){
        next[level] = n;
    }

    public Node getNext(int l) {
        return next[l];
    }

    public void show(){
        System.out.print(this.getValue());
        System.out.print(" ");
        for(int i=0;i<=leval;i++) {
            System.out.print(next[i].getValue());
            System.out.print(" ");
        }
        System.out.print(this.getLeval());
        System.out.print("\n");
    }
}
