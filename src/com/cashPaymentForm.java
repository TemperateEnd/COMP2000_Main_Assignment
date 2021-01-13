package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class cashPaymentForm extends JFrame{
    private JPanel mainPanel;
    private JLabel lblTotalToPay;
    private JLabel lblAmountPaid;
    private JButton btn2p;
    private JButton btn1p;
    private JButton btn5p;
    private JButton btn10p;
    private JButton btn50p;
    private JButton btn£2;
    private JButton btn£1;
    private JButton btn20p;
    private JButton btn£5;
    private JButton btn£10;
    private JButton btn£20;
    private JButton btn£50;
    private JButton btnConfirmPayment;

    static float totalToPay;
    static ArrayList<stockItem> basket;
    static float amountPaid;
    static stockDatabase stockData;

    public cashPaymentForm(String title, ArrayList<stockItem> customerBasket, float customerTotal, stockDatabase databaseParameter)
    {
        super(title);
        setContentPane(mainPanel);
        setSize(2500, 3000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        lblTotalToPay.setText(String.valueOf(totalToPay));
        lblAmountPaid.setText(String.valueOf(amountPaid));

        stockData = databaseParameter;

        btnConfirmPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptForm receipt = new receiptForm("customerReceipt", basket, totalToPay, (amountPaid - totalToPay), false, stockData);
                setVisible(false);
            }
        });

        btn£2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 2.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn1p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.01f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn2p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.02f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn5p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.05f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn10p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.1f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn20p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.2f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn£1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 1.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn50p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 0.5f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn£5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 5.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn£10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 10.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn£20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 20.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });

        btn£50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amountPaid += 50.0f;
                lblAmountPaid.setText(String.valueOf(amountPaid));
            }
        });
    }

    public static void main(String[] args)
    {
        cashPaymentForm newForm = new cashPaymentForm("cashPayment",basket ,totalToPay, stockData);
    }
}
