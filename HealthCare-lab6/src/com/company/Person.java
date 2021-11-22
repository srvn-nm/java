package com.company;

/**
 * The type Person.
 */
public abstract class Person {
    private String name;

    /**
     * Instantiates a new Person.
     *
     * @param name the name
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
