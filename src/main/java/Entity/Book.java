package Entity;

import abstraction.Identifier;
import enamerations.BookType;

public class Book extends Identifier {
    private final Author author;
    private final BookType type;
    private final int page;
    private final boolean hardCover;

    public Book(int id, String name, Author author, BookType type, int page, boolean hardCover) {
        super(id, name);
        this.author = author;
        this.type = type;
        this.page = page;
        this.hardCover = hardCover;
    }

    public Author getAuthor() {
        return author;
    }

    public BookType getType() {
        return type;
    }

    public int getPage() {
        return page;
    }

    public boolean isHardCover() {
        return hardCover;
    }

    @Override
    public String toString() {
        return "Entity.Book{" +
                "id:" + getId() +
                ", " + getName() +
                ", author:" + author.getName() +
                ", type:" + type.getName() +
                ", page:" + page +
                '}';
    }
}
