import app.controller.LoginController;
import org.jboss.weld.environment.se.StartMain;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Locale;

/**
 * Created by alexandremasanes on 19/04/2017.
 */

public class App {

    @Inject
    private LoginController loginController;

    public static void main(String... args) {
        Locale.setDefault(Locale.FRENCH);
        StartMain.main(args);
    }

    public void start(@Observes ContainerInitialized containerInitialized) {
        loginController.openView();
    }
}
