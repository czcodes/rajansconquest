import  main.java.game_manager.Decisions;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Objects;
public class DecisionTest {
    /**
     *
     * This file tests the Decisions Class and makes sure that all methods are working.
     *
     * */
    private Decisions decisions;
    @Before
    public void setUp(){
        decisions = new Decisions();

    }

    /**
     * Tests to see if the decisions are the same as the ones outputted to the user
     */
    @Test
    public void testDecision() {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("Do you want to boost your food for one round?");
        lst.add("Do you want to enlist more soldiers?");
        lst.add("Do you want to increase revenue?");
        ArrayList<String> dec = decisions.getDecisionQuestions();
        assert (Objects.equals(lst.size(), 3));
        for(Object d : dec){
            assert lst.contains(d);
        }
    }
}
