import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StockDatabaseForm extends JFrame{

    private JPanel mainPanel;
    private JTable dataTableStock;
    private JPanel dataTablePanel;
    private JPanel inputsPanel;
    private JButton btnDelete;
    private JButton btnAdd;
    private JButton btnEdit;
    private JPanel btnPanel;
    private JPanel itemDataPanel;
    private JLabel lblItemName;
    private JTextField txtItemName;
    private JLabel lblPrice;
    private JTextField txtItemPrice;

    static StockDatabase stockDatabaseInstance = new StockDatabase();

    public static void main(String[] args)
    {
        StockDatabaseForm page = new StockDatabaseForm("Stock Database");
        page.setVisible(true);

        try {
            Scanner input = new Scanner(new File("src//testStockData.txt"));
            input.useDelimiter("\n");

            while(input.hasNext())
            {
                for(int i = 0; i < stockDatabaseInstance.itemsInStock.length;i++)
                {
                    String tempName = input.nextLine();
                    float tempPrice = Float.valueOf(input.nextLine());
                    stockDatabaseInstance.itemsInStock[i] = new StockItem(tempName, tempPrice);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public StockDatabaseForm(String title)
    {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension( 500, 500));
        pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
