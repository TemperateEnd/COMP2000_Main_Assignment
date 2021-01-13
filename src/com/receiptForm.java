package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class receiptForm extends JFrame{
    private JPanel mainPanel;
    private JTextPane txtPanelReceipt;
    private JButton btnReturn;

    static float totalCost;
    static float changeGiven;

    static ArrayList<stockItem> basket;

    static boolean cardPaymentReceived;
    static stockDatabase stockItems;
    static int dataNum;

    static String source = "src//testStockData.txt";

    public receiptForm(String title, ArrayList<stockItem> itemsToRemoveFromDatabase, float transactionTotal, float changeToGive, boolean paidViaCard, stockDatabase databaseToUpdate)
    {
        super(title);
        setContentPane(mainPanel);
        setSize(2500, 3000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        cardPaymentReceived = paidViaCard;
        stockItems = databaseToUpdate;

        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutHubForm hubForm = new checkoutHubForm("returnToHub");
                setVisible(false);
            }
        });
    }

    public static void main(String[] args)
    {
        receiptForm customerReceipt = new receiptForm("receipt", basket, totalCost, changeGiven, cardPaymentReceived,stockItems);

        customerReceipt.SwingWorkerLoader();
    }

    void SwingWorkerLoader()
    {
        new SwingWorker<Void, Object>(){
            String receiptString;
            final String totalCostFormat = String.format("%.2f", String.valueOf(totalCost));
            final String changeGivenFormat = String.format("%.2f",String.valueOf(changeGiven));
            @Override
            protected Void doInBackground() throws Exception{
                txtPanelReceipt.setText("Printing Receipt");

                for(int i = 0; i < 999999999; i++)
                {
                    new Date();
                }

                if (cardPaymentReceived == true)
                {
                    receiptString = "GeneriCo Superstores \n \n" + LocalDateTime.now() + "\n\n ------------ \n\n";

                    for (int i = 0; i < basket.size();i++)
                    {
                        receiptString += basket.get(i).itemName + ": £" + basket.get(i).itemPrice + "\n";
                    }

                    receiptString += "\n \n \n \n \n ------------ \n \n Total cost: £" + totalCostFormat + "\n\n\n Verified by card for amount: £" + totalCostFormat;
                }

                else if (cardPaymentReceived == false)
                {
                    receiptString = "GeneriCo Superstores \n \n" + LocalDateTime.now() + "\n\n ------------ \n\n";

                    for (int i = 0; i < basket.size();i++)
                    {
                        receiptString += basket.get(i).itemName + ": £" + basket.get(i).itemPrice + "\n";
                    }

                    receiptString += "\n \n \n \n \n ------------ \n \n Total cost: £" + totalCostFormat + "\n\n\n Changed given: £" + changeGivenFormat;
                }

                txtPanelReceipt.setText(receiptString);

                for(int i = 0; i < stockItems.itemsInStock.length;i++) {
                    for (int j = 0; j < basket.size(); j++) {
                        if (stockItems.itemsInStock[i].itemName == basket.get(j).itemName) {
                            stockItems.itemsInStock[i] = null;
                            dataNum--;
                            stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, dataNum);
                        }
                    }
                }

                try {
                    FileWriter f2 = new FileWriter(source,false);
                    f2.write(dataNum + "\n");
                    for(int i = 0; i < stockItems.itemsInStock.length; i++)
                    {
                        f2.write(stockItems.itemsInStock[i].itemName + "\n");
                        f2.write(stockItems.itemsInStock[i].itemPrice + "\n");
                        f2.write(stockItems.itemsInStock[i].itemBarcode + "\n");
                    }
                    f2.close();
                } catch (IOException ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                } catch (NumberFormatException ne){
                    ne.printStackTrace();
                }

                return null;
            }
        }.execute();
    }
}
