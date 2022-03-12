package abstraction;

public abstract class Person extends Identifier {
    private final String surname;

    protected Person(int id, String name, String surname) {
        super(id, name);
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }
}
