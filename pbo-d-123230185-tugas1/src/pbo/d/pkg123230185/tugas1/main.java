package pbo.d.pkg123230185.tugas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main {

    public static void main(String[] args) {
        GUI g = new GUI();
    }
}

class GUI extends JFrame {

    final JTextField fnama = new JTextField(10);
    JLabel lnama = new JLabel("Username: ");

    final JPasswordField pwd = new JPasswordField(10);
    JLabel lpassword = new JLabel("Password: ");

    JButton loginButton = new JButton("Login");

    private JLabel messageLabel;

    public GUI() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 200);
        setLayout(null);

        lnama.setBounds(50, 20, 80, 25);
        fnama.setBounds(140, 20, 165, 25);
        lpassword.setBounds(50, 50, 80, 25);
        pwd.setBounds(140, 50, 165, 25);
        loginButton.setBounds(140, 80, 165, 25);

        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(185, 110, 200, 25);

        add(lnama);
        add(fnama);
        add(lpassword);
        add(pwd);
        add(loginButton);
        add(messageLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = fnama.getText();
                var password = new String(pwd.getPassword());

                if (username.equalsIgnoreCase("pbo") && password.equalsIgnoreCase("if-d")) {
                    openDashboard();
                } else {
                    messageLabel.setText("Gagal Login");
                }
            }
        });

        setVisible(true);
    }

    private void openDashboard() {
        try {
            dashboard dashboard = new dashboard();
            dashboard.setVisible(true);
            dispose();
        } catch (Exception e) {
            e.printStackTrace(); // Cetak exception jika terjadi
        }
    }
}

class dashboard extends JFrame {
    public dashboard(){
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}