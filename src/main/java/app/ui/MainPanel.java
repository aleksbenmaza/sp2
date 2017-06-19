package app.ui;

import javax.inject.Singleton;
import javax.swing.*;
import java.awt.event.*;

@Singleton
public class MainPanel extends View {

    private JPanel contentPane;

    private JButton customerValidationButton;

    private JButton contractValidationButton;

    private JButton customersListButton;

    private JButton button1;

    private JButton button2;

    private JButton sinisterValidationButton;

    private JButton buttonOK;

    private JButton buttonCancel;

    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getCustomersListButton() {
        return customersListButton;
    }

    public JButton getButton1() {
        return button1;
    }

    public JButton getButton2() {
        return button2;
    }

    public JButton getCustomerValidationButton() {
        return customerValidationButton;
    }

    public JButton getContractValidationButton() {
        return contractValidationButton;
    }

    public JButton[] getButtons() {
        return new JButton[] {
                customerValidationButton,
                sinisterValidationButton,
                contractValidationButton,
                customersListButton
        };
    }

    public JButton getSinisterValidationButton() {
        return sinisterValidationButton;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
