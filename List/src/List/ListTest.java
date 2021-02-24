package List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class ListTest {
    @Test
    public void LIstTest(){
        List list = new List();
        Assert.assertNotNull("List object not created.",list);
    }
    //public
}
