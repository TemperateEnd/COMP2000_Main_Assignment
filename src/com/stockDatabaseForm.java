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

    public static stockDatabase stockItems = new stockDatabase();
    static String source = "src//testStockData.txt";

    public int itemIndex;
    static int dataNum;

    public static void main(String[] args)
    {
        stockDatabaseForm databaseForm = new stockDatabaseForm("databaseForm");
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

        //Reads data from testStock.txt and uses it to create new objects to populate the stock database
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
                    stockItems.itemsInStock[i] = new stockItem(tempName, tempPrice, tempBarcode, tempNum);

                    if(i < dataNum - 1) {
                        stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock,
                                stockItems.itemsInStock.length + 1);
                    }
                }
            }

            DefaultTableModel stockData = new DefaultTableModel();

            stockData.addColumn("itemName");
            stockData.addColumn("itemPrice");
            stockData.addColumn("itemBarcode");
            stockData.addColumn("itemNum");

            for(int i =0;i<stockItems.itemsInStock.length;i++) {
                stockData.addRow(new Object[]{stockItems.itemsInStock[i].itemName,
                        stockItems.itemsInStock[i].itemPrice,
                        stockItems.itemsInStock[i].itemBarcode,
                        stockItems.itemsInStock[i].itemNum});
            }
            getTable().setModel(stockData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tblStockItems.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtItemName.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 0).toString());
                txtItemPrice.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 1).toString());
                txtItemBarcode.setText(tblStockItems.getValueAt(tblStockItems.getSelectedRow(), 2).toString());

                itemIndex = tblStockItems.getSelectedRow();

            }
        });

        //Logs the user out and moves them to the hub form on button press
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutHubForm originForm = new checkoutHubForm("originForm");
                setVisible(false);
            }
        });

        /**Takes the item selected in the datatable. A new object is created in the array where the selected item was
         * positioned using the values inside the text fields containing the item name, price and barcode before writing the
         * entire array to the stock data text file**/
        btnEditItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockItems.itemsInStock[itemIndex] = new stockItem(txtItemName.getText(),
                        Float.parseFloat(txtItemPrice.getText()),
                        Long.parseLong(txtItemBarcode.getText()),
                        stockItems.itemsInStock[itemIndex].itemNum);

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

        /** Creates a new object using the input entered by the user in the textfields and adds it to the array
         * before writing the newly resized array to the text file**/
        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newSize = stockItems.itemsInStock.length + 1;
                stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, newSize);
                stockItems.itemsInStock[newSize - 1] = new stockItem(txtItemName.getText(),
                        Float.parseFloat(txtItemPrice.getText()),
                        Long.parseLong(txtItemBarcode.getText()),
                        1);

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

        /** Deletes the selected item and shrinks the array before writing it to the text file**/
        btnDeleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockItems.itemsInStock[itemIndex] = new stockItem(txtItemName.getText(),
                        Float.parseFloat(txtItemPrice.getText()),
                        Long.parseLong(txtItemBarcode.getText()),
                        (stockItems.itemsInStock[itemIndex].itemNum - 1));

                if(stockItems.itemsInStock[itemIndex].itemNum == 0)
                {
                    stockItems.itemsInStock[itemIndex] = null;

                    for(int i = itemIndex + 1; i < stockItems.itemsInStock.length; i++)
                    {
                        stockItems.itemsInStock[i-1] = stockItems.itemsInStock[i];
                    }
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
                        f2.write(stockItems.itemsInStock[i].itemNum + "\n");
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
