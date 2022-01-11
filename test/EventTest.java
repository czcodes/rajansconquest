import  main.java.game_manager.Events;
import org.junit.Test;

import java.util.*;

public class EventTest {
    /*Testing that a random event is actually in the predefined map of events*/
    @Test
    public void testEvent() {
        /*First I need to recreate the map*/
        final Map<String, List<Integer>> map = new HashMap<>();
        map.put("A neighboring king is fielding suitors for his daughter, who is particularly taken with your second " +
                "son; will you make the match?", Arrays.asList(80, 20, 30, -50, 0, 0, 0, 0));

        map.put("Morale is running low in the province. A party would reinvigorate the civilians, and potentially " +
                "attract more people to your cause. Will you do it?", Arrays.asList(-40, 20, 30, -30, 0, 0, -20, -10));

        map.put("Your clergymen are looking for more financial support in exchange for some valuable military " +
                "endorsement. Will you accept their deal?", Arrays.asList(-40, 10, 40, 20, 0, -10, -25, 0));

        map.put("Motivated by attacks on the province, your military commander proposes a training program to " +
                        "prepare your civilians for future conflicts. Will you approve the program?",
                Arrays.asList(-30, 20, 60, -30, 0, -10, -20, 30));

        map.put("A fiery dragon has made its home in your mountains, and is setting crops and livestock ablaze. " +
                "Will you slay the dragon?", Arrays.asList(-25, 40, -80, 40, -20, -20, 0, -20));

        map.put("Your first-born son, ambitious and jealous after a lifetime living in your shadow, is revealed " +
                "to be spying on you for your enemies. The punishment for his treachery is death. Will you carry " +
                "it out?", Arrays.asList(40, -20, -20, 30, 10, 10, 0, 10));

        map.put("A band of thugs is wreaking havoc on local villages. Your advisors suggest assembling a military " +
                        "unit to eliminate the threat with some of your current soldiers. Will you do it?",
                Arrays.asList(-10, 20, -20, 20, -20, -10, 0, -10));

        map.put("Influenza is spreading across the province; the quarantined people are becoming restless, and " +
                "are likely to attempt an escape soon. Your only option is to kill them before they try " +
                "to break out. Will you do it?", Arrays.asList(-10, -10, -10, 20, -10, -50, -20, 30));

        map.put("A local town has accused a woman of witchcraft, and wishes to burn her at the stake. Failing to " +
                        "give your approval could be controversial; will you approve the execution?",
                Arrays.asList(20, -1, 20, 20, -10, -10, -10, -10));

        map.put("Due to food shortages in one of your villages, the people have begun attacking your other villages " +
                        "in order to sustain themselves. Will you eliminate them?",
                Arrays.asList(-10, 20, -20, 40, 10, -20, 0, -30));

        map.put("A tornado has swept across the land, decimating homes in your province. Will you pay to rebuild " +
                "the destroyed property?", Arrays.asList(-30, 20, 20, 40, 20, -30, -10, -10));

        map.put("A charismatic religious leader is beginning to attract followers in your province, and you fear he " +
                        "may plan to turn your people against you? Will you assassinate him?",
                Arrays.asList(-40, 20, 20, 20, -20, -40, -20, -30));

        map.put("Local schoolteachers are pushing anti-monarchy curricula to children which could threaten " +
                        "your position. You may defund them, thereby forcing them to get in line; will you?",
                Arrays.asList(50, -10, 0, 0, 0, -20, -20, 20));

        map.put("An infestation of crickets is beginning to damage food supplies. Hiring professionals " +
                        "to exterminate them, however, could be quite expensive. Will you hire them?",
                Arrays.asList(-50, 20, 0, 40, -20, -20, -10, -40));

        map.put("One of your most beloved generals, Howard, falls in battle. Will you commission a statue " +
                "in his honor?", Arrays.asList(-30, -10, 30, 20, 0, -10, -20, 30));

        map.put("You discover that your second son has impregnated a young girl, which could cause a massive " +
                "scandal. Your council urges you to order her assassination to avoid the ensuing scandal: " +
                "will you approve it?", Arrays.asList(-30, -10, 0, 0, 0, -30, -10, -20));

        map.put("Your religious advisors suggest you intertwine the roots of your family tree in order to keep " +
                        "bloodlines pure, despite widespread disapproval? Will you act on their advice?",
                Arrays.asList(-100, -100, -100, -100, 0, 0, 0, 0));

        map.put("Your military leaders recommend quick raids on nearby towns to collect their resources; " +
                "will you spare twenty good men?", Arrays.asList(30, 0, -20, 40, -10, -10, 25, -10));

        map.put("Enemy territory is full of meticulous traps. While you can just risk entering blindly, you may " +
                "also put forward an infiltration team to clear the way; however, they will likely all die " +
                "in the process. Will you make the sacrifice?", Arrays.asList(40, 10, -10, 30, 20, 0, -30, 10));

        map.put("The once renowned Sir Robin deserted your army in terror, to the disgust of your people. In order " +
                        "to save face, your advisors suggest you execute him as punishment. Will you carry it out?",
                Arrays.asList(-10, 10, 30, 20, -10, 0, -40, 10));
        new Events();
        /*testEvent actually does need to be created even if it is not used or else the program will crash*/
        String ev = Events.getRandomEvent();
        assert (map.containsKey(ev));

    }

    @Test
    public void testEventGetValue() {
        new Events();
        /*testEvent actually does need to be created even if it is not used or else the program will crash*/
        String ev = Events.getRandomEvent();
        List<Integer> lst = Events.getValues(ev);
        assert (Objects.equals(lst.size(), 8));
    }
}
