import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm extends JFrame{
    private JButton btnClear;
    private JPanel mainPanel;
    private JButton btnLogin;
    private JTextField txtUsername;
    private JPasswordField pwdFieldPassword;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel txtUsernamePanel;
    private JPanel pwdFieldPasswordPanel;
    private JPanel buttonsPanel;

    adminAccount[] userLogins = new adminAccount[2];



    public LoginForm(String title)
    {
        super(title);

        txtUsername.setEditable(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension( 400, 400));
        pack();

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                pwdFieldPassword.setText("");
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main (String[] args)
    {
        LoginForm page = new LoginForm("Admin Login");
        page.setVisible(true);
        //System.out.println("Test");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
