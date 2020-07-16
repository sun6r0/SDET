import org.junit.Assert;
import org.junit.Test;
import pojo.AllFacts;

import restProc.RestProcessor;

public class TestFacts {

    @Test
    public void testFacts() {
        RestProcessor rp = new RestProcessor();
        rp.checkStatusCode();
        AllFacts allFacts = rp.getAllFacts();
        String idMax = rp.getUserIdWithMaxFacts(allFacts);
        Assert.assertEquals(rp.getUserNameWithMaxFacts(allFacts,idMax), "Kasimir Shultz");
    }

}

