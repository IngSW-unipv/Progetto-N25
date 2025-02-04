package it.unipv.ingsw.lasout.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    T get(T oggetto) throws Exception;

    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void update(T t, String[] params) throws Exception;

    void delete(T t) throws Exception;
}