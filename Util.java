package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.tom.mainGui.Canton.cantonList;

public class Util {

    public static JLabel calculateButtonLabel;
    public static JLabel postTaxField;
    public static JLabel mthlTaxField;
    public static JTextField salaryField;
    public static  JTextField pensionField;

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
    static String salaryTxt = "Take home salary: CHF";
    static String mthSalaryTxt = "Monthly: CHF";
    static String ahvText = "AHV/IV/EO (OASI) Value at 10.6%: CHF";
    static String alvText = "ALV Value at 2.2%: CHF";
    static String taxText = "Income Tax at " + taxRate + "%: CHF";
    static String churchTaxText = "Church Tax at " + churchTaxRate + "%: CHF";
    static String headerText = "Salary Information:";

    static JFrame frame = new JFrame();
    public static JPanel panel = new JPanel();
    static JLabel ahvLabel = new JLabel(ahvText);
    static JLabel alvLabel = new JLabel(alvText);
    static JLabel taxLabel = new JLabel(taxText);
    static JLabel churchTaxLabel = new JLabel(churchTaxText);
    public static JLabel pensionLabel = new JLabel();
    public static JRadioButton churchTax = new JRadioButton("Church Tax?");



    // calculate the salary, input of pre-tax salary and pension values
    static double calcSalary(double preTaxSalary, double pensionValue, double churchTax) {
        System.out.println("calcSalary called");

        double postTaxSal;

        // convert the input values to percentages
        ahvValue = (ahvRate / 100) * preTaxSalary;
        alvValue = (alvRate / 100) * preTaxSalary;
        taxValue = (taxRate / 100) * preTaxSalary;
        churchTaxValue = (churchTax / 100) * preTaxSalary;
        pensionVal = (pensionValue / 100) * preTaxSalary;

        System.out.println("preTaxSalary: " + preTaxSalary);
        System.out.println("taxRate: " + taxRate);
        System.out.println("taxValue: " + taxValue);

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - ahvValue - alvValue - taxValue - churchTaxValue - pensionVal;
        return Double.parseDouble(df.format(postTaxSal));
    }
    // adds the panels with the results after calculation complete
    public static void addDeductionPanels() {
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
    public static void createGUI() {
        System.out.println("createGUI called");

        JLabel salaryLabel = new JLabel("Enter pre tax Salary (CHF):");
        JButton calculateButton = new JButton("Calculate Salary");
        JLabel enterPensionLabel = new JLabel("Enter your employee pension contribution (%):");
        JLabel cantonLabel = new JLabel("Choose your Canton of Residence:");

        // Create cantonList array
        Canton.createCantonList();

        // Add all the elements into the GUI panel
        for (Canton canton : cantonList) {
            Canton.cantonField.addItem(canton);
        }

        salaryField = new JTextField(1);
        pensionField = new JTextField(1);
        postTaxField = new JLabel("Salary after tax: ");
        mthlTaxField = new JLabel("Monthly Salary after tax: ");
        calculateButtonLabel = new JLabel("Salary after tax: ");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(enterPensionLabel);
        panel.add(pensionField);
        panel.add(cantonLabel);
        panel.add(Canton.cantonField);
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
        Canton.cantonField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Canton cantonFieldSelectedItem = (Canton) Canton.cantonField.getItemAt(Canton.cantonField.getSelectedIndex()); // Crates a variable of type Canton and finds Canton in the Field
                taxRate = cantonFieldSelectedItem.getTaxRate();                                                               // Update the global variable with chosen Canton's tax rate
                cantonFieldSelectedItem.printObject();
                System.out.println("canton selected, tax rate set to: " + taxRate);
            }
        });
    }

    // Add the panels for the deductions after clicking calculate
    public static void updateValues(double postTaxSalaryAmt, double mthNetSalaryAmt) {
        System.out.println("updateValues called");

        // update the labels with the new values
        ahvLabel.setText(ahvLabel.getText() + NumberFormat.getInstance().format(ahvValue));
        alvLabel.setText(alvLabel.getText() + NumberFormat.getInstance().format(alvValue));
        taxLabel.setText(taxLabel.getText() + NumberFormat.getInstance().format(taxValue));
        pensionLabel.setText(pensionLabel.getText() + NumberFormat.getInstance().format(pensionVal));
        churchTaxLabel.setText(churchTaxLabel.getText() + NumberFormat.getInstance().format(churchTaxValue));
        postTaxField.setText(postTaxField.getText() + NumberFormat.getInstance().format(postTaxSalaryAmt));
        mthlTaxField.setText(mthlTaxField.getText() + NumberFormat.getInstance().format(mthNetSalaryAmt));
    }
    // Method to clear the values after a recalc
    public static void clearValues() {
        System.out.println("clear values called");

        postTaxField.setText(salaryTxt);
        mthlTaxField.setText(mthSalaryTxt);
        ahvLabel.setText(ahvText);
        alvLabel.setText(alvText);
        taxLabel.setText(taxText);
        churchTaxLabel.setText(churchTaxText);
    }
    // checks adds/removes churchtax fields
    public static void chkChurchTax(boolean isChurchTaxSelected) {
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
            panel.remove(mthlTaxField);
            panel.add(postTaxField);
            panel.add(mthlTaxField);
            frame.pack();
        }
    }
    // Calculates various taxes
    public static double calcTax() {
        System.out.println("calcTax called");

        double postTaxSalaryAmt;
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

    // Processes the calculations
    public static void processCalc(String pensionAmt) {

        String pensionText = "Pension at " + pensionAmt + "%: CHF";

        System.out.println("processCalc called " + taxText);
        System.out.println("Print values is true");
        System.out.println("Calculating post tax salary");

        postTaxSalaryAmt = calcTax();
        mthNetSalaryAmt = postTaxSalaryAmt / 12;
        pensionLabel.setText(pensionText);

        // update the labels with the new values
        updateValues(postTaxSalaryAmt, mthNetSalaryAmt);
        taxText = "Income Tax at " + taxRate + "%: CHF" + taxValue;                                                  // Update the label to reflect newly set tax rate

        // add the labels for the deductions after calculating
        addDeductionPanels();

        frame.pack();
    }

    // clears values if nullChecker fails
    public static void clearAfterNull() {
        System.out.println("clearAfterNull called, taxvalue" + taxValue);
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


    // checks required values have been entered and can we proceed with printing results
    public static boolean nullChecker(String preTaxSalaryAmt, String pensionAmt) {
        System.out.println("nullChecker called, tax value" + taxValue);

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
