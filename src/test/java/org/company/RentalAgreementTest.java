package org.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentalAgreementTest {

    @Test
    public void testCalculateChargeableDays() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("CHNS", 5, 0, "07/02/15");
        assertEquals(3, agreement.calculateChargeableDays(), "Chainsaw: 5 days from 07/02/15 should have 3 chargeable days");

        agreement = ToolRentalApp.generateRentalAgreement("LADW", 3, 0, "07/02/20");
        assertEquals(2, agreement.calculateChargeableDays(), "Ladder: 3 days from 07/02/20 should have 2 chargeable days");

        agreement = ToolRentalApp.generateRentalAgreement("JAKD", 6, 0, "09/03/15");
        assertEquals(3, agreement.calculateChargeableDays(), "Jackhammer: 6 days from 09/03/15 should have 6 chargeable days");

        agreement = ToolRentalApp.generateRentalAgreement("JAKR", 9, 0, "07/02/15");
        assertEquals(6, agreement.calculateChargeableDays(), "Jackhammer: 9 days from 07/02/15 should have 9 chargeable days");
    }

    @Test
    public void testCalculateFinalCharge() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("CHNS", 5, 25, "07/02/15");
        double preDiscountCharge = 3 * agreement.getTool().getDailyFee();
        double discountAmount = preDiscountCharge * 0.25;
        double finalCharge = preDiscountCharge - discountAmount;
        assertEquals(finalCharge, agreement.calculateFinalCharge(), 0.01, "Chainsaw: 5 days from 07/02/15 with 25% discount");

        agreement = ToolRentalApp.generateRentalAgreement("LADW", 3, 10, "07/02/20");
        preDiscountCharge = 2 * agreement.getTool().getDailyFee();
        discountAmount = preDiscountCharge * 0.10;
        finalCharge = preDiscountCharge - discountAmount;
        assertEquals(finalCharge, agreement.calculateFinalCharge(), 0.01, "Ladder: 3 days from 07/02/20 with 10% discount");

        agreement = ToolRentalApp.generateRentalAgreement("JAKD", 6, 0, "09/03/15");
        preDiscountCharge = 3 * agreement.getTool().getDailyFee();
        finalCharge = preDiscountCharge;
        assertEquals(finalCharge, agreement.calculateFinalCharge(), 0.01, "Jackhammer: 6 days from 09/03/15 with 0% discount");

        agreement = ToolRentalApp.generateRentalAgreement("JAKR", 9, 50, "07/02/15");
        preDiscountCharge = 6 * agreement.getTool().getDailyFee();
        discountAmount = preDiscountCharge * 0.50;
        finalCharge = preDiscountCharge - discountAmount;
        assertEquals(finalCharge, agreement.calculateFinalCharge(), 0.01, "Jackhammer: 9 days from 07/02/15 with 50% discount");
    }
}
