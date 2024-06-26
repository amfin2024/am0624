package org.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Month;
import java.time.DayOfWeek;
import java.text.NumberFormat;

public class RentalAgreement {
    private Tool tool;
    private LocalDate checkoutDate;
    private int rentalDays;
    private double discount;

    public RentalAgreement(Tool tool, LocalDate checkoutDate, int rentalDays, double discount) {
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discount = discount;
    }

    public LocalDate getDueDate() {
        return checkoutDate.plusDays(rentalDays);
    }

    public int calculateChargeableDays() {
        int chargeableDays = 0;
        LocalDate currentDate = checkoutDate.plusDays(0);

        for (int i = 0; i < rentalDays; i++) {
            boolean isChargeableDay = isChargeableDay(currentDate);
            // System.out.println(getTool().getCode() + ":" + currentDate + ", chargeable:" + isChargeableDay);
            if (isChargeableDay) {
                chargeableDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return chargeableDays;
    }

    public double calculatePreDiscountCharge() {
        return calculateChargeableDays() * tool.getDailyFee();
    }

    public double calculateDiscountAmount() {
        return calculatePreDiscountCharge() * (discount / 100.0);
    }

    public double calculateFinalCharge() {
        return calculatePreDiscountCharge() - calculateDiscountAmount();
    }

    public Tool getTool() {
        return this.tool;
    }
    private boolean isChargeableDay(LocalDate date) {
        boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        boolean isHoliday = isHoliday(date);

        if ((isWeekend && !tool.isWeekendCharge()) || (isHoliday && !tool.isHolidayCharge())) {
            return false;
        }

        if (!isWeekend && !isHoliday && !tool.isWeekdayCharge()) {
            return false;
        }

        return true;
    }

    private boolean isHoliday(LocalDate date) {
        // Independence Day
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }

        // Labor Day
        LocalDate laborDay = getLaborDay(date.getYear());

        return date.equals(independenceDay) || date.equals(laborDay);
    }

    private static LocalDate getLaborDay(int year) {
        // Labor Day in the US is the first Monday of September
        LocalDate firstDayOfSeptember = LocalDate.of(year, Month.SEPTEMBER, 1);
        int dayOfWeek = firstDayOfSeptember.getDayOfWeek().getValue();
        int daysToAdd = (dayOfWeek == 1) ? 0 : (8 - dayOfWeek);
        return firstDayOfSeptember.plusDays(daysToAdd);
    }

    public void printAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        percentFormatter.setMinimumFractionDigits(0);

        System.out.println("Tool code: " + tool.getCode());
        System.out.println("Tool type: " + tool.getType());
        System.out.println("Tool brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Check out date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + getDueDate().format(dateFormatter));
        System.out.println("Daily rental charge: " + currencyFormatter.format(tool.getDailyFee()));
        System.out.println("Chargeable days: " + calculateChargeableDays());
        System.out.println("Pre-discount charge: " + currencyFormatter.format(calculatePreDiscountCharge()));
        System.out.println("Discount percent: " + percentFormatter.format(discount / 100.0));
        System.out.println("Discount amount: " + currencyFormatter.format(calculateDiscountAmount()));
        System.out.println("Final charge: " + currencyFormatter.format(calculateFinalCharge()));
    }
}
