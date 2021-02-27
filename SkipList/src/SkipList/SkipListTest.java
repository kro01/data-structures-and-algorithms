package SkipList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
//import org.testng.annotations.Test;

public class SkipListTest {
    private SkipList list,list2;
    @Test
    public void SkipListTest(){
        list2 = new SkipList(4);
        Assert.assertNotNull("List object not created.",list2);
    }

    //@BeforeAll
    public void init(){
        int[] arr = {-1,9,0,8,1,1,7,1,2,0,6,1,1,5,1,2,0,4,1,0,2,1,1,1,1,3};
        list = new SkipList(arr);
        list.testMode = true;
    }

    @Test
    public void SearchTest(){
        this.init();
        //String str = "1 2 5 \n2 4 \n4 5 \n5 6 7 \n6 7 \n7 8 9 \n8 9 \n9";
        Assert.assertFalse("Find not existing value 3",list2.Search(3));
    }
    @Test
    public void addBeginingTest() {
        this.init();
        String str = "0 1 2 5 \n1 2 \n2 4 5 \n4 5 \n5 6 7 9 \n6 7 \n7 8 9 \n8 9 \n9";
        list.add(0);
        Assert.assertEquals("Bed add in beginig",str,list.show());
    }
}
