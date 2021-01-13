package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class checkoutForm extends JFrame{

    private JPanel mainPanel;
    private JList listOrderDisplay;
    private JTextField txtItemBarcode;
    private JButton btnCheckout;
    private JButton btnConfirmBarcode;
    private JLabel lblBarcode;
    private JLabel lblTotal;

    static stockDatabase stockData = new stockDatabase();
    static int dataNum;
    static String source = "src\\testStockData.txt";
    public float runningTotal;
    ArrayList<stockItem> basket = new ArrayList<>();

    public static void main(String[] args)
    {
        checkoutForm newForm = new checkoutForm("newForm");
    }

    public checkoutForm(String title){

        super(title);
        setContentPane(mainPanel);
        setSize(2500, 3000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // Takes the testStockData.txt file and writes it into the stockItems array
        try {
            Scanner input = new Scanner(new File(source));
            input.useDelimiter("\n");

            while (input.hasNext()) {
                dataNum = Integer.parseInt(input.nextLine());
                for (int i = 0; i < dataNum; i++) {
                    String tempName = input.nextLine();
                    float tempPrice = Float.parseFloat(input.nextLine());
                    long tempBarcode = Long.parseLong(input.nextLine());
                    int tempNum = Integer.parseInt(input.nextLine());
                    stockData.itemsInStock[i] = new stockItem(tempName, tempPrice, tempBarcode, tempNum);
                    if(i < dataNum - 1) {
                        stockData.itemsInStock = Arrays.copyOf(stockData.itemsInStock, stockData.itemsInStock.length + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /** On button press, opens the payment option form and passes the basket,
         * running total and stockDatabase to the form**/
        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentOptionsForm paymentForm = new paymentOptionsForm("paymentForm", basket, runningTotal, stockData);
                setVisible(false);
            }
        });

        //On button press, takes the barcode entered by the user and puts the item into the cart
        btnConfirmBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               for(int i = 0;i < stockData.itemsInStock.length;i++)
               {
                   if(stockData.itemsInStock[i].itemBarcode == Long.parseLong(txtItemBarcode.getText()))
                   {
                        basket.add(stockData.itemsInStock[i]);
                        runningTotal += stockData.itemsInStock[i].itemPrice;
                        lblTotal.setText("£" + String.format(String.valueOf(runningTotal), "%.2f"));
                        break;
                   }
               }

               DefaultListModel<String> newList = new DefaultListModel<>();

                for(int i = 0; i < basket.size(); i++)
                {
                    newList.addElement(basket.get(i).itemName + ": £" + basket.get(i).itemPrice);
                }

                listOrderDisplay.setModel(newList);
            }
        });
    }
}
