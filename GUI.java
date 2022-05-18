package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI {
    private JLabel calculateButtonLabel;
    private JLabel postTaxField;
    private JLabel mthlTaxField;
    private JTextField salaryField;
    private JTextField pensionField;

    static DecimalFormat df = new DecimalFormat("#.##");
    static double ahvRate = 10.6;
    static double alvRate = 2.2;
    static double taxRate = 11.25;
    static double churchTaxRate = 0.8;
    static double ahvValue;
    static double alvValue;
    static double taxValue;
    static double churchTaxValue;
    static double pensionVal;
    static double postTaxSalaryAmt;
    static double mthNetSalaryAmt;
    static double preTaxSalary;
    String salaryTxt = "Take home salary: CHF";
    String mthSalaryTxt = "Monthly CHF:";
    String ahvText = "AHV/IV/EO (OASI) Value at 10.6%: CHF";
    String alvText = "ALV Value at 2.2%: CHF";
    String taxText = "Income Tax at " + taxRate + "%: CHF";
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

    Canton aargau = new Canton("Aargau", 1);
    Canton appenzellAu = new Canton("Appenzell Ausserrhoden",2);
    Canton appenzellIn = new Canton("Appenzell Innerrhoden",3);
    Canton baselLand = new Canton("Basel-Landschaft",4);
    Canton baselStadt = new Canton("Basel-Stadt",5);
    Canton bernen = new Canton("Berne",6);
    Canton fribourg = new Canton("Fribourg",7);
    Canton geneva = new Canton("Geneva",8);
    Canton glarus = new Canton("Glarus",9);
    Canton graubunden = new Canton("Graubünden,",10);
    Canton jura = new Canton("Jura",11);
    Canton lucerne = new Canton("Lucerne",12);
    Canton neuchatel = new Canton("Neuchâtel",13);
    Canton nidwalden = new Canton("Nidwalden",14);
    Canton obwalden = new Canton("Obwalden",15);
    Canton schaffhausen = new Canton("Schaffhausen",16);
    Canton schwyz = new Canton("Schwyz",17);
    Canton solothurn = new Canton("Solothurn",18);
    Canton stGallen = new Canton("St. Gallen",19);
    Canton thurgau = new Canton("Thurgau",20);
    Canton ticino = new Canton("Ticino",22);
    Canton uri = new Canton("Uri",23);
    Canton valais = new Canton("Valais",24);
    Canton vaud = new Canton("Vaud",25);
    Canton zug = new Canton("Zug",26);
    Canton zurich = new Canton("Zürich",27);

    public GUI() {

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
        JComboBox<Canton> cantonField = new JComboBox<Canton>();
        cantonField.addItem(aargau);
        cantonField.addItem(appenzellAu);
        cantonField.addItem(appenzellIn);
        cantonField.addItem(baselLand);
        cantonField.addItem(baselStadt);
        cantonField.addItem(bernen);
        cantonField.addItem(fribourg);
        cantonField.addItem(geneva);
        cantonField.addItem(glarus);
        cantonField.addItem(graubunden);
        cantonField.addItem(jura);
        cantonField.addItem(lucerne);
        cantonField.addItem(neuchatel);
        cantonField.addItem(nidwalden);
        cantonField.addItem(obwalden);
        cantonField.addItem(schaffhausen);
        cantonField.addItem(schwyz);
        cantonField.addItem(solothurn);
        cantonField.addItem(stGallen);
        cantonField.addItem(ticino);
        cantonField.addItem(thurgau);
        cantonField.addItem(uri);
        cantonField.addItem(valais);
        cantonField.addItem(vaud);
        cantonField.addItem(zug);
        cantonField.addItem(zurich);

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
                    taxLabel.setText(taxText);
                }
            }
        });

        //called when a canton is selected in the cantonList field
        cantonField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select");
                Canton cantonFieldSelectedItem = cantonField.getItemAt(cantonField.getSelectedIndex()); // Crates a variable of type Canton and finds Canton in the Field
                taxRate = cantonFieldSelectedItem.getTaxRate();                                        // Update the global variable with chosen Canton's tax rate
                taxText = "Income Tax at " + taxRate + "%: CHF";                                     // Update the label to reflect newly set tax rate

                System.out.println("Canton: " + cantonFieldSelectedItem.getName() + ", tax rate: " + taxRate);
            }
        });
    }

    // Shouldn't all of these be in a separate page somewhere?
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
            frame.pack();
        } else {
            System.out.println("churchTax.isSelected is false");
            panel.remove(churchTaxLabel);
            panel.add(postTaxField);
            frame.pack();
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
            postTaxSalaryAmt = GUI_Util.calcSalary(Double.parseDouble(preTaxSalaryAmt)
                    , Double.parseDouble(pensionAmt)
                    , Double.parseDouble("0")
            );
        } else {
            // calculate the post tax salary with church tax
            postTaxSalaryAmt = GUI_Util.calcSalary(Double.parseDouble(preTaxSalaryAmt)
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
        frame.pack();
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
