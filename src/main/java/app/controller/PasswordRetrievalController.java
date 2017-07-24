package app.controller;

import app.ui.PasswordRetrievalForm;
import app.ui.View;
import business.logic.UserAccountService;
import business.model.mapping.UserAccount;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.event.ActionEvent;

/**
 * Created by alexandremasanes on 22/06/2017.
 */
@Singleton
public class PasswordRetrievalController extends BaseController {

    @Inject
    UserAccountService userAccountService;

    @Inject
    PasswordRetrievalForm passwordRetrievalForm;

    @Inject
    @Override
    public void init() {
        passwordRetrievalForm.getButtonOK().addActionListener(this);
        passwordRetrievalForm.
    }

    @Override
    protected View getView() {
        return passwordRetrievalForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String emailAddress;
        UserAccount userAccount;

        emailAddress = passwordRetrievalForm.getTextField1().getText();
        if(!emailAddress.isEmpty()) {
            userAccount = userAccountService.getUserAccount(emailAddress);

            if(userAccount == null)
                warn("L'adresse n'existe pas");
            else
                warn("Votre mot de passe est " + userAccount.getEmail());

        }
    }
}
