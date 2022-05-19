package com.tom.mainGui;

public class Canton {
    public String name;
    public double taxRate;

    public Canton(String initialName, double initialTaxRate){
        name = initialName;
        taxRate = initialTaxRate;
    }

    static Canton aargau = new Canton("Aargau", 1);
    static Canton appenzellAu = new Canton("Appenzell Ausserrhoden",2);
    static Canton appenzellIn = new Canton("Appenzell Innerrhoden",3);
    static Canton baselLand = new Canton("Basel-Landschaft",4);
    static Canton baselStadt = new Canton("Basel-Stadt",5);
    static Canton bernen = new Canton("Berne",6);
    static Canton fribourg = new Canton("Fribourg",7);
    static Canton geneva = new Canton("Geneva",8);
    static Canton glarus = new Canton("Glarus",9);
    static Canton graubunden = new Canton("Graubünden,",10);
    static Canton jura = new Canton("Jura",11);
    static Canton lucerne = new Canton("Lucerne",12);
    static Canton neuchatel = new Canton("Neuchâtel",13);
    static Canton nidwalden = new Canton("Nidwalden",14);
    static Canton obwalden = new Canton("Obwalden",15);
    static Canton schaffhausen = new Canton("Schaffhausen",16);
    static Canton schwyz = new Canton("Schwyz",17);
    static Canton solothurn = new Canton("Solothurn",18);
    static Canton stGallen = new Canton("St. Gallen",19);
    static Canton thurgau = new Canton("Thurgau",20);
    static Canton ticino = new Canton("Ticino",22);
    static Canton uri = new Canton("Uri",23);
    static Canton valais = new Canton("Valais",24);
    static Canton vaud = new Canton("Vaud",25);
    static Canton zug = new Canton("Zug",26);
    static Canton zurich = new Canton("Zürich",27);

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
