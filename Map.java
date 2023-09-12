import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import static java.lang.System.out;

public class Map {
    static String ground = ".";
    static HashMap<String, Coord> dir = new HashMap<>();
    int size;
    Coord pos;
    String hero;
    protected ArrayList<ArrayList<String>> mat = new ArrayList<>();
    protected HashMap<String, Coord> elem = new HashMap<>();

    Map(int size, Coord pos, String hero) {
        dir.put("z", new Coord(0, -1));
        dir.put("s", new Coord(0, 1));
        dir.put("d", new Coord(1, 0));
        dir.put("q", new Coord(-1, 0));
        this.size = size;
        this.pos = pos;
        this.hero = hero;
        ArrayList<String> insideMat = new ArrayList<>();
        for (int i =0;i<size;i++) {
            for (int j =0;j<size;j++) {
                insideMat.add(ground);
            }
            mat.add(new ArrayList<>(insideMat));
            insideMat.clear();
        }
        mat.get(pos.y).set(pos.x, hero);
        elem.put(hero, pos);
    }

    Map(int size, Coord pos) {
        this(size, pos,"@");
    }

    Map(int size) {
        this(size, new Coord(1, 1), "@");
    }

    Map() {
        this(5, new Coord(1, 1), "@");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<size; i++) {
            for (int j=0; j<size;j++) {
                result.append(mat.get(i).get(j));
            }
            result.append("\n");
        }
        return result.toString();
    }

    public int length() {
        return size;
    }

    public boolean contains(Object o) {
        if (o.getClass() == Coord.class) {
            Coord c = (Coord) o;

            return 0 <= c.x && c.x < size && 0 <= c.y && c.y < size;
        }
        if (o.getClass() == String.class) {
            String s = (String) o;
            return elem.containsKey(s);
        }
        return false;
    }

    public String get(Coord c) {
        if (!contains(c)) {
            return "";
        }
        return mat.get(c.y).get(c.x);
    }

    public Coord pos(String s) throws NonexistentError{
        if (elem.containsKey(s)) {
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    if (Objects.equals(mat.get(i).get(j), s)) {
                        return new Coord(j, i);
                    }
                }
            }
        }
        throw new NonexistentError("L'élément demandé ne figure pas dans la carte !");
    }

    public void put(Coord c, String e) throws IllegalArgumentException {
        if (contains(c)) {
            mat.get(c.y).set(c.x, e);
            elem.put(e, c);
        }
        else {
            throw new IllegalArgumentException("La coordonnée donnée n'est pas dans la carte !");
        }
    }

    public void rm(Coord c) {
        if (contains(c)) {
            String e = mat.get(c.y).get(c.x);
            mat.get(c.y).set(c.x, ground);
            elem.remove(e);
        }
        else {
            throw new IllegalArgumentException("La coordonnée donnée n'est pas dans la carte !");
        }
    }

    public void move(String e, Coord way) throws NonexistentError {
        Coord pos = pos(e);
        Coord arrive = pos.add(way);
        String elemArrive = get(arrive);
        if (contains(arrive) && elemArrive.equals(ground)) {
            rm(pos);
            put(arrive, e);
        }
    }

    public void play(Keyboard k, String hero) throws NonexistentError {
        while (true) {
            out.println(this);
            move(hero, dir.get(k.waitUntilPressed().toLowerCase()));
        }
    }

    public void play(Keyboard k) throws NonexistentError {
        play(k, "@");
    }
}
