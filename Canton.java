package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canton {
    public String name;
    public double taxRate;
    public static ArrayList<Canton> cantonList = new ArrayList<>();
    public static JComboBox cantonField;

    public static void createCantonList() {
        cantonList.add(Canton.aargau);
        cantonList.add(Canton.appenzellAu);
        cantonList.add(Canton.appenzellIn);
        cantonList.add(Canton.baselLand);
        cantonList.add(Canton.baselStadt);
        cantonList.add(Canton.bernen);
        cantonList.add(Canton.fribourg);
        cantonList.add(Canton.geneva);
        cantonList.add(Canton.glarus);
        cantonList.add(Canton.graubunden);
        cantonList.add(Canton.jura);
        cantonList.add(Canton.lucerne);
        cantonList.add(Canton.neuchatel);
        cantonList.add(Canton.nidwalden);
        cantonList.add(Canton.obwalden);
        cantonList.add(Canton.schaffhausen);
        cantonList.add(Canton.schwyz);
        cantonList.add(Canton.solothurn);
        cantonList.add(Canton.stGallen);
        cantonList.add(Canton.ticino);
        cantonList.add(Canton.thurgau);
        cantonList.add(Canton.uri);
        cantonList.add(Canton.valais);
        cantonList.add(Canton.vaud);
        cantonList.add(Canton.zug);
        cantonList.add(Canton.zurich);


        cantonField = new JComboBox(new DefaultComboBoxModel());

        cantonField.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Canton){
                    Canton canton = (Canton) value;
                    setText(canton.getName());
                }
                return this;
            }
        } );
    }


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
