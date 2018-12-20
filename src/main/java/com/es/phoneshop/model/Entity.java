package com.es.phoneshop.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class used as common class,
 * that unite all child classes by
 * Id, that give possibility to use
 * common dao.
 *
 * @author sheremet-vlad
 *
 * @version 1.0
 */
public class Entity implements Serializable {
    /**entity id*/
    private Long id;

    /**
     * This method creates object and set nothing
     */
    public Entity() {
    }

    /**
     * Method creates object and set passed parameter
     *
     * @param id this parameter will set to entity id
     */
    public Entity(Long id) {
        this.id = id;
    }

    /**
     * Method returns entity id
     *
     * @return {@code Long} entity id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method sets passed parameter to
     * entity id
     *
     * @param id this parameter will set to entity id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
