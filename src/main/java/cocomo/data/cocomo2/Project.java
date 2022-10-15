package cocomo.data.cocomo2;

public enum Project {
    EARLY_DESIGN("Попередня оцінка", 2.94, 0.91, 3.67, 0.28),
    POST_ARCHITECTURE("Детальна оцінка", 2.45, 0.91, 3.67, 0.28);

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
