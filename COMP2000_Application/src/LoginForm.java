import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private JLabel lblTitle;

    static loginAccount[] accountsDatabase = new loginAccount[2];

    static loginAccount userAdmin = new loginAccount("admin", "admin");
    static loginAccount userReece = new loginAccount("rtarrant", "w4RN3veRch4nG35");


    public static void main (String[] args)
    {
        LoginForm page = new LoginForm("Admin Login");
        page.setVisible(true);

        accountsDatabase[0] = userAdmin;
        accountsDatabase[1] = userReece;
    }

    public LoginForm(String title)
    {
        super(title);

        txtUsername.setEditable(true);
        pwdFieldPassword.setEditable(true);
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
                boolean matchNotFound = true;
                    for(int i = 0; i < accountsDatabase.length; i++) {
                        if (accountsDatabase[i].username.equals(txtUsername.getText()) && accountsDatabase[i].password.equals(new String (pwdFieldPassword.getPassword()))){
                            System.out.println("Match found!");
                            matchNotFound = false;
                            break;
                        }
                    }

                    if(matchNotFound)
                    {
                        System.out.println("Match not found!");
                    }

                    else
                    {
                        StockDatabaseForm nextPage = new StockDatabaseForm("nextPage");
                        dispose();
                        nextPage.setVisible(true);
                    }
                }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
