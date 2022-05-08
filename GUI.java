package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GUI  {
    private JLabel calculateButtonLabel;
    private JLabel postTaxField;
    private JLabel mthlTaxField;
    private JTextField salaryField;
    private JTextField pensionField;

    DecimalFormat df = new DecimalFormat("#.##");
    double ahvRate = 10.6;
    double alvRate = 2.2;
    double taxRate = 11.25;
    double churchTaxRate = 0.8;
    double ahvValue;
    double alvValue;
    double taxValue;
    double churchTaxValue;
    double pensionVal;
    double postTaxSalaryAmt;
    double mthNetSalaryAmt;
    String salaryTxt = "Take home salary: CHF";
    String mthSalaryTxt = "Monthly CHF:";
    String ahvText = "AHV/IV/EO (OASI) Value at 10.6%: CHF";
    String alvText = "ALV Value at 2.2%: CHF";
    String taxText = "Income Tax at 11.25%: CHF";
    String churchTaxText = "Church Tax at " + churchTaxRate + "%: CHF";
    String headerText = "Salary Information:";

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel ahvLabel = new JLabel(ahvText);
    JLabel alvLabel = new JLabel(alvText);
    JLabel taxLabel = new JLabel(taxText);
    JLabel churchTaxLabel = new JLabel(churchTaxText);
    JLabel pensionLabel = new JLabel();
    JRadioButton churchTax = new JRadioButton("Church Tax?");


    Canton aargau = new Canton("Aargau");
    Canton appenzellAu = new Canton("Appenzell Ausserrhoden");
    Canton appenzellIn = new Canton("Appenzell Innerrhoden");
    Canton baselLand = new Canton("Basel-Landschaft");
    Canton baselStadt = new Canton("Basel-Stadt");
    Canton bernen = new Canton("Berne");
    Canton fribourg = new Canton("Fribourg");
    Canton geneva = new Canton("Geneva");
    Canton glarus = new Canton("Glarus");
    Canton graubunden = new Canton("Graubünden");
    Canton jura = new Canton("Jura");
    Canton lucerne = new Canton("Lucerne");
    Canton neuchatel = new Canton("Neuchâtel");
    Canton nidwalden = new Canton("Nidwalden");
    Canton obwalden = new Canton("Obwalden");
    Canton schaffhausen = new Canton("Schaffhausen");
    Canton schwyz = new Canton("Schwyz");
    Canton solothurn = new Canton("Solothurn");
    Canton stGallen = new Canton("St. Gallen");
    Canton thurgau = new Canton("Thurgau");
    Canton ticino = new Canton("Ticino");
    Canton uri = new Canton("Uri");
    Canton valais = new Canton("Valais");
    Canton vaud = new Canton("Vaud");
    Canton zug = new Canton("Zug");
    Canton zurich = new Canton("Zürich");

    public GUI() {

        JButton calculateButton = new JButton("Calculate Salary");
        JLabel enterPensionLabel = new JLabel("Enter your employee pension contribution (%):");
        JLabel salaryLabel = new JLabel("Enter pre tax Salary (CHF):");
        JLabel cantonLabel = new JLabel("Choose your Canton of Residence:");

        salaryField = new JTextField(1);
        pensionField = new JTextField(1);
        postTaxField = new JLabel("Salary after tax: ");
        mthlTaxField = new JLabel("Monthly Salary after tax: ");
        calculateButtonLabel = new JLabel("Salary after tax: ");

        addInitialPanels();

    }

    public static void main(String[] args) {

        new GUI();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //METHODS USED BELOW
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // method to calculate the salary, input of pre-tax salary and pension values
    public double calcSalary(double preTaxSalary, double pensionValue, double churchTax) {
        System.out.println("calcSalary called");

        double postTaxSal;

        // convert the input values to percentages
        ahvValue = (ahvRate / 100) * preTaxSalary;
        alvValue = (alvRate / 100) * preTaxSalary;
        taxValue = (taxRate / 100) * preTaxSalary;
        churchTaxValue = (churchTax / 100) * preTaxSalary;
        pensionVal = (pensionValue / 100) * preTaxSalary;

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - ahvValue - alvValue - taxValue - churchTaxValue - pensionVal;

        return Double.parseDouble(df.format(postTaxSal));
    }

    // Method to add the panels for the deductions
    public void addDeductionPanels() {
        System.out.println("addDeductionPanels called");
        panel.add(calculateButtonLabel);
        panel.add(ahvLabel);
        panel.add(alvLabel);
        panel.add(taxLabel);
        panel.add(pensionLabel);
        // Add church tax panels if required
        chkChurchTax(churchTax.isSelected());

    }

    // Method adds the panels to the GUI
    public void addInitialPanels() {
        System.out.println("addInitialPanels called");

        JLabel salaryLabel = new JLabel("Enter pre tax Salary (CHF):");
        JButton calculateButton = new JButton("Calculate Salary");
        JLabel enterPensionLabel = new JLabel("Enter your employee pension contribution (%):");
        JLabel cantonLabel = new JLabel("Choose your Canton of Residence:");


        // Add all the elements into the GUI panel
        JComboBox cantonField = new JComboBox();

        ArrayList<String> cantonArray = new ArrayList<String>();
        cantonArray.add(aargau.getName());
        cantonArray.add(appenzellAu.getName());
        cantonArray.add(aargau.getName());
        cantonArray.add(appenzellAu.getName());
        cantonArray.add(appenzellIn.getName());
        cantonArray.add(baselLand.getName());
        cantonArray.add(baselStadt.getName());
        cantonArray.add(bernen.getName());
        cantonArray.add(fribourg.getName());
        cantonArray.add(geneva.getName());
        cantonArray.add(glarus.getName());
        cantonArray.add(graubunden.getName());
        cantonArray.add(jura.getName());
        cantonArray.add(lucerne.getName());
        cantonArray.add(neuchatel.getName());
        cantonArray.add(nidwalden.getName());
        cantonArray.add(obwalden.getName());
        cantonArray.add(schaffhausen.getName());
        cantonArray.add(schwyz.getName());
        cantonArray.add(solothurn.getName());
        cantonArray.add(stGallen.getName());
        cantonArray.add(ticino.getName());
        cantonArray.add(thurgau.getName());
        cantonArray.add(uri.getName());
        cantonArray.add(valais.getName());
        cantonArray.add(vaud.getName());
        cantonArray.add(zug.getName());
        cantonArray.add(zurich.getName());

        for(int i=0; i<cantonArray.size(); i++)
        {
            cantonField.addItem(cantonArray.get(i));
        }

        salaryField = new JTextField(1);
        pensionField = new JTextField(1);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(enterPensionLabel);
        panel.add(pensionField);
        panel.add(cantonLabel);
        panel.add(cantonField);
        panel.add(churchTax);
        panel.add(calculateButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Salary Calculator");
        //frame.setPreferredSize(new Dimension(1000, 11000));
        frame.pack();
        frame.setVisible(true);

        // called when clicking calculate salary button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click");

                boolean printValues = nullChecker(salaryField.getText(), pensionField.getText());

                // Null check OK, proceed
                if (printValues) {
                    processCalc(pensionField.getText());
                }
            }
        });

        //called when a canton is selected in the cantonList field
        cantonField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Canton Selected");
            }
        });
    }

    // Method to add the panels for the deductions after clicking calculate
    public void updateValues(double postTaxSalaryAmt, double mthNetSalaryAmt) {
        System.out.println("updateValues called");

        // update the labels with the new values
        churchTaxLabel.setText(churchTaxLabel.getText() + NumberFormat.getInstance().format(churchTaxValue));
        postTaxField.setText(postTaxField.getText() + NumberFormat.getInstance().format(postTaxSalaryAmt));
        mthlTaxField.setText(mthlTaxField.getText() + NumberFormat.getInstance().format(mthNetSalaryAmt));
        pensionLabel.setText(pensionLabel.getText() + NumberFormat.getInstance().format(pensionVal));
        ahvLabel.setText(ahvLabel.getText() + NumberFormat.getInstance().format(ahvValue));
        alvLabel.setText(alvLabel.getText() + NumberFormat.getInstance().format(alvValue));
        taxLabel.setText(taxLabel.getText() + NumberFormat.getInstance().format(taxValue));
    }

    // Method to clear the values after a recalc
    public void clearValues() {
        System.out.println("clear values called");

        postTaxField.setText(salaryTxt);
        mthlTaxField.setText(mthSalaryTxt);
        ahvLabel.setText(ahvText);
        alvLabel.setText(alvText);
        taxLabel.setText(taxText);
        churchTaxLabel.setText(churchTaxText);
    }

    // Method checks adds/removes churchtax fields
    public void chkChurchTax(boolean isChurchTaxSelected) {
        System.out.println("chkChurchTax called");

        if (isChurchTaxSelected) {
            System.out.println("churchTax.isSelected is true");
            panel.add(churchTaxLabel);
            panel.add(postTaxField);
            panel.add(mthlTaxField);
        } else {
            System.out.println("churchTax.isSelected is false");
            panel.remove(churchTaxLabel);
            panel.add(postTaxField);
        }
    }

    // Method to calculate taxes
    public double calcTax() {
        System.out.println("calcTax called");

        double postTaxSalaryAmt;
        double mthNetSalaryAmt;
        String preTaxSalaryAmt = salaryField.getText();
        String pensionAmt = pensionField.getText();

        if (!churchTax.isSelected()) {
            // calculate the post tax salary without churchtax
            postTaxSalaryAmt = calcSalary(Double.parseDouble(preTaxSalaryAmt)
                    , Double.parseDouble(pensionAmt)
                    , Double.parseDouble("0")
            );
        } else {
            // calculate the post tax salary with church tax
            postTaxSalaryAmt = calcSalary(Double.parseDouble(preTaxSalaryAmt)
                    , Double.parseDouble(pensionAmt)
                    , churchTaxRate
            );
        }
        return Double.parseDouble(df.format(postTaxSalaryAmt));
    }

    // Method which processes the calculations
    public void processCalc(String pensionAmt) {
        System.out.println("processCalc called");
        String pensionText = "Pension at " + pensionAmt + "%: CHF";
        System.out.println("Print values is true");
        System.out.println("Calculating post tax salary");

        postTaxSalaryAmt = calcTax();
        mthNetSalaryAmt = postTaxSalaryAmt / 12;
        pensionLabel.setText(pensionText);

        // update the labels with the new values
        updateValues(postTaxSalaryAmt, mthNetSalaryAmt);

        // add the labels for the deductions after calculating
        addDeductionPanels();

        frame.pack();
    }

    public void clearAfterNull() {
        System.out.println("clearAfterNull called");
        panel.remove(ahvLabel);
        panel.remove(alvLabel);
        panel.remove(taxLabel);
        panel.remove(pensionLabel);
        panel.remove(postTaxField);
        panel.remove(mthlTaxField);
        panel.remove(churchTaxLabel);
        panel.add(calculateButtonLabel);
    }

    // Method to check required values have been entered and can we proceed with printing results
    public boolean nullChecker(String preTaxSalaryAmt, String pensionAmt) {
        System.out.println("nullChecker called");
        boolean printValues;
        String SalaryErr = "Please enter a salary value";
        String PensionErr = "Please enter a pension value";

        if (preTaxSalaryAmt.equals("")) {
            System.out.println("pre tax salary amount is null ");

            calculateButtonLabel.setText(SalaryErr);
            printValues = false;
            clearAfterNull();
        } else {
            System.out.println("pre tax salary amount is not null");
            if (pensionAmt.equals("")) {
                System.out.println("pension amount is null");

                calculateButtonLabel.setText(PensionErr);
                printValues = false;
                clearAfterNull();
            } else {
                System.out.println("pension amount is not null");
                System.out.println("printValues set to true");

                pensionAmt = pensionField.getText();
                calculateButtonLabel.setText(headerText);
                printValues = true;
            }
        }
        // Clear the values of the results for recalculations
        clearValues();

        // returns whether or not we should proceed with printing results
        return printValues;
    }
}
        
