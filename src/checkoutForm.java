import javax.swing.*;

public class checkoutForm extends JFrame{

    private JPanel mainPanel;

    public checkoutForm(String title){

        super(title);
        setContentPane(mainPanel);
        setSize(550, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
