package helper;

import static java.util.ResourceBundle.getBundle;

import javax.inject.Singleton;
import java.text.MessageFormat;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by alexandremasanes on 22/04/2017.
 */
@Singleton
public class MessageHelper {

    private ResourceBundle resourceBundle;

    private MessageFormat messageFormat;

    {
        resourceBundle = getBundle("i18n/messages", Locale.FRENCH);

        messageFormat = new MessageFormat("");
    }

    public String getMessage(String code, Object... vars) {
        messageFormat.applyPattern(resourceBundle.getString(code));
        return messageFormat.format(vars);
    }
}
