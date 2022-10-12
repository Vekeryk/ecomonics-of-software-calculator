package cocomo.data;

public enum Priority {
    VERY_LOW(false),
    LOW(false),
    AVERAGE(true),
    HIGH(false),
    VERY_HIGH(false),
    CRITICAL(false);

    public final boolean selected;

    Priority(boolean selected) {
        this.selected = selected;
    }
}
