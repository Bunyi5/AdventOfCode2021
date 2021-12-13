package advent.of.code.tasks.task12;

import java.util.Objects;

public class Cave {

    private final String name;
    private final boolean big;

    public Cave(String name) {
        this.name = name;
        this.big = Character.isUpperCase(name.charAt(0));
    }

    public String getName() {
        return name;
    }

    public boolean isBig() {
        return big;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cave cave = (Cave) o;
        return big == cave.big && Objects.equals(name, cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, big);
    }

    @Override
    public String toString() {
        return "Cave{" +
            "name='" + name + '\'' +
            ", big=" + big +
            '}';
    }
}
