package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JScrollPane;

import static java.awt.Component.RIGHT_ALIGNMENT;

public class GUI implements ActionListener {
    private JLabel calculateButtonLabel;
    private JTextField salaryField;
    private JTextField pensionField;
    private DefaultListModel listModel;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel ahvLabel = new JLabel("AHV Value at 5.3%: CHF");
    JLabel alvLabel = new JLabel("IV Value at 1.1%: CHF");
    JLabel taxLabel = new JLabel("Income Tax Value at 11.25%: CHF");
    JRadioButton churchTax = new JRadioButton( "Church Tax?");
    double ahvRate = 5.3;
    double alvRate = 1.1;
    double taxRate = 11.25;
    double churchTaxRate = 15;
    double ahvValue;
    double alvValue;
    double taxValue;

    DecimalFormat df = new DecimalFormat("#.##");


    public GUI() {

        JButton calculateButton = new JButton("Calculate Salary");
        JLabel pensionLabel = new JLabel("Enter your employee pension contribution (%):");
        JLabel salaryLabel = new JLabel("Enter pre tax Salary (CHF):");
        JLabel cantonLabel = new JLabel("Choose your Canton of Residence:");


        // Set up the Canton Array
        String[] cantons = {"Aargau (Argovia)"
                , "Appenzell Ausserrhoden (Outer Rhodes)"
                , "Appenzell Innerrhoden (Inner Rhodes)"
                , "Basel-Landschaft (Basle-Country)"
                , "Basel-Stadt (Basle-City)"
                , "Berne (Bern)"
                , "Fribourg"
                , "Geneva"
                , "Glarus"
                , "Graubünden (Grisons)"
                , "Jura"
                , "Lucerne"
                , "Neuchâtel"
                , "Nidwalden (Nidwald)"
                , "Obwalden (Obwald)"
                , "Schaffhausen"
                , "Schwyz"
                , "Solothurn"
                , "St. Gallen (St.Gall)"
                , "Thurgau (Thurgovia)"
                , "Ticino"
                , "Uri"
                , "Valais"
                , "Vaud"
                , "Zug"
                , "Zürich (Zurich)"};

        // set up the list field and scroll bar
        JList cantonList = new JList(cantons);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(cantonList);
        cantonList.setLayoutOrientation(JList.VERTICAL);
        cantonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cantonList.setVisibleRowCount(-1);

        salaryField = new JTextField(1);
        pensionField = new JTextField(1);
        calculateButtonLabel = new JLabel("Salary after tax: ");

        // called when clicking calculate salary button
        calculateButton.addActionListener(this);

        // Add all the elements into the GUI panel
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(pensionLabel);
        panel.add(pensionField);
        //panel.add(cantonLabel);
        //panel.add(cantonList);
        //panel.add(scrollPane);
        panel.add(churchTax);
        panel.add(calculateButton);

        // Add panel to the frame and enable settings
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Salary Calculator");
        frame.setPreferredSize(new Dimension(1000, 11000));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // handler for calculate salary button
    public void actionPerformed(ActionEvent e) {
        String preTaxSalaryAmt = salaryField.getText();
        String pensionAmt = pensionField.getText();
        String SalaryErr = "Please enter a salary value";
        String PensionErr = "Please enter a pension value";
        String salaryTxt = "Take home salary: CHF";
        String ahvText = "AHV Value at 5.3%: CHF";
        String alvText = "ALV Value at 1.1%: CHF";
        String taxText = "Income Tax at 11.25%: CHF";
        JLabel churchTaxLabel = new JLabel("Church Tax at 15%: CHF");
        double postTaxSalaryAmt;
        boolean printValues;

        System.out.println("calculate button clicked");

        // handle null values in the input fields
        if (preTaxSalaryAmt.equals("")) {
            calculateButtonLabel.setText(SalaryErr);
            printValues = false;
        } else if (pensionAmt.equals("")) {
            calculateButtonLabel.setText(PensionErr);
            printValues = false;
        } else{
            printValues = true;
        }

        if (printValues != false) {
            if (!churchTax.isSelected()) {
                // calculate the post tax salary
                postTaxSalaryAmt = calcSalary(Double.parseDouble(preTaxSalaryAmt)
                        , Double.parseDouble(pensionAmt)
                        , Double.parseDouble("0")
                );
            } else {
                // calculate the post tax salary with churchh tax
                postTaxSalaryAmt = calcSalary(Double.parseDouble(preTaxSalaryAmt)
                        , Double.parseDouble(pensionAmt)
                        , churchTaxRate
                );
            }

            // clear the fields first before recalc
            calculateButtonLabel.setText(salaryTxt);
            ahvLabel.setText(ahvText);
            alvLabel.setText(alvText);
            taxLabel.setText(taxText);

            // update the labels with the new values
            calculateButtonLabel.setText(calculateButtonLabel.getText() + postTaxSalaryAmt);
            ahvLabel.setText(ahvLabel.getText() + df.format(ahvValue));
            alvLabel.setText(alvLabel.getText() + df.format(alvValue));
            taxLabel.setText(taxLabel.getText() + df.format(taxValue));

            // add the labels for the deductions after calculating
            panel.add(ahvLabel);
            panel.add(alvLabel);
            panel.add(taxLabel);
            panel.add(calculateButtonLabel);

            if(churchTax.isEnabled()){
                panel.add(churchTaxLabel);
            }

        } else {
            // return error label due to missing values
            panel.add(calculateButtonLabel);
        }
    }





    // method to calculate the salary, input of pre-tax salary and pension values
    public double calcSalary(double preTaxSalary, double pensionValue, double churchTax) {
        double pensionVal;
        double postTaxSal;
        double churchTaxVal;

        // convert the input values to percentages
        ahvValue = (ahvRate / 100) * preTaxSalary;
        alvValue = (alvRate / 100) * preTaxSalary;
        taxValue = (taxRate / 100) * preTaxSalary;
        churchTaxVal = (churchTax/100)* preTaxSalary;
        pensionVal = (pensionValue / 100) * preTaxSalary;

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - ahvValue - alvValue - taxValue - churchTaxVal - pensionVal;

        // debug values
        System.out.println("pre tax: " + preTaxSalary);
        System.out.println("income tax: " + taxValue);
        System.out.println("ahv: " + ahvValue);
        System.out.println("alv: " + alvValue);
        System.out.println("pensionVal: " + pensionVal);
        System.out.println("churchTax: " + churchTaxVal);
        System.out.println("post tax: " + postTaxSal);

        return Double.parseDouble(df.format(postTaxSal));
    }
    /* method to return the tax rate of a specific canton
    public double retTaxRate(String cantonName){

    }

     */
}
