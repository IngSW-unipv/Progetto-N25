package it.unipv.ingsw.lasout.view.notify;

import it.unipv.ingsw.lasout.controller.notify.ButtonNotifyAction;
import it.unipv.ingsw.lasout.controller.notify.ButtonNotifyAction;
import it.unipv.ingsw.lasout.controller.notify.NotifyController;
import it.unipv.ingsw.lasout.controller.notify.NotifyRenderer;
import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.group.Group;
import it.unipv.ingsw.lasout.model.notify.Notify;
import it.unipv.ingsw.lasout.view.LaColor;
import it.unipv.ingsw.lasout.view.group.GroupItem;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class NotifyPanel extends JPanel {

    private NotifyController notifyController;


    private DefaultListModel<Notify> notifyModel;
    private JList<Notify> notifyList;
    private JScrollPane  scrollPane;


    public NotifyPanel(NotifyController  notifyController) {
        notifyController.setNotifyPanel(this);
        this.notifyController = notifyController;

        preparePanel();
        prepareJList();

        setVisible(true);

    }


    public void preparePanel(){
        setLayout(new BorderLayout());
        setBackground(LaColor.SFONDO);


    }
    public void prepareJList(){
        notifyModel = new DefaultListModel<>();
        notifyList = new JList<>(notifyModel);
        notifyList.setCellRenderer(new NotifyRenderer(notifyController));
        notifyList.setFixedCellHeight(-1);


        notifyList.addMouseListener(new MouseAdapter() {
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
                        ((DefaultListModel<Notify>) notifyList.getModel()).removeElementAt(index);
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
        });

        scrollPane = new JScrollPane(notifyList);
        add(scrollPane);
    }

    public void populateJList() {
        notifyModel.clear();
        try {
            Collection<Notify> list = notifyController.getAll();
            list.forEach(notify -> {
                System.out.println(notify);
                notifyModel.addElement(notify);
            });
        } catch (Exception e) {
            //TODO
            throw new RuntimeException(e);
        }

    }

    public void updateView(){
        populateJList();
    }



}
