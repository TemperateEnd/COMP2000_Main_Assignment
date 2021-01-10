import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class checkoutHubForm extends JFrame{
    private JPanel mainPanel;
    private JLabel lblTitle;



    public checkoutHubForm(String title) {

        super(title);
        setContentPane(mainPanel);
        setSize(550,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        lblTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Admin Login Screen should pop up");
            }
        });
    }

    public static void main (String[] args)
    {
        checkoutHubForm hubPage = new checkoutHubForm("testHub");
    }
}
