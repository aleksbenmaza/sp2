package app.controller;

import app.ui.CustomersListPanel;
import app.ui.View;
import business.logic.AdminService;
import business.model.mapping.Contract;
import business.model.mapping.ToBeChecked;
import business.model.mapping.person.Manager;
import business.model.mapping.person.insuree.Customer;
import javafx.scene.control.SelectionModel;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by alexandremasanes on 25/04/2017.
 */

@Singleton
public class CustomersListController extends BaseController implements MouseClickListener, KeyListener {

    protected MainController mainController;

    @Inject
    private CustomersListPanel customersListPanel;

    @Inject
    private CustomerDetailsController customerDetailsController;

    @Inject
    private AdminService adminService;

    @Override
    @PostConstruct
    public void init() {
        customerDetailsController.customersListController = this;
        customersListPanel.getBackButton().addActionListener(this);
        customersListPanel.getTable().addMouseListener(this);
        customersListPanel.getFiltrerCheckBox().addActionListener(this);
        customersListPanel.addRadioButtonsActionListener(this);
        Arrays.asList(customersListPanel.getTextFields())
                                        .forEach(
                                                jTextField -> jTextField.addKeyListener(this)
                                        );
    }

    @Override
    protected View getView() {
        return customersListPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source;
        if((source = e.getSource()) == customersListPanel.getBackButton())
            forward(mainController);



        else if(source instanceof JCheckBox)
            filter(((JCheckBox)source).isSelected());

        else if(source instanceof JRadioButton && customersListPanel.getFiltrerCheckBox().isSelected())
            filter(true);
    }

    protected void refreshViewModel() {
        Manager admin;

        admin = (Manager) userSession.getUser();
        admin = (Manager) adminService.refresh(admin);
        customersListPanel.setCustomers(admin.getCustomers());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table;
        long contractId;
        Manager admin;

        table = customersListPanel.getTable();
        if(e.getClickCount() == 2) {
            admin = (Manager) userSession.getUser();
            contractId = (Long)table.getValueAt(table.getSelectedRow(),0);
            admin.getCustomers()
                 .stream()
                 .filter(customer -> customer.getId() == contractId)
                 .findAny()
                 .ifPresent(customer -> forward(customerDetailsController, customer));
        }
    }

    private void filter(boolean bool) {
        System.out.println("begin");
        Set<Customer> customers = ((Manager) userSession.getUser()).getCustomers();
        System.out.println("end");

        if(bool) {
            JRadioButton radioButton;
            ToBeChecked.Status status;

            radioButton = customersListPanel.getCheckedRadioButton();

            if (radioButton == customersListPanel.getValidRadioButton())
                status = ToBeChecked.Status.VALID;
            else if (radioButton == customersListPanel.getAwaitingRadioButton())
                status = ToBeChecked.Status.AWAITING;
            else if (radioButton == customersListPanel.getInvalidRadioButton())
                status = ToBeChecked.Status.INVALID;
            else
                status = null;
                System.out.println(radioButton);
                customers = customers.stream()
                                     .filter(customer -> (
                                     customersListPanel.getLastName() == null || customersListPanel.getLastName().trim().isEmpty() || customer.getLastName().toLowerCase().startsWith(customersListPanel.getLastName().toLowerCase()))
                                    &&
                                    (customersListPanel.getFirstName() == null
                                            || customer.getFirstName().trim().isEmpty()
                                            || customer.getFirstName().toLowerCase().startsWith(customersListPanel.getFirstName().toLowerCase()))
                                    &&
                                    (customersListPanel.getMinContractsCount() == null
                                            || customer.getContracts().size() >= customersListPanel.getMinContractsCount())
                                    &&
                                    (customersListPanel.getMaxContractsCount() == null
                                            || customer.getContracts().size() <= customersListPanel.getMaxContractsCount())
                                    &&
                                    (status == null
                                            || customer.getStatus() == status)
            ).collect(Collectors.toSet());
        }

        customersListPanel.setCustomers(customers);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object source;
        if((source = e.getSource()) instanceof JTextField) {
            System.out.println("lol");
            if (customersListPanel.getFiltrerCheckBox().isSelected()) {
                filter(true);System.out.println("kek");
            }
        }
    }
}