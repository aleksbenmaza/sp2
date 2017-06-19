package business.logic;

/**
 * Created by alexandremasanes on 24/04/2017.
 */
public class LoginFailureException extends ServiceException {

    public LoginFailureException() {
        super();
    }

    public LoginFailureException(String notification) {
        super(notification);
    }
}
