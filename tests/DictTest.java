import org.junit.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DictTest {
    public Dict _dict;

    @Before
    public void init(){
        _dict = Dict.getInstance();
    }

    @Test
    public void testGetKey() throws Exception {
        Assert.assertEquals("404",_dict.getKey("fet"));
        Assert.assertEquals("404",_dict.getKey("   Fet  "));
        Assert.assertEquals("404",_dict.getKey("f\"et  "));
        Assert.assertEquals("404",_dict.getKey("  f-et"  ));
        Assert.assertEquals("404404",_dict.getKey("  feTFet  "));
    }

    @Test
    public void testInitDict() throws Exception {
        Assert.assertEquals(false,_dict.initDict(""));
        Assert.assertEquals(true,_dict.initDict("dictionary.txt"));

    }

    @Test
    public void testGetWords() throws Exception {
        _dict.initDict("");
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("fort");
        temp.add("Torf");
        Assert.assertEquals(temp,_dict.getWords("4824"));
        ArrayList<String> temp2 = new ArrayList<String>();
        temp2.add("Tor");
        Assert.assertEquals(temp2,_dict.getWords("482"));

    }
}
