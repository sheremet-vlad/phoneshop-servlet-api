package com.es.phoneshop.dao;

import com.es.phoneshop.model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * This class realize interface
 * to work with database. All methods
 * can work with objects, that realizes
 * {@link Entity} interface
 *
 * @param <T> type of object with which work database.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public abstract class DaoImpl<T extends Entity> implements Dao<T> {

    /** list of entity, emulations the databese */
    protected final List<T> entities = new ArrayList<>();

    @Override
    public T getEntity(Long id) {
        synchronized (entities) {
            return entities.stream()
                    .filter((p) -> p.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("There is no element with such id = " + id));
        }
    }

    @Override
    public void save(T t) {
        synchronized (entities) {
            if (t.getId() == null) {
                t.setId((long) entities.size());
                entities.add(t);
            } else {
                if (!isExist(t.getId())) {
                    entities.add(t);
                }
            }
        }
    }

    @Override
    public void delete(Long id) {
        synchronized (entities) {
            if (!entities.removeIf(p -> p.getId().equals(id))) {
                throw new IllegalArgumentException("There is no element with such id = " + id);
            }
        }
    }

    @Override
    public void deleteAll() {
        synchronized (entities) {
            entities.clear();
        }
    }


    /**
     * Method checks if entity
     * exist in the list
     *
     * @param id id by which search entity
     *
     * @return {@code boolean} if product was found in
     * the list, then return true, else false.
     */
    private boolean isExist(Long id) {
        return entities.stream()
                .anyMatch((p) -> p.getId().equals(id));
    }

}
