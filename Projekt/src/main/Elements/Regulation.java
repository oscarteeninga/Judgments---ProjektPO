package Elements;

import java.util.HashSet;
import java.util.Set;

public class Regulation extends AbstractDocument {
    public String name;
    public Set<Judgment> judgments = new HashSet<>();

    public Regulation(long id, String name, String date, String description) {
        this.id = id;
        this.name = name;
        this.publicationDate = date;
        this.description = description;
    }

    public String toString() {
        return name + " " + publicationDate;
    }
}
