package emploiDuTemps_java;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;

public class login_admin extends JFrame {
    JLabel lbUsername, lbPassword, lbEmail;
    JTextField tfUsername, tfEmail;
    JPasswordField pfPassword;
    JButton btnLogin;

    public login_admin() {
        setTitle("Login Page");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel pn = new JPanel();
        pn.setLayout(null);
        add(pn);
        pn.setBackground(new Color(173, 216, 230));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lbUsername = new JLabel("Nom d'utilisateur :");
        lbUsername.setBounds(35, 50, 160, 25);
        pn.add(lbUsername);
        
        tfUsername = new JTextField();
        tfUsername.setBounds(140, 50, 200, 25);
        pn.add(tfUsername);

        lbPassword = new JLabel("Mot de passe :");
        lbPassword.setBounds(40, 90, 100, 25);
        pn.add(lbPassword);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(140, 90, 200, 25);
        pn.add(pfPassword);

        lbEmail = new JLabel("E-mail :");
        lbEmail.setBounds(50, 130, 80, 25);
        pn.add(lbEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(140, 130, 200, 25);
        pn.add(tfEmail);

        btnLogin = new JButton("Se connecter");
        btnLogin.setBounds(150, 180, 120, 30);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText();
                String password = new String(pfPassword.getPassword());
                String email = tfEmail.getText();

                if (isValidEmail(email) && username.equals("etudiant_tekup") && password.equals("etudiant123")) {
                    dispose();
                    Etudiant ed = new Etudiant();
                    ed.setVisible(true);
                } else if (isValidEmailISI(email) && username.equals("admin_tekup") && password.equals("admin123")) {
                    dispose();
                    Cours crs = new Cours();
                    crs.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants invalides ou format d'email incorrect !", "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pn.add(btnLogin);

        setVisible(true);
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@etudiant-tek-up.de");
    }

    private boolean isValidEmailISI(String email) {
        return email.endsWith("@tek-up.de");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new login_admin();
            }
        });
    }
}
