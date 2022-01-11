import main.java.province_construction.Province;
import org.junit.*;

/**
 *
 * This file tests the ProvinceConstruction package and makes sure that all methods are working.
 *
 * */

public class ProvinceTest {

    /*** Tests that the newly made province is not dead ***/
    @Test
    public void testNotDeath(){
        Province p = new Province();
        p.setProvinceFood(100);
        p.setProvinceCivilians(200);
        p.setProvinceGold(100);
        p.setProvinceSoldiers(150);
        assert (!p.isDeath());

    }
    /*** Test that the province with a 0 parameter is dead***/
    @Test
    public void testDeath(){
        Province p = new Province();
        p.setProvinceFood(0);
        p.setProvinceCivilians(0);
        p.setProvinceGold(0);
        p.setProvinceSoldiers(0);
        assert (p.isDeath());
    }
}
