import java.util.ArrayList;
import java.util.HashMap;

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
        }
        return result.toString();
    }
}
