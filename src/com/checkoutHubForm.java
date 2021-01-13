package com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class checkoutHubForm extends JFrame{
    private JPanel mainPanel;
    private JLabel lblTitle;
    private JButton btnCheckout;


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
                adminLoginForm adminLoginForm = new adminLoginForm("loginForm");
                setVisible(false);
            }
        });
        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkoutForm customerPage = new checkoutForm("customerPage");
                setVisible(false);
            }
        });
    }

    public static void main (String[] args)
    {
        checkoutHubForm hubPage = new checkoutHubForm("testHub");
    }
}
