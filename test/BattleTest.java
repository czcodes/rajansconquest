/*

  This file tests the Battle file and makes sure that all methods are working.

  */
import main.java.usecases.Battle;

import main.java.province_construction.ProvinceAssembler;
import main.java.province_construction.Province;
import main.java.province_construction.ProvinceBuilder;
import org.junit.*;

import java.util.*;

public class BattleTest {
    private Battle b1;
    private ProvinceAssembler p;

    @Before
    public void setUp() {
        b1 = new Battle();
        ProvinceBuilder p1 = new ProvinceBuilder();
        p = new ProvinceAssembler(p1);
    }

    /** Checks that startsBattle correctly returns the name of the winning province*/
    @Test
    public void testStartsBattle(){
        Province opp = p.create().get(0);
        p.makeUserProvince("Ontario");
        Province user = p.getUserProvince();
        String result = b1.startsBattle(user, opp);
        // making sure it matches one of the names
        String uiName = opp.getAiProvinceName();
        String userName = user.getUserProvinceName();
        assert (Objects.equals(result, uiName) | Objects.equals(result, userName));
    }
}