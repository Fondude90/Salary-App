package com.tom.mainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JButton calculateButton;
    private JLabel calculateButtonLabel;
    private JLabel salaryLabel;
    private JTextField salaryField;
    private JTextField pensionField;
    private JLabel pensionLabel;

    public GUI() {
        frame = new JFrame();
        panel = new JPanel();
        calculateButton = new JButton("Calculate Salary");
        calculateButtonLabel = new JLabel("Salary after tax: ");
        salaryLabel = new JLabel("Enter pre tax Salary");
        salaryField = new JTextField(1);
        pensionLabel = new JLabel("Enter your employee pension contribution (%)");
        pensionField = new JTextField(1);

        calculateButton.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
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
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    // handler for the calculate salary button
    public void actionPerformed(ActionEvent e) {
        String preTaxSalaryAmt = salaryField.getText();
        String pensionAmt = pensionField.getText();
        double postTaxSalaryAmt;
        String SalaryErr = "Please enter a salary value";
        String PensionErr = "Please enter a pension value";

        // handle null values in the input fields
        if (preTaxSalaryAmt.equals("")){
            calculateButtonLabel.setText(SalaryErr); // tell the user to enter a value
        } else if (pensionAmt.equals("")){
            calculateButtonLabel.setText(PensionErr); // tell the user to enter a value
        } else {
            postTaxSalaryAmt = calcSalary(Double.parseDouble(String.valueOf(preTaxSalaryAmt)),
                                          Double.parseDouble(String.valueOf(pensionAmt))
                                         );

            calculateButtonLabel.setText("Salary after tax, AHV/IV, pension: "); // clear the field first before recalc
            calculateButtonLabel.setText(calculateButtonLabel.getText() + postTaxSalaryAmt);
        }
    }

    // method to calculate the salary, input of pre-tax salary and pension values
    public double calcSalary(double preTaxSalary, double pensionValue){
        double ahvValue = 5.3;
        double alvValue = 1.1;
        double incomeTax = 11.25;
        double pensionVal;
        double postTaxSal;

        // convert the input values to percentages
        ahvValue = (ahvValue/100)*preTaxSalary;
        alvValue = (alvValue/100)*preTaxSalary;
        incomeTax = (incomeTax/100)*preTaxSalary;
        pensionVal = (pensionValue/100)*preTaxSalary;

        // Calculate the post tax salary
        postTaxSal = preTaxSalary - ahvValue - alvValue - incomeTax - pensionVal;

        // debug values
        System.out.println("pre tax: " + preTaxSalary);
        System.out.println("income tax: " + incomeTax);
        System.out.println("ahv: " + ahvValue);
        System.out.println("alv: " + alvValue);
        System.out.println("pensionVal: " + pensionVal);
        System.out.println("post tax: " + postTaxSal);

        return postTaxSal;
    }
}
