package business.logic;

/**
 * Created by alexandremasanes on 25/04/2017.
 */
public class ServiceException extends Exception {

    protected String notification;

    public ServiceException() {
        super();
    }

    public ServiceException(String notification) {
        super();
        this.notification = notification;
    }

    public String getNotification() {
        return notification;
    }
}
