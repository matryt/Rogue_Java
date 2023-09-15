import static java.lang.System.out;

public class Test {

    public static Map initialize() {
        return new Map(10);
    }

    public static Map placeMonsters(Map m) {
        m.put(new Coord(6, 3), new Creature("Goblin", 5, "G", 3));
        m.put(new Coord(0, 4), new Creature("Snake", 5));
        m.put(new Coord(7, 7), new Creature("Snake", 5));
        m.put(new Coord(6, 8), new Element("gold", "o"));
        m.put(new Coord(4, 8), new Element("gold", "o"));
        return m;
    }
    public static void main(String[] args) throws NonexistentError {
        Map m = placeMonsters(initialize());
        m.play(new Keyboard());
    }
}
