package main.java.province_construction;

/**
 * This file contains the implementation for the Province Class which implements ProvinceLayout, Cloneable.
 * Responsibility: The Province is responsible for representing a
 * Province Object that contains the attribute within a Province.
 **/

public class Province implements ProvinceLayout, Cloneable {
    /**
     * Instances Variables:
     * userProvinceName: represents the name of the User Province
     * aiProvinceName: represents the name of the Ai Provinces.
     * provinceGold: represents the total money that the province has.
     * provinceCivilians: represents the total number of civilians in a province.
     * provinceSolider: represents the total number of soldiers in a province.
     * provinceFood: represents the total number of food in a province.
     */

    private String userProvinceName;
    private String aiProvinceName;
    private int provinceGold;
    private int provinceCivilians;
    private int provinceSoldiers;
    private int provinceFood;
    private boolean active = true;

    /**
     * Constructor
     */
    public Province() {
    }

    /**
     * Sets the name for the User Province
     * @param provinceName: Name of the User Province
     */
    @Override
    public void setUserProvinceName(String provinceName) {
        this.userProvinceName = provinceName;
    }

    /**
     * Sets the name for the Ai province
     * @param provinceName: Name of the Ai province
     */
    @Override
    public void setAiProvinceName(String provinceName) {
        this.aiProvinceName = provinceName;
    }

    /**
     * Sets the Total Gold for the province
     * @param provinceGold: Total gold for the province
     */
    @Override
    public void setProvinceGold(int provinceGold) {
        this.provinceGold = provinceGold;
    }

    /**
     * Sets the total number of civilians for the province
     * @param provinceCivilians: Total number of civilians in the province
     */
    @Override
    public void setProvinceCivilians(int provinceCivilians) {
        this.provinceCivilians = provinceCivilians;
    }

    /**
     * Sets the total number of soldiers for the province
     * @param provinceSoldiers: Total number of soldiers in the province
     */
    @Override
    public void setProvinceSoldiers(int provinceSoldiers) {
        this.provinceSoldiers = provinceSoldiers;
    }

    /**
     * Sets the total number of food units for the province
     * @param provinceFood: Total number of food units for the province.
     */
    @Override
    public void setProvinceFood(int provinceFood) {
        this.provinceFood = provinceFood;
    }

    /**
     * Gets the name for the User province
     * @return name of the User province
     */
    public String getUserProvinceName() {
        return userProvinceName;
    }

    /**
     * Gets the name for the Ai province
     * @return name of the Ai province
     */
    public String getAiProvinceName() {
        return aiProvinceName;
    }

    /**
     * Gets the Gold for the Province
     * @return Total Gold for the province.
     */
    public int getProvinceGold() {
        return provinceGold;
    }

    /**
     * Gets the totals number of Civilians in the province
     * @return number of civilians in the province
     */
    public int getProvinceCivilians() {
        return provinceCivilians;
    }

    /**
     * Gets the total number of Soldiers in the province
     * @return number of Soldiers in the province
     */
    public int getProvinceSoldiers() {
        return provinceSoldiers;
    }

    /**
     * Gets the total number of food units in the province
     * @return number of food units in the province
     */
    public int getProvinceFood() {
        return provinceFood;
    }

    /**
     * Checks to see whether the Province is alive or not
     * @return True or False whether the province is alive or dead
     */
    public boolean getStatus(){
        return active;
    }

    /**
     * Returns the Gold or Civilians corresponding to the Province based on the
     * user choice
     * @param choice : Choice made by the user
     * @return Gold amount or Civilian count for the Province.
     */
    public int returnMaximumValue(String choice){
        if (choice.equals("1")){
            return getProvinceGold();
        }
        else{
            return getProvinceCivilians();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    /**
     * If the value of any attribute dips to 0 or below, the province is announced dead
     * @return True if Gold, Civilians, Soldiers or Food dips to 0 or below.
     */
    public boolean isDeath(){
        //TODO: Change it so that province wont commit suicide after no gold
        return getProvinceCivilians() <= 0 || getProvinceSoldiers() < 0 || getProvinceFood() < 0;
    }

    /**
     * Announces the province dead.
     */
    public void die(){
        this.active = false;
    }
}
