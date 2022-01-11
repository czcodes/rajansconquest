package main.java.province_construction;
/**
 * This file contains the implementation for the
 * ProvincesLayout which is them implemented by Province.
 * Responsibility: Contains setter of methods that are over-ridden by the Province class
 **/

public interface ProvinceLayout {
    /*
     * Sets the name of the User Province
     */
     void setUserProvinceName(String provinceName);
    /*
    * Sets the name of the Ai Province
    */
     void setAiProvinceName(String provinceName);
    /*
     * Sets the Gold amount for the Province
     */
     void setProvinceGold(int provinceGold);

    /*
     * Sets the Civilian amount for the Province
     */
     void setProvinceCivilians(int provinceCivilians);

    /*
     * Sets the Soldier amount for the Province
     */
     void setProvinceSoldiers(int provinceSoldiers);

    /*
     * Sets the Food amount for the Province
     */
     void setProvinceFood(int provinceFood);

}
