package app.ui;

import javax.swing.*;
import java.awt.event.*;

public class PasswordRetrievalForm extends View {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;


    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JTextField getTextField1() {
        return textField1;
    }
}
