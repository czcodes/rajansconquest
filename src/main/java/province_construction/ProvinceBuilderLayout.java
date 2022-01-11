package main.java.province_construction;
/**
 * This file contains the implementation for the
 * ProvinceBuilderLayout which is then implemented by ProvinceBuilder.
 * Responsibility: Contains methods which help to build the different aspects of a Province
 **/

public interface ProvinceBuilderLayout {
    /*
     *   Builds the name of the Ai Province
     */
     void buildAiProvinceName();

     /*
     * Builds the name of the User Province, name of province
     * specified by the User through the terminal
     */
     void buildUserProvinceName(String userProvinceName);

    /*
     * Builds the Gold of the Province
     */
     void buildProvinceGold();

    /*
     * Builds the Civilians of the Province
     */
     void buildProvinceCivilians();

    /*
     * Builds the Soldiers of the Province
     */
     void buildProvinceSoldiers();

    /*
     * Builds the Food of the Province
     */
     void buildProvinceFood();

    /*
     * Returns the Ai Province
     */
     Province getAiProvince();

    /*
     * Returns the User Province
     */
     Province getUserProvince();
}

