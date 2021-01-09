import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static void main (String[] args)
    {
        LoginForm page = new LoginForm("Admin Login");
        page.setVisible(true);

        try {
            Scanner input = new Scanner(new File("src//adminAccountsCreds.txt"));
            input.useDelimiter("\n");

            while(input.hasNext())
            {
                for(int i = 0; i < accountsDatabase.length;i++)
                {
                    String tempName = input.nextLine();
                    String tempPassword = input.nextLine();
                    accountsDatabase[i] = new loginAccount(tempName, tempPassword);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
                AuthenticateUser(txtUsername.getText(), new String (pwdFieldPassword.getPassword()));
                }
        });
    }

    public void AuthenticateUser(String usernameInput, String passwordInput)
    {
        boolean matchNotFound = true;
        for(int i = 0; i < accountsDatabase.length; i++) {
            if (accountsDatabase[i].username.equals(usernameInput) && accountsDatabase[i].password.equals(passwordInput)){
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
            //System.out.println("Match found!");
            StockDatabaseForm nextPage = new StockDatabaseForm("nextPage");
            nextPage.setVisible(true);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
