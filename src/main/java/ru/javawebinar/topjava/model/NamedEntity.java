package ru.javawebinar.topjava.model;

/**
 * Created by rolep on 15/06/16.
 */
public class NamedEntity extends BaseEntity {

    protected String name;

    public NamedEntity() {};

    protected NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
