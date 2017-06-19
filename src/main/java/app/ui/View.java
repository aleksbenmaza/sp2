package app.ui;

import helper.MessageHelper;

import javax.inject.Inject;
import javax.swing.*;

/**
 * Created by alexandremasanes on 24/04/2017.
 */
public abstract class View extends JDialog {

    @Inject
    protected MessageHelper messageHelper;
}
