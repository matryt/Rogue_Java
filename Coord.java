public class Coord {
    int x;
    int y;
    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Coord(int x) {
        this(x, 0);
    }
    Coord() {
        this(0, 0);
    }

    @Override
    public boolean equals(Object c) {
        if (c == null || c.getClass() != getClass()) {
            return false;
        }
        Coord other = (Coord) c;
        return other.x == this.x && other.y == this.y;
    }

    @Override
    public String toString() {
        return "<" + this.x + "," + this.y + ">"; 
    }

    public Coord add(Coord other) {
        return new Coord(this.x+other.x, this.y+other.y);
    }

    public Coord subtract(Coord other) {
        return new Coord(this.x-other.x, this.y-other.y);
    }

}
