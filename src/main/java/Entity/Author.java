package Entity;

import abstraction.Person;

public class Author extends Person {

    public Author(int id, String name, String surname) {
        super(id, name, surname);
    }

    @Override
    public String toString() {
        return "Entity.Author{" + "id:" + getId() + ", " + getName() + '}';
    }
}
