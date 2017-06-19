package app.controller;

import static java.util.Arrays.asList;

import app.ui.MainPanel;
import app.ui.View;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.ActionEvent;




/**
 * Created by alexandremasanes on 24/04/2017.
 */
@Singleton
public class MainController extends BaseController {

    @Inject
    private MainPanel mainPanel;

    @Inject
    private CustomersListController customersListController;

    @Override
    @PostConstruct
    public void init() {
        customersListController.mainController = this;
        asList(mainPanel.getButtons()).
                         forEach(b -> b.addActionListener(this));
    }

    @Override
    protected View getView() {
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source;
        source = e.getSource();

        if(source == mainPanel.getCustomersListButton())
            forward(customersListController);
    }
}
