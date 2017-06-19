package app.controller;

import business.logic.ServiceException;
import business.logic.UserAccountService;
import app.ui.LoginForm;
import app.ui.View;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import java.awt.event.ActionEvent;

/**
 * Created by alexandremasanes on 24/04/2017.
 */
@Singleton
public class LoginController extends BaseController {

    @Inject
    private MainController mainController;

    @Inject
    private LoginForm loginForm;

    @Inject
    private UserAccountService userAccountService;

    @Override
    @PostConstruct
    public void init() {
        loginForm.getButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) { System.out.println(loginForm.getEmailAddressField().getText() +'\n'+ loginForm.getPasswordField().getText());
        try {
            userAccountService.logIn(
                    userSession,
                    loginForm.getEmailAddressField().getText(),
                    loginForm.getPasswordField().getText()
            );

            forward(mainController);

        }catch (ServiceException ex) {
            warn(ex.getNotification());
        }
    }

    @Override
    protected View getView() {
        return loginForm;
    }
}
