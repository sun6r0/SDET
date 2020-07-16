package restProc;

import pojo.AllFacts;
import pojo.Fact;
import pojo.Name;
import pojo.User;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class RestProcessor {

    public void checkStatusCode() {
        given().when().get("https://cat-fact.herokuapp.com/facts").then().statusCode(200);
    }

    public AllFacts getAllFacts(){
        return get("https://cat-fact.herokuapp.com/facts").as(AllFacts.class);
    }

    public String[] getUserNameWithMaxFacts (AllFacts allFacts, String id) {
        User user;
        Name myName;
        String firstName = null;
        String lastName = null;
        for (Fact fact : allFacts.getFacts()) {
            user = fact.getUser();
            if (user != null) {
                myName = user.getNameById(id);
                if (myName != null) {
                    firstName = user.getName().getFirst();
                    lastName = user.getName().getLast();
                }
            }
        }
        return new String[]{firstName, lastName};
    }

    public String getUserIdWithMaxFacts(AllFacts allFacts) {
        String result = "";
        result = allFacts.getFacts().stream()
                .map(Fact::getUser)
                .filter(Objects::nonNull)
                .map(User::get_id)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(map->map.getKey())
                .get();
        return result;
    }



}
