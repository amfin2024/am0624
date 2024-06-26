package org.company;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tool {
    private String code;
    private ToolType type;
    private String brand;

    public String getTypeName() {
        return type.getName();
    }

    public double getDailyFee() {
        return type.getDailyFee();
    }

    public boolean isWeekdayCharge() {
        return type.isWeekdayCharge();
    }

    public boolean isWeekendCharge() {
        return type.isWeekendCharge();
    }

    public boolean isHolidayCharge() {
        return type.isHolidayCharge();
    }
}
