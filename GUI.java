package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GUI implements ActionListener{
    private JLabel calculateButtonLabel;
    private JTextField salaryField;
    private JTextField pensionField;
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel ahvLabel = new JLabel("AHV Value at 5.3%: CHF" );
    JLabel alvLabel = new JLabel("IV Value at 1.1%: CHF");
    JLabel taxLabel = new JLabel("Income Tax Value at 11.25%: CHF");
    double ahvRate = 5.3;
    double alvRate = 1.1;
    double taxRate = 11.25;
    double ahvValue;
    double alvValue;
    double taxValue;

    DecimalFormat df = new DecimalFormat("#.##");

    public GUI() {

        JButton calculateButton = new JButton("Calculate Salary");
        JLabel pensionLabel = new JLabel("Enter your employee pension contribution (%)");
        JLabel salaryLabel = new JLabel("Enter pre tax Salary (CHF)");

        salaryField = new JTextField(1);
        pensionField = new JTextField(1);
        calculateButtonLabel = new JLabel("Salary after tax: ");
        calculateButton.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(salaryLabel);
        panel.add(salaryField);
        panel.add(pensionLabel);
        panel.add(pensionField);
        panel.add(calculateButton);
        panel.add(calculateButtonLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Salary Calculator");
        frame.setPreferredSize(new Dimension(400, 300));
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
        String salaryTxt = "Salary after tax, AHV/IV, pension: CHF";
        String ahvText = "AHV Value at 5.3%: CHF";
        String alvText = "ALV Value at 1.1%: CHF";
        String taxText = "Income Tax at 11.25%: CHF";
        double postTaxSalaryAmt;

        // handle null values in the input fields
        if (preTaxSalaryAmt.equals("")){
            calculateButtonLabel.setText(SalaryErr);
        } else if (pensionAmt.equals("")){
            calculateButtonLabel.setText(PensionErr);
        } else {
            // calculate the post tax salary
            postTaxSalaryAmt = calcSalary(Double.parseDouble(preTaxSalaryAmt)
                                         ,Double.parseDouble(pensionAmt)
                                         );

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
        }
    }

    // method to calculate the salary, input of pre-tax salary and pension values
    public double calcSalary(double preTaxSalary, double pensionValue){
        double pensionVal;
        double postTaxSal;

        // convert the input values to percentages
        ahvValue = (ahvRate/100)*preTaxSalary;
        alvValue = (alvRate/100)*preTaxSalary;
        taxValue = (taxRate/100)*preTaxSalary;
        pensionVal = (pensionValue/100)*preTaxSalary;

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - ahvValue - alvValue - taxValue - pensionVal;

        // debug values
        System.out.println("pre tax: " + preTaxSalary);
        System.out.println("income tax: " + taxValue);
        System.out.println("ahv: " + ahvValue);
        System.out.println("alv: " + alvValue);
        System.out.println("pensionVal: " + pensionVal);    
        System.out.println("post tax: " + postTaxSal);

        return Double.parseDouble(df.format(postTaxSal));
    }
}
