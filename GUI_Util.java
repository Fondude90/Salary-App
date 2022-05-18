package com.tom.mainGui;

public class GUI_Util {

    // method to calculate the salary, input of pre-tax salary and pension values
    static double calcSalary(double preTaxSalary, double pensionValue, double churchTax) {
        System.out.println("calcSalary called");

        double postTaxSal;

        // convert the input values to percentages



        GUI.ahvValue = (GUI.ahvRate / 100) * preTaxSalary;
        GUI.alvValue = (GUI.alvRate / 100) * preTaxSalary;
        GUI.taxValue = (GUI.taxRate / 100) * preTaxSalary;
        GUI.churchTaxValue = (churchTax / 100) * preTaxSalary;
        GUI.pensionVal = (pensionValue / 100) * preTaxSalary;

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - GUI.ahvValue - - GUI.alvValue - GUI.taxValue - GUI.churchTaxValue -GUI. pensionVal;

        return Double.parseDouble(GUI.df.format(postTaxSal));
    }


}
