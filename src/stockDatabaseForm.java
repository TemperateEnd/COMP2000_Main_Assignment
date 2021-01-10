import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
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
    public JList listStockItems;

    static stockDatabase stockItems = new stockDatabase();

    public static void main (String[] args)
    {
        DefaultListModel listModel = new DefaultListModel();
        stockDatabaseForm databaseForm = new stockDatabaseForm("databaseForm");

        try {
            Scanner input = new Scanner(new File("src//testStockData.txt"));
            input.useDelimiter("\n");

            while (input.hasNext()) {
                for (int i = 0; i < stockItems.itemsInStock.length; i++) {
                    String tempName = input.nextLine();
                    float tempPrice = Float.parseFloat(input.nextLine());
                    int tempBarcode = Integer.parseInt(input.nextLine());
                    stockItems.itemsInStock[i] = new stockItem(tempName, tempPrice, tempBarcode);
                    listModel.addElement(stockItems.itemsInStock[i]);
                    stockItems.itemsInStock = Arrays.copyOf(stockItems.itemsInStock, stockItems.itemsInStock.length + 1);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public stockDatabaseForm(String title)
    {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 1000);
        setVisible(true);
        pack();
    }
}
