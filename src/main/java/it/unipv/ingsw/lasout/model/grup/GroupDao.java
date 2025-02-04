package it.unipv.ingsw.lasout.model.grup;

import it.unipv.ingsw.lasout.util.DatabaseUtil;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class GroupDao implements IGroupDao{

    @Override
    public Optional get(long id) {
        return Optional.empty();
        //con le conn di dada
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public void save(GroupBean group) {

    }

    @Override
    public void update(GroupBean group, String[] params) {

    }

    @Override
    public void delete(GroupBean group) {

    }

}
