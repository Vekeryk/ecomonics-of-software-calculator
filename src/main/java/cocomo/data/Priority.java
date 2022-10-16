package cocomo.data;

public enum Priority {
    EXTRA_LOW(false, "Наднизький"),
    VERY_LOW(false, "Дуже низький"),
    LOW(false, "Низький"),
    AVERAGE(true, "Середній"),
    HIGH(false, "Високий"),
    VERY_HIGH(false, "Дуже високий"),
    EXTRA_HIGH(false, "Надвисокий");

    public final boolean selected;
    public final String name;

    Priority(boolean selected, String name) {
        this.selected = selected;
        this.name = name;
    }
}
