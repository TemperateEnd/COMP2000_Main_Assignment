import javax.swing.*;
import java.util.ArrayList;

public class cardPaymentForm extends JFrame{
    private JPanel mainPanel;

    static float totalToPay;
    static ArrayList<stockItem> basket;

    public cardPaymentForm(String title, ArrayList<stockItem> customerBasket, float customerTotal)
    {
        super(title);
        setContentPane(mainPanel);
        setSize(2500, 3000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        cardPaymentForm newForm = new cardPaymentForm("cardPayment",basket ,totalToPay);
    }
}
