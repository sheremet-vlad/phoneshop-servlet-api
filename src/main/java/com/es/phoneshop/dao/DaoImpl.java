package com.es.phoneshop.dao;

import com.es.phoneshop.model.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl<T extends Entity> implements Dao<T>, Serializable {

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
                t.setId((long) entities.size() + 1);
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

    private boolean isExist(Long id) {
        return entities.stream()
                .anyMatch((p) -> p.getId().equals(id));
    }

}
