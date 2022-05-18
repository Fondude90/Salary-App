package com.tom.mainGui;

public class Canton {
    public String name;
    public double taxRate;

    public Canton(String initialName){
        name = initialName;
        taxRate = 0;
    }

    public double getTaxRate() {

        return taxRate;
    }

    public String getName() {

        return name;
    }

    public void updateCantonRate(double newRate){

        taxRate = newRate;
    }
    public void updateCantonName(String newName){

        name = newName;
    }

    public void printObject(){
        System.out.println("Canton: " + name + ", tax rate: " + taxRate);
    }
}
