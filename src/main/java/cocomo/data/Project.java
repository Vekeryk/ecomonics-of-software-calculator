package cocomo.data;

public enum Project {
    ORGANIC("Organic", 3.2, 1.05, 2.5, 0.38),
    SEMIDETACHED("Semidetached", 3.0, 1.12, 2.5, 0.35),
    EMBEDDED("Embedded", 2.8, 1.20, 2.5, 0.32);

    final String name;
    final double a;
    final double b;
    final double c;
    final double d;

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
