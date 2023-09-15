public class Element {
    private final String name;
    private final String abbrv;
    public Element(String name, String abbrv) {
        this.name = name;
        this.abbrv = abbrv;
    }

    public Element(String name) {
        this(name, String.valueOf(name.charAt(0)));
    }

    public String getName() {
        return name;
    }

    public String getAbbrv() {
        return abbrv;
    }

    @Override
    public String toString() {
        return abbrv;
    }

    public String description() {
        return "<" +
                name +
                ">";
    }

    public boolean meet(Element h) {
        if (h.getClass() == Hero.class) {
            ((Hero) h).take(this);
        }
        return true;
    }
}

//TODO: Define equals method
