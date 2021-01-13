package com;

import org.junit.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class adminLoginForm extends JFrame
{
    static loginAccount[] accountsDatabase = new loginAccount[2];
    private JPanel mainPanel;
    private JButton btnLogin;
    private JButton btnClear;
    private JTextField txtUsername;
    private JPasswordField pwdFieldAdminPass;
    private JLabel lblUsername;
    private JLabel lblPassword;

    public adminLoginForm (String title)
    {
        super(title);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550,1000);
        pack();
        setVisible(true);

        /**This section reads data from adminAccountsCreds.txt to the accountsDatabase array to create
        the administrator accounts**/
        try {
            Scanner input = new Scanner(new File("src\\adminAccountsCreds.txt"));
            input.useDelimiter("\n");

            while (input.hasNext()) {
                for (int i = 0; i < accountsDatabase.length; i++) {
                    String tempName = input.nextLine();
                    String tempPassword = input.nextLine();
                    accountsDatabase[i] = new loginAccount(tempName, tempPassword);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Clears textfields on button press
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                pwdFieldAdminPass.setText("");
            }
        });

        /**On button press, invokes a method and passes the values stored in the
        textfields as parameters within the method**/
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthenticateUser(txtUsername.getText(), pwdFieldAdminPass.getText());
            }
        });
    }

    public static void main (String[] args)
    {
        adminLoginForm page = new adminLoginForm("loginPage");
    }

    /** Takes the values in the textfields and compares them to
     * any of the usernames and passwords stored within the loginAccount
     * objects stored in the accountsDatabase array.**/
    public void AuthenticateUser(String usernameInput, String passwordInput) {
        boolean matchNotFound = true;
        for (int i = 0; i < accountsDatabase.length; i++) {
            if (accountsDatabase[i].username.equals(usernameInput) && accountsDatabase[i].password.equals(passwordInput)) {
                matchNotFound = false;
                break;
            }
        }

        if (matchNotFound) {
            InvalidCredentials();
        } else {
            ValidCredentials();
        }
    }

    //Invoked when matchNotFound is false; Opens the stockDatabaseForm
    public void ValidCredentials()
    {
        stockDatabaseForm dbPage = new stockDatabaseForm("nextPage");
        this.setVisible(false);
    }

    /**Invoked when matchNotFound is true; Displays a messagebox to inform the
    user that they have entered invalid credentials **/
    public void InvalidCredentials()
    {
        JOptionPane.showMessageDialog(null, "Error! Invalid username or " +
                "password! Please try again!");
        txtUsername.setText("");
        pwdFieldAdminPass.setText("");
    }
}
