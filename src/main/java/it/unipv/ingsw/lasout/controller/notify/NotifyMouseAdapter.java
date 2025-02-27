package it.unipv.ingsw.lasout.controller.notify;

import it.unipv.ingsw.lasout.model.notify.Notify;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class NotifyMouseAdapter extends MouseAdapter {

    private JList<Notify> notifyList;

    public NotifyMouseAdapter(JList<Notify> notifyList) {
        this.notifyList = notifyList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int index = notifyList.locationToIndex(e.getPoint());
        if (index != -1) {
            Rectangle cellBounds = notifyList.getCellBounds(index, index);
            Point pointInCell = new Point(e.getX() - cellBounds.x, e.getY() - cellBounds.y);

            NotifyRenderer renderer = (NotifyRenderer) notifyList.getCellRenderer();
            renderer.getListCellRendererComponent(notifyList, notifyList.getModel().getElementAt(index), index, false, false);

            Rectangle trashBounds = renderer.getTrashButton().getBounds();
            trashBounds.width += 10;
            trashBounds.height += 10;
            renderer.getButtonPanel().doLayout();
            if (trashBounds.contains(pointInCell)) {
                Arrays.stream(renderer.getTrashButton().getActionListeners()).forEach(x -> {
                    Notify notify = notifyList.getModel().getElementAt(index);
                    x.actionPerformed(new ButtonNotifyAction(notify, renderer.getTrashButton(), ActionEvent.ACTION_PERFORMED, null));
                });
                return;
            }


            for (Component comp : renderer.getButtonPanel().getComponents()) {

                JButton button = (JButton) comp;

                Rectangle buttonBounds = button.getBounds();
                buttonBounds.translate(renderer.getButtonPanel().getX(), renderer.getButtonPanel().getY());


                if (buttonBounds.contains(pointInCell)) {
                    Arrays.stream(button.getActionListeners()).forEach(x -> {
                        Notify notify = notifyList.getModel().getElementAt(index);
                        x.actionPerformed(new ButtonNotifyAction(notify, button, ActionEvent.ACTION_PERFORMED, null));
                    });
                    return;
                }

            }
        }
    }
}
