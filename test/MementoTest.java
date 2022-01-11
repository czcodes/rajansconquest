import main.java.province_construction.Province;
import main.java.province_construction.ProvinceAssembler;
import main.java.province_construction.ProvinceBuilder;

import main.java.snapshots.OriginatorProvince;
import main.java.snapshots.CaretakerProvince;
import main.java.snapshots.MementoProvince;
import org.junit.*;
import java.util.List;

/**
 *
 * This file tests the snapshot package and makes sure that all methods are working.
 *
 * */

public class MementoTest {
    private OriginatorProvince origProvince;
    private CaretakerProvince ctProvince;
    private Province testProvince;
    @Before
    public void setUp() {
        origProvince = new OriginatorProvince();
        ctProvince = new CaretakerProvince();
        ProvinceBuilder pB = new ProvinceBuilder();
        ProvinceAssembler pA = new ProvinceAssembler(pB);
        pA.makeUserProvince("Ontario");
        testProvince = pA.getUserProvince();
    }

    /**
     * Added a tester to see if the memento design pattern holds the previous state
     * of the province properly
     */
    @Test
    public void testRestoration(){
        // send the province state to the Originator
        origProvince.setProvince(testProvince);

        // Create a memento Object from the given state.
        MementoProvince mp = origProvince.createMementoProvinces();

        // send to the CareTackerProvince
        ctProvince.addMementoProvince(mp);
        List<Province> restoredProvince = origProvince.setListOfMementoProvinces(ctProvince.getMementoProvinceList());

        assert(restoredProvince.get(0).equals(testProvince));
    }
}