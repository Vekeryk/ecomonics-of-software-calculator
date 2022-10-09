package cocomo.data;

public class Result {
    public final double peopleOnMonth;
    public final double timeAtMonth;
    public final double numberOfEmployee;

    public Result(double peopleOnMonth, double timeAtMonth, double numberOfEmployee) {
        this.peopleOnMonth = peopleOnMonth;
        this.timeAtMonth = timeAtMonth;
        this.numberOfEmployee = numberOfEmployee;
    }
}
