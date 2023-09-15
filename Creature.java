public class Creature extends Element{
    private double hp;
    private int strength;
    public Creature(String name, double hp, String abbrv, int strength) {
        super(name, abbrv);
        this.hp = hp;
        this.strength = strength;
    }

    public Creature(String name, double hp, String abbrv) {
        this(name, hp, abbrv, 1);
    }

    public Creature(String name, double hp) {
        this(name, hp, String.valueOf(name.charAt(0)), 1);
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String description() {
        return super.description()+"("+ (int) hp +")";
    }

    public boolean meet(Hero h) {
        hp -= h.getStrength();
        return hp<=0;
    }
}
