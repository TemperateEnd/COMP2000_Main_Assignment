package com;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class stockDatabaseForm extends JFrame{
    private JPanel mainPanel;
    private JButton btnAddItem;
    private JButton btnDeleteItem;
    private JButton btnEditItem;
    private JTextField txtItemName;
    private JTextField txtItemPrice;
    private JTextField txtItemBarcode;
    private JTable tblStockItems;
    private JButton btnLogOut;
    private JButton btnRefresh;

    public static stockDatabase stockItems = new stockDatabase();
    static String source = "src//testStockData.txt";

    public int itemIndex;
    static int dataNum;

    public static void main(String[] args)
    {
        stockDatabaseForm databaseForm = new stockDatabaseForm("databaseForm");

        try {
            Scanner input = new Scanner(new File(source));
            input.useDelimiter("\n");

            while (input.hasNext()) {
                dataNum = Integer.parseInt(input.nextLine());
                for (int i = 0; i < dataNum; i++) {
                    String tempName = input.nextLine();
                    float tempPrice = Float.parseFloat(input.nextLine());
                    int tempBarcode = Integer.parseInt(input.nextLine());
                    stockItems.itemsInStock[i] = new stockItem(tempName, tempPrice, tempBarcode);

                    if (stockItems.itemsInStock.length <= dataNum - 1) {
                        stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, stockItems.itemsInStock.length + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DefaultTableModel stockData = new DefaultTableModel();

        stockData.addColumn("itemName");
        stockData.addColumn("itemPrice");
        stockData.addColumn("itemBarcode");

        for(int i =0;i<stockItems.itemsInStock.length;i++) {
            stockData.addRow(new Object[]{stockItems.itemsInStock[i].itemName, stockItems.itemsInStock[i].itemPrice, stockItems.itemsInStock[i].itemBarcode});
        }
        databaseForm.getTable().setModel(stockData);
    }

    public JTable getTable()
    {
        return tblStockItems;
    }

    public stockDatabaseForm(String title)
    {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2500, 3000);
        setVisible(true);
        pack();


        tblStockItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtItemName.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 0).toString());
                txtItemPrice.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 1).toString());
                txtItemBarcode.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 2).toString());

                itemIndex = tblStockItems.getSelectedRow();

            }
        });

        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminLoginForm loginForm = new adminLoginForm("loginForm");
                setVisible(false);
            }
        });

        btnEditItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockItems.itemsInStock[itemIndex] = new stockItem(txtItemName.getText(), Float.parseFloat(txtItemPrice.getText()),Long.parseLong(txtItemBarcode.getText()));

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

                /**DefaultTableModel newStockData = new DefaultTableModel();

                newStockData.addColumn("itemName");
                newStockData.addColumn("itemPrice");
                newStockData.addColumn("itemBarcode");

                for(int i =0;i<stockItems.itemsInStock.length;i++) {
                    newStockData.addRow(new Object[]{stockItems.itemsInStock[i].itemName, stockItems.itemsInStock[i].itemPrice, stockItems.itemsInStock[i].itemBarcode});
                }
                getTable().setModel(newStockData);**/
            }
        });
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newSize = stockItems.itemsInStock.length + 1;
                stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, newSize);
                stockItems.itemsInStock[newSize - 1] = new stockItem(txtItemName.getText(),Float.parseFloat(txtItemPrice.getText()), Long.parseLong(txtItemBarcode.getText()));

                dataNum = stockItems.itemsInStock.length;

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
            }
        });

        btnDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockItems.itemsInStock[itemIndex] = null;

                for(int i = itemIndex + 1; i < stockItems.itemsInStock.length; i++)
                {
                    stockItems.itemsInStock[i-1] = stockItems.itemsInStock[i];
                }

                int newSize = stockItems.itemsInStock.length - 1;
                stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, newSize);

                dataNum = stockItems.itemsInStock.length;

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
            }
        });
    }
}
