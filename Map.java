import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import static java.lang.System.out;

public class Map {
    static Element ground = new Element("ground", ".");
    static HashMap<String, Coord> dir = new HashMap<>();
    int size;
    Coord pos;
    Hero hero;
    protected ArrayList<ArrayList<Element>> mat = new ArrayList<>();
    protected HashMap<Element, Coord> elem = new HashMap<>();

    Map(int size, Coord pos) {
        dir.put("z", new Coord(0, -1));
        dir.put("s", new Coord(0, 1));
        dir.put("d", new Coord(1, 0));
        dir.put("q", new Coord(-1, 0));
        this.size = size;
        this.pos = pos;
        this.hero = new Hero();
        ArrayList<Element> insideMat = new ArrayList<>();
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

    Map(int size) {
        this(size, new Coord(1, 1));
    }

    Map() {
        this(5, new Coord(1, 1));
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
        if (o.getClass() == Element.class) {
            return elem.containsKey(o);
        }
        return false;
    }

    public Element get(Coord c) throws NoSuchElementException{
        if (!contains(c)) {
            throw new NoSuchElementException();
        }
        return mat.get(c.y).get(c.x);
    }

    public Coord pos(Element s) throws NonexistentError{
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

    public void put(Coord c, Element e) throws IllegalArgumentException {
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
            Element e = mat.get(c.y).get(c.x);
            mat.get(c.y).set(c.x, ground);
            elem.remove(e);
        }
        else {
            throw new IllegalArgumentException("La coordonnée donnée n'est pas dans la carte !");
        }
    }

    public void move(Element e, Coord way) throws NonexistentError {
        Coord pos = pos(e);
        Coord arrive = pos.add(way);
        Element elemArrive = get(arrive);
        if (contains(arrive)) {
            if (elemArrive.equals(ground)) {
                rm(pos);
                put(arrive, e);
            }
            elemArrive.meet(hero);
        }
    }

    public void play(Keyboard k, Element hero) throws NonexistentError {
        while (true) {
            out.println(this);
            move(hero, dir.get(k.waitUntilPressed().toLowerCase()));
        }
    }

    public void play(Keyboard k) throws NonexistentError {
        play(k, new Hero());
    }
}
