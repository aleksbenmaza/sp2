package app.ui;

import app.InitializingBean;
import business.model.mapping.person.insuree.Customer;
import provider.PropertyProducer.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Enumeration;

@Singleton
public class CustomersListPanel extends View implements InitializingBean {

    private JPanel contentPane;

    private JButton backButton;

    private JTable table;

    private JTextField lastNameField;

    private JTextField firstNameField;

    private JTextField minContractsCountField;

    private JTextField maxContractsCountField;

    private JRadioButton validRadioButton;

    private JRadioButton invalidRadioButton;

    private ButtonGroup buttonGroup;
    private JRadioButton awaitingRadioButton;
    private JCheckBox filtrerCheckBox;
    private JRadioButton nullRadioButton;

    @Inject
    @Property(value = "customersListPanel.tableHeader", delimiter = '$')
    private String[] tableHeader;

    {
        buttonGroup = new ButtonGroup();

        buttonGroup.add(nullRadioButton);
        buttonGroup.add(validRadioButton);
        buttonGroup.add(invalidRadioButton);
        buttonGroup.add(awaitingRadioButton);
        buttonGroup.setSelected(nullRadioButton.getModel(), true);
        table.setDefaultEditor(Object.class, null);
        setContentPane(contentPane);
        setModal(true);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // call onCancel() on ESCAPE
        pack();
    }

    @Override
    @PostConstruct
    public void init() {
        table.setModel(new DefaultTableModel(new Object[][] {}, tableHeader));
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getFirstName() {
        return firstNameField.getText();
    }

    public Integer getMinContractsCount() {
        String text;
        text = minContractsCountField.getText();
        return text.matches("^-?\\d+$") ? Integer.valueOf(text) : null;
    }

    public Integer getMaxContractsCount() {
        String text;
        text = maxContractsCountField.getText();
        return text.matches("^-?\\d+$") ? Integer.valueOf(text) : null;
    }

    public JTextField[] getTextFields() {
        return new JTextField[] {
                lastNameField,
                firstNameField,
                minContractsCountField,
                maxContractsCountField,
        };
    }

    public JRadioButton getCheckedRadioButton() {
        Enumeration<AbstractButton> buttonsEnum;
        AbstractButton button;

        buttonsEnum = buttonGroup.getElements();
        button = null;

        while(buttonsEnum.hasMoreElements() && (button = buttonsEnum.nextElement()) != null)
            if(button.isSelected())
                return (JRadioButton)button;

        return null;
    }

    public JRadioButton getValidRadioButton() {
        return validRadioButton;
    }

    public JRadioButton getInvalidRadioButton() {
        return invalidRadioButton;
    }

    public JRadioButton getAwaitingRadioButton() {
        return awaitingRadioButton;
    }

    public void addRadioButtonsActionListener(ActionListener actionListener) {
        nullRadioButton.addActionListener(actionListener);
        validRadioButton.addActionListener(actionListener);
        awaitingRadioButton.addActionListener(actionListener);
        invalidRadioButton.addActionListener(actionListener);
    }

    public JCheckBox getFiltrerCheckBox() {
        return filtrerCheckBox;
    }

    public JRadioButton getNullRadioButton() {
        return nullRadioButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTable getTable() {
        return table;
    }

    public void setCustomers(Collection<Customer> customers) {
        DefaultTableModel model;

        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for(Customer customer : customers)
            model.addRow(new Object[] {
                    customer.getId(),
                    customer.getLastName(),
                    customer.getFirstName(),
                    customer.getAddress() + " " +
                        customer.getZipCode() + " " +
                            customer.getCity(),
                    messageHelper.getMessage("status"+customer.getStatus().ordinal()),
                    customer.getContracts().size()
            });

        table.getSelectionModel().setSelectionInterval(0, table.getModel().getColumnCount()-1);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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