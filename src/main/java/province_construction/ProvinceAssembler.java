package main.java.province_construction;
import java.util.ArrayList;
/**
 * This file contains the implementation for the ProvincesAssembler Class.
 * Responsibility: This class is responsible for the creation of the province and
 * assembles all the other class into a Province with the necessary attributes.
 **/

public class ProvinceAssembler {
    private ProvinceBuilder provinceBuilder;

    /**
     * Constructor
     * @param provinceBuilder: Represents all of built components for a province.
     * */
    public ProvinceAssembler(ProvinceBuilder provinceBuilder) {
        this.provinceBuilder = provinceBuilder;
    }

    /**
     * Constructor
     * */

    public ProvinceAssembler() {
    }

    /**
     * Gets the Ai Province
     * Returns the Ai Province Object
     */
    public Province getAiProvince(){
        return this.provinceBuilder.getAiProvince();
    }

    /**
     * Gets the User Province
     *  Returns the User Province Object
     */
    public Province getUserProvince(){
        return this.provinceBuilder.getUserProvince();
    }
    /**
     * Assembles all the components of a province and creates an Ai Province
     */
    public void makeAiProvince(){
        this.provinceBuilder.buildAiProvinceName();
        this.provinceBuilder.buildProvinceGold();
        this.provinceBuilder.buildProvinceCivilians();
        this.provinceBuilder.buildProvinceSoldiers();
        this.provinceBuilder.buildProvinceFood();
    }
    /**
     * Assembles all the components of a province and creates a User Province
     */
    public void makeUserProvince(String name){
        this.provinceBuilder.buildUserProvinceName(name);
        this.provinceBuilder.buildProvinceGold();
        this.provinceBuilder.buildProvinceCivilians();
        this.provinceBuilder.buildProvinceSoldiers();
        this.provinceBuilder.buildProvinceFood();
    }

    /**
     * Creates four Ai Provinces and returns them through an arraylist
     * Array List of the 4 Ai Provinces
     */
    public ArrayList<Province> create(){
        ProvinceBuilder provinceBuilder = new ProvinceBuilder();
        ProvinceBuilder provinceBuilder2 = new ProvinceBuilder();
        ProvinceBuilder provinceBuilder3 = new ProvinceBuilder();
        ProvinceBuilder provinceBuilder4 = new ProvinceBuilder();

        ProvinceAssembler provinceAssembler  =  new ProvinceAssembler(provinceBuilder);
        ProvinceAssembler provinceAssembler2  =  new ProvinceAssembler(provinceBuilder2);
        ProvinceAssembler provinceAssembler3  =  new ProvinceAssembler(provinceBuilder3);
        ProvinceAssembler provinceAssembler4  =  new ProvinceAssembler(provinceBuilder4);

        provinceAssembler.makeAiProvince();
        provinceAssembler2.makeAiProvince();
        provinceAssembler3.makeAiProvince();
        provinceAssembler4.makeAiProvince();

        Province p1 = provinceAssembler.getAiProvince();
        Province p2 = provinceAssembler2.getAiProvince();
        Province p3 = provinceAssembler3.getAiProvince();
        Province p4 = provinceAssembler4.getAiProvince();

        ArrayList<Province> listAiProvinces = new ArrayList<>();
        listAiProvinces.add(p1);
        listAiProvinces.add(p2);
        listAiProvinces.add(p3);
        listAiProvinces.add(p4);

        return listAiProvinces;
    }
}

