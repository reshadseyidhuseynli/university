package abstraction;

public abstract class Identifier {
    private final int id;
    private final String name;

    protected Identifier(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
