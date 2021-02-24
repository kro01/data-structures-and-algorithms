package List;

import java.lang.reflect.Array;

class List {
    private int[] arr;
    //private int capacity;
    private int last;

    public List(int capacity) {
        //this.capacity = capacity;
        arr =new int[capacity];
        last = 0;
    }
    public List(){
        this(1);
    }

    public void add(int data){
        if(last == arr.length){
            int[] novo =new int[2*arr.length];
            for(int i=0;i< arr.length;i++) {
                novo[i] = arr[i];
            }
            arr = novo;
            arr[last] = data;
            last++;
        }else{
            arr[last] = data;
            last++;
        }
    }

    public void set(int position,int value){
        arr[position] = value;
    }
    public int get(int position){
        return arr[position];
    }
    public int getCapacity(){
        return arr.length;
    }
    public void show(){
        for(int i=0;i<last;i++) {
            System.out.print(arr[i]);
            System.out.print(" ");
        }
    }

}
