package business.logic;

import static util.Hash.*;


import app.UserSession;
import business.model.mapping.UserAccount;
import business.model.mapping.person.Manager;
import business.model.mapping.person.RegisteredUser;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alexandremasanes on 24/04/2017.
 */

@Singleton
public class UserAccountService extends BaseService<UserAccount> {

    private String salt;

    public void logIn(UserSession userSession, String emailAddress, String password) throws ServiceException {
        RegisteredUser user;
        UserAccount userAccount;
        String toBeHashed;

        toBeHashed  = emailAddress+salt+password; System.out.println(toBeHashed);

        try {
            userAccount = dao.findUserAccount(emailAddress, encrypt(toBeHashed, SHA_1));
        }catch (NoSuchAlgorithmException e) {
            throw new ServiceException(getMessage("notification.error"));
        }

        if(userAccount == null || !((user = userAccount.getUser()) instanceof Manager))
            throw new LoginFailureException(getMessage("notifications.badCredentials"));


        userSession.setUser(user);
    }

    public UserAccount getUserAccount(String emailAddress) {
        return dao.findUserAccount(emailAddress);
    }

    @PostConstruct
    protected void postConstruct() {
        salt = dao.getHashSalt();
    }
}
