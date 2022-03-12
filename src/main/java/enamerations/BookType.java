package enamerations;

public enum BookType {
    SCIENCE("Science"), DRAMA("Drama"), HISTORY("History");

    private final String name;

    BookType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "enamerations.BookType{" +
                "name='" + name + '\'' +
                '}';
    }
}
