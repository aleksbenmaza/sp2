package app.controller;

import static javax.swing.JOptionPane.showMessageDialog;

import app.InitializingBean;
import app.UserSession;
import app.ui.View;

import javax.inject.Inject;

import java.awt.event.ActionListener;

/**
 * Created by alexandremasanes on 24/04/2017.
 */

public abstract class BaseController implements ActionListener, InitializingBean {

    @Inject
    protected UserSession userSession;

    protected Object[] params;

    public final void openView() {
        refreshViewModel();
        getView().setVisible(true);
    }

    public final void closeView() {
        getView().setVisible(false);
    }

    protected final void warn(String notification) {
        showMessageDialog(getView(), notification);
    }

    protected final void forward(BaseController baseController, Object... params) {
        baseController.params = params;
        baseController.refreshViewModel();
        closeView();
        baseController.openView();
    }

    protected abstract View getView();

    protected void refreshViewModel() {
    }
}
