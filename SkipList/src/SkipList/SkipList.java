package SkipList;

public class SkipList {
    public Node start;
    private int size;
    private int maxLeval;
    final private int maxSize = 3;
    final private int minSize = 2;
    public boolean testMode;

    public SkipList(int value){
        size = 1;
        start = new Node(-1,value);
        maxLeval = 0;//-1
        testMode = false;
    }

    public SkipList(int[] values){
        // leval value next[0]...
        //values[0] must be -1
        testMode = false;
        size = 1;
        maxLeval = 0;
        start = new Node(values[0],values[1]);

        Node n;
        int i = 2;
        int j;
        Node t;
        while(i < values.length) {
            size++;
            n = new Node(values[i],values[i+1]);
            if(values[i]>maxLeval){
                maxLeval = values[i];
            }
            i+=2;
            for (j = 0; j < n.getLeval()+1; j++) {
                t = start;
                for(int k=1;k<values[i+j];k++){
                    t = t.getNext(0);
                }
                n.setNext(j,t);
            }
            start = n;
            i += j;
        }

    }

    public int getSize() {
        return size;
    }

    public boolean Search(int v){
        start.show();
        if(v<start.getValue()){

            return false;
        }
        Node t;
        t = start;
        int leval = maxLeval;
        while(leval > -1) {

            while (t.getLeval() != -1 && v > t.getNext(leval).getValue()) {
                t = t.getNext(leval);
                t.show();
            }
            if(t.getLeval() != -1 && v == t.getNext(leval).getValue()){
                return true;
            }
            if(leval == -1){
                return false;
            }
            leval--;
        }
        return false;
    }

    private Node GetNode(int v){
        Node t;
        t = start;
        int leval = maxLeval;
        while(leval >= 0) {
            while (t.getLeval() >= leval && v > t.getValue()) {
                t = t.next(leval);
            }
            if(t.getLeval() >= leval && v == t.getValue()){
                return t;
            }
            leval--;
        }
        return t;
    }

    public void add(int value){
        //put it in Begining of list
        if(value < start.getValue()){
            Node s = new Node(0,start.getValue());
            s.setNext(0,start.getNext(0));
            Node t = new Node(start.getLeval(),value);
            t.setNext(0,s);
            for(int i = 1; i <= start.getLeval(); i++){
                t.setNext(i,start.getNext(i));
            }
            start = t;
            //balacing SkipList
            int leval = 0;
            finish: while(true){
                int numNodes = 1;
                t = t.getNext(0);
                while (t.getLeval() == leval) {
                    numNodes++;
                    t = t.getNext(leval);
                }
                if (numNodes > maxSize) {
                    int nodesMiddel = 0;
                    t = start;
                    while (nodesMiddel < maxSize / 2) {
                        t = t.getNext(leval);
                        nodesMiddel++;
                    }
                    Node n = new Node(leval+1, t.getNext(leval).getValue());
                    for(int i=0;i<=leval;i++){
                        n.setNext(i, t.getNext(leval).getNext(i));
                    }
                    //if we have to add new leval
                    if(start.getLeval()>=leval+1) {
                        n.setNext(leval+1,start.getNext(leval+1));
                        start.setNext(leval + 1, n);
                    }else{
                        Node z = new Node(leval+1,start.getValue());
                        for(int i=0;i<=start.getLeval();i++){
                            z.setNext(i,start.getNext(i));
                        }
                        z.setNext(leval+1,n);
                        start = z;
                        while(z.getLeval() > -1){
                            z=z.getNext(leval);
                        }
                        n.setNext(leval+1,z);
                    }
                    //n.setNext(leval+, (t.getNext(leval)).getNext(leval));
                    t.setNext(leval, n);
                    Node p = t;
                    for(int i = leval; i >= 0; i--){
                        while(p.getNext(i).getValue() != t.getNext(leval).getValue()){
                            p = p.getNext(i);
                        }
                        p.setNext(i,n);
                    }
                }else {
                    break finish;
                }
                leval++;
            }
        }
        else{
            //put it in the middle or in the end
            //find where to put it and remember previous an all levals
            int leval = maxLeval;
            Node[] previous = new Node[maxLeval];
            Node t = start;
            while (leval >-1) {
                while (t.getNext(leval).getLeval() > leval && value > t.getValue()) {
                    t = t.getNext(leval);
                }
                if (t.getNext(leval).getLeval()==0){
                    //end of SkipList
                    //break outer
                }else{
                    previous[leval] = t;
                    leval--;
                }
            }
            //put it in middle

        }
    }

    public String show(){
        StringBuilder sb = new StringBuilder();
        Node t = start;

        sb.append(t.getValue());
        sb.append(" ");
        for(int i=0;i<=t.leval;i++){
            sb.append(t.next[i].getValue());
            sb.append(" ");
        }
        sb.append("\n");
        while(t.getLeval()!=-1){
            t=t.getNext(0);
            sb.append(t.getValue());
            sb.append(" ");
            for(int i=0;i<=t.leval;i++){
                sb.append(t.next[i].getValue());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {-1,9,0,8,1,1,7,1,2,0,6,1,1,5,1,2,0,4,1,0,2,1,1,1,1,3};
        //int[] arr = {-1,42,0,40,1};
        SkipList list = new SkipList(arr);
        System.out.print(list.show());
        list.add(0);
        System.out.print(list.show());

    }
}
