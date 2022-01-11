package main.java.snapshots;

import main.java.province_construction.Province;

import java.util.ArrayList;
import java.util.List;

/**
 *  This file contains the implementation for the OriginatorProvince Class.
 *  Responsibility: This file is responsible for creating new MementoProvince Object,
 *  by a given Province Object, and converting the List of MementoProvince into
 *  a List of Province.
 */

public class OriginatorProvince {
    private Province p;

    /**
     * Creates MementoProvince Object given a ProvinceAssembler Object
     * @return new Memento Province Object
     */
    public MementoProvince createMementoProvinces(){
        return new MementoProvince(p);
    }

    /**
     * Given a MementoProvince sets an Object given a Province Object.
     * @return new MementoProvince Object.
     */

    public List<Province> setListOfMementoProvinces(List<MementoProvince> mementoProvinceList) {
        List<Province> ListOfP = new ArrayList<>();
        for (MementoProvince mp: mementoProvinceList){
            p = mp.getProvince();
            ListOfP.add(p);
        }
        return ListOfP;
    }

    public void setProvince(Province p){
        this.p = p;
    }
}
