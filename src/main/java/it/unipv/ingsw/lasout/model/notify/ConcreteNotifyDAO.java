package it.unipv.ingsw.lasout.model.notify;

import it.unipv.ingsw.lasout.facade.LaVaultFacade;
import it.unipv.ingsw.lasout.model.notify.action.INotifyAction;
import it.unipv.ingsw.lasout.model.notify.action.persistence.INotifyActionPersistenceFactory;
import it.unipv.ingsw.lasout.model.notify.logic.LogicNotifyDAOStrategy;
import it.unipv.ingsw.lasout.model.user.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ConcreteNotifyDAO implements INotifyDAO{

    private LogicNotifyDAOStrategy logicNotifyDAOStrategy;

    public ConcreteNotifyDAO() {

        Properties properties = new Properties();
        try {
            properties.load(LaVaultFacade.class.getResourceAsStream("/app.properties"));
            Class<?>  clazz = Class.forName(properties.getProperty("dao.inotifydao.strategy"));
            logicNotifyDAOStrategy  = (LogicNotifyDAOStrategy) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Notify> notifiesOf(User user) throws Exception {
        return logicNotifyDAOStrategy.notifiesOf(user);
    }

    @Override
    public INotifyAction getNotifyActionOf(Notify notify) throws Exception {
        return logicNotifyDAOStrategy.getNotifyActionOf(notify);
    }


    @Override
    public Notify getRaw(Notify oggetto) throws Exception {
        return logicNotifyDAOStrategy.getRaw(oggetto);
    }

    @Override
    public Notify get(Notify oggetto) throws Exception {
        return logicNotifyDAOStrategy.get(oggetto);
    }

    @Override
    public List<Notify> getAll() throws Exception {
        return logicNotifyDAOStrategy.getAll();
    }

    @Override
    public void save(Notify notify) throws Exception {
        logicNotifyDAOStrategy.save(notify);
    }

    @Override
    public void update(Notify notify) throws Exception {
        logicNotifyDAOStrategy.update(notify);
    }

    @Override
    public void delete(Notify notify) throws Exception {
        logicNotifyDAOStrategy.delete(notify);
    }
}
