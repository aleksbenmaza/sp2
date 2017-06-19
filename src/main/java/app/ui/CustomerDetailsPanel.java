package app.ui;

import java.util.Arrays;
import java.util.Objects;

import app.InitializingBean;
import business.model.mapping.ToBeChecked;
import business.model.mapping.person.insuree.Customer;
import provider.PropertyProducer.Property;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.event.*;


@Singleton
public class CustomerDetailsPanel extends View implements InitializingBean {

    private JPanel contentPane;

    private JButton backButton;

    private JButton validateButton;

    private JTextField idField;

    private JTextField lastNameField;

    private JTextField firstNameField;

    private JTextField bonusMalusField;

    private JComboBox statusCBox;

    @Inject
    @Property(value = "allStatus", delimiter = '$')
    private String[] statusTitles;

    {
        setContentPane(contentPane);
        setModal(true);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    @Override
    @PostConstruct
    public void init() {
        Arrays.asList(statusTitles)
              .forEach(t -> statusCBox.addItem(t));
    }

    public void setCustomer(Customer customer) {
        idField.setText(Objects.toString(customer.getId()));
        lastNameField.setText(Objects.toString(customer.getLastName()));
        firstNameField.setText(Objects.toString(customer.getFirstName()));
        bonusMalusField.setText(Objects.toString(customer.getBonusMalus()));
        statusCBox.setSelectedIndex(customer.getStatus().ordinal());
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public ToBeChecked.Status getStatus() {
        return ToBeChecked.Status.values()[statusCBox.getSelectedIndex()];
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