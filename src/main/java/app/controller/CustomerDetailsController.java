package app.controller;

import app.ui.CustomerDetailsPanel;
import app.ui.View;
import business.logic.CustomerService;
import business.model.mapping.person.insuree.Customer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.ActionEvent;

/**
 * Created by alexandremasanes on 25/04/2017.
 */

@Singleton
public class CustomerDetailsController extends BaseController {

    protected CustomersListController customersListController;

    @Inject
    private CustomerDetailsPanel customerDetailsPanel;

    @Inject
    private CustomerService customerService;

    @Override
    @PostConstruct
    public void init() {
        customerDetailsPanel.getValidateButton().addActionListener(this);
        customerDetailsPanel.getBackButton().addActionListener(this);
    }

    @Override
    protected View getView() {
        return customerDetailsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source;
        Customer customer;

        source = e.getSource();

        if(source == customerDetailsPanel.getBackButton())
            forward(customersListController);

        else if(source == customerDetailsPanel.getValidateButton()) {
            customer = (Customer) params[0];
            customer.setFirstName(customerDetailsPanel.getFirstName());
            customer.setLastName(customerDetailsPanel.getLastName());
            customer.setStatus(customerDetailsPanel.getStatus());
            customerService.save(customer);
        }
    }

    @Override
    public void refreshViewModel() {
        customerDetailsPanel.setCustomer(params.length == 1 ? (Customer) params[0]:new Customer());
    }
}
