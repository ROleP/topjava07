package ru.javawebinar.topjava.model;

/**
 * Created by rolep on 15/06/16.
 */
public class BaseEntity {

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
