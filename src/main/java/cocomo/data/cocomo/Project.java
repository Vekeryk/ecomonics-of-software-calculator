package cocomo.data.cocomo;

public enum Project {
    ORGANIC("Розповсюджений", 3.2, 1.05, 2.5, 0.38),
    SEMIDETACHED("Напіврозділений", 3.0, 1.12, 2.5, 0.35),
    EMBEDDED("Вбудований", 2.8, 1.20, 2.5, 0.32);

    public final String name;
    public final double a;
    public final double b;
    public final double c;
    public final double d;

    Project(String name, double a, double b, double c, double d) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
