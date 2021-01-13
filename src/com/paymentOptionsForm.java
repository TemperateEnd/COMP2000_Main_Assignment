package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class paymentOptionsForm extends  JFrame{
    private JPanel mainPanel;
    private JLabel lblTotal;
    private JButton btnCash;
    private JButton btnCard;

    public static float transactionTotal;
    public static ArrayList<stockItem> transactionBasket;
    public static stockDatabase dataStockItems;

    public paymentOptionsForm (String title, ArrayList<stockItem> itemsList, float customerTotal, stockDatabase stockData)
    {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        transactionTotal = customerTotal;
        transactionBasket = itemsList;
        dataStockItems = stockData;

        lblTotal.setText("Your total is £" + String.format(String.valueOf(customerTotal), "%.2f") + ".\n\n How would you like to pay?");

        btnCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashPaymentForm cashPayment = new cashPaymentForm("paying via cash", transactionBasket, transactionTotal, dataStockItems);
                setVisible(false);
            }
        });
        btnCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receiptForm cardPayment = new receiptForm("paying via card", transactionBasket, transactionTotal, 0.0f, true, dataStockItems);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args)
    {
        paymentOptionsForm form = new paymentOptionsForm("payment", transactionBasket, transactionTotal, dataStockItems);
    }
}
