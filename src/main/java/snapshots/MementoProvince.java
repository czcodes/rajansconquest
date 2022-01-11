package main.java.snapshots;

import main.java.province_construction.Province;

/**
 *  This file contains the implementation for the MementoProvince Class.
 *  Responsibility: This file converts a given Province Object to a
 *  MementoProvince Object.
 */
public class MementoProvince {
    private final Province pA;

    /**
     * Constructor that sets the ProvinceAssembler.
     * @param pA represents a ProvinceAssembler Object.
     */
    public MementoProvince(Province pA) {
        this.pA = pA;
    }

    /**
     * Getters and Setters
     */
    public Province getProvince() {
        return this.pA;
    }

}