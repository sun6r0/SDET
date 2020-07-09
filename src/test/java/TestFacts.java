import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

public class TestFacts {
    @Test
    public void testKasimir() {
        final String KASIMIR_ID = "58e007480aac31001185ecef";
        RestAssured.baseURI = "https://cat-fact.herokuapp.com";
        RestAssured.basePath = "/facts";
        String response = given().contentType(ContentType.JSON).log().all().get().asString();
        List<String> ids = from(response).getList("all.user._id");
        Map<String, Integer> instances = new HashMap();
        for (String id : ids) {
            if(!instances.containsKey(id)) {
                instances.put(id, Collections.frequency(ids, id)) ;
            }
        }
        String key = Collections.max(instances.entrySet(), Map.Entry.comparingByValue()).getKey();
        Assert.assertEquals(KASIMIR_ID, key);
    }
}

