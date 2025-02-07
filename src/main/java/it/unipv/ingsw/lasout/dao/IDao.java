package it.unipv.ingsw.lasout.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {

    /**
     *
     * @param oggetto ogetto contenente il solo identificatore dell'entità
     * @return l'oggetto caricato con i suoi dati PRIMITIVI
     * contiene anche gli oggetti di relazioni  1-1, 1-N
     * Per avere anche le relazioni  N-N usare get
     * @throws Exception
     */
    T getRaw(T oggetto) throws Exception;


    /**
     * @param oggetto ogetto contenente il solo identificatore dell'entità
     * @return l'oggetto caricato con i suoi dati,
     * attenzione che nelle relazioni N-N mi carica solo l'id di tale relazione.
     * @throws Exception se non esiste
     */
    T get(T oggetto) throws Exception;


    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void update(T t, String[] params) throws Exception;

    void delete(T t) throws Exception;
}