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

    public paymentOptionsForm (String title, ArrayList<stockItem> itemsList, float customerTotal)
    {
        super(title);
        setContentPane(mainPanel);
        setSize(2500, 3000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        transactionTotal = customerTotal;
        transactionBasket = itemsList;

        lblTotal.setText("Your total is £" + String.format(String.valueOf(customerTotal), "%.2f") + ".\n How would you like to pay?");

        btnCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashPaymentForm cashPayment = new cashPaymentForm("paying via cash", transactionBasket, transactionTotal);
            }
        });
        btnCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPaymentForm cardPayment = new cardPaymentForm("paying via card", transactionBasket, transactionTotal);
            }
        });
    }

    public static void main(String[] args)
    {
        paymentOptionsForm form = new paymentOptionsForm("payment", transactionBasket, transactionTotal);
    }
}
