package main.java.snapshots;

import java.util.ArrayList;
import java.util.List;

/**
 *  This file contains the implementation for the CaretakerProvince Class.
 *  Responsibility: This file stores all the Memento Provinces Object
 *  which are saved and restored by the given index.
 */

public class CaretakerProvince {
    /**
     * Instance Variables:
     * listOfMementoProvinces: An ArrayList of to store the MementoProvinces Objects.
     */
    private final List<MementoProvince> listOfMementoProvinces = new ArrayList<>();

    /**
     * Adds the given MementoProvince Object to the ArrayList.
     * @param mp represents a given MementoProvince Object
     */
    public void addMementoProvince(MementoProvince mp){this.listOfMementoProvinces.add(mp);
    }


    /**
     * Getter
     */
    public List<MementoProvince> getMementoProvinceList(){
        return this.listOfMementoProvinces;
    }

}
