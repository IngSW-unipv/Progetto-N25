package it.unipv.ingsw.lasout.view.notify;

import it.unipv.ingsw.lasout.controller.Loadable;
import it.unipv.ingsw.lasout.controller.notify.*;
import it.unipv.ingsw.lasout.controller.notify.ButtonNotifyAction;
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

public class NotifyPanel extends JPanel implements Loadable {

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
        notifyList.setBackground(LaColor.SFONDO);
        notifyList.setFixedCellHeight(-1);
        notifyList.setCellRenderer(new NotifyRenderer(notifyController));
        notifyList.addMouseListener(new NotifyMouseAdapter(notifyList));
        scrollPane = new JScrollPane(notifyList);
        add(scrollPane);
    }

    @Override
    public void reload() {
        populateJList();
    }

    public void populateJList() {
        notifyModel.clear();
        try {
            Collection<Notify> list = notifyController.getAll();
            list.forEach(notify -> {
                notifyModel.addElement(notify);
            });
        } catch (Exception e) {
            //TODO
            throw new RuntimeException(e);
        }

    }



}
