package app.ui;

import javax.inject.Singleton;
import javax.swing.*;

/**
 * Created by alexandremasanes on 24/04/2017.
 */

@Singleton
public class LoginForm extends View {

    private JTextField emailAddressField;

    private JPasswordField passwordField;

    private JButton button;

    private JPanel main;

    {
        setContentPane(main);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
    }

    public JTextField getEmailAddressField() {
        return emailAddressField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getMain() {
        return main;
    }
}
