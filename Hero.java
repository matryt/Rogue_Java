import java.util.ArrayList;

public class Hero extends Creature{
    private ArrayList<Element> inventory;
    public Hero() {
        super("Hero", 10, "@", 2);
        inventory = new ArrayList<>();
    }

    public void take(Element e) {
        inventory.add(e);
    }

    @Override
    public String description() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (Element e: inventory) {
            s.append(e.toString());
        }
        s.append("]");
        return super.description() + s;
    }
}
