package it.unipv.ingsw.lasout.model.group;

public class GroupFacade {

    private GroupFacade() {}
    private static GroupFacade instance;
    public static GroupFacade getInstance() {
        if (instance == null) {
            instance = new GroupFacade();
        }
        return instance;
    }

    public boolean newGroup(Group group) {
        try {
            GroupDao.getInstance().save(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean getGroup(Group group) {
        try {
            GroupDao.getInstance().get(group);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean editGroup(Group group) {
        try {
            GroupDao.getInstance().update(group);
        } catch (Exception e) {
            return false;
        }
        return false;
    }


}
