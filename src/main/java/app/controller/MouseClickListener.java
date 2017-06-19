package app.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by alexandremasanes on 25/04/2017.
 */
public interface MouseClickListener extends MouseListener {

    @Deprecated
    @Override
    default void mousePressed(MouseEvent e) {

    }

    @Deprecated
    @Override
    default void mouseReleased(MouseEvent e) {

    }

    @Deprecated
    @Override
    default void mouseEntered(MouseEvent e) {

    }

    @Deprecated
    @Override
    default void mouseExited(MouseEvent e) {

    }
}
