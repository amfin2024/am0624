package org.company;

public enum ToolType {
    LADDER("Ladder", 1.99, true, true, false),
    CHAINSAW("Chainsaw", 1.49, true, false, true),
    JACKHAMMER("Jackhammer", 2.99, true, false, false);

    private final String name;
    private final double dailyFee;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    ToolType(String name, double dailyFee, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.name = name;
        this.dailyFee = dailyFee;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getName() {
        return name;
    }

    public double getDailyFee() {
        return dailyFee;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
