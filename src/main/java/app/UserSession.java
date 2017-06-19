package app;

import business.model.mapping.person.RegisteredUser;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandremasanes on 24/04/2017.
 */

@Singleton
public class UserSession {

    private RegisteredUser user;

    private ArrayList<String> notifications;

    {
        notifications = new ArrayList<String>();
    }

    public RegisteredUser getUser() {
        return user == null ? user = null : user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }

    public void pushNotification(String notification) {
        notifications.add(notification);
    }

    public List<String> flushNotifications() {
        ArrayList<String> notifications;
        notifications = new ArrayList<String>(this.notifications);
        this.notifications.clear();
        return notifications;
    }
}
