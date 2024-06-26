package org.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToolRentalAppTest {
    @Test
    public void testRentalAgreementInvalidDiscount() {
        assertThrows(ToolRentalException.class, () -> ToolRentalApp.generateRentalAgreement("JAKR", 5, 101.0, "09/03/15"));
    }

    @Test
    public void testRentalAgreementLADW() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("LADW", 3, 10.0, "07/02/20");
        assertEquals(2, agreement.calculateChargeableDays());
    }

    @Test
    public void testRentalAgreementCHNS() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("CHNS", 5, 25.0, "07/02/15");
        assertEquals(3, agreement.calculateChargeableDays());
    }

    @Test
    public void testRentalAgreementJAKD() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("JAKD", 6, 0.0, "09/03/15");
        assertEquals(3, agreement.calculateChargeableDays());
    }

    @Test
    public void testRentalAgreementJAKR_9days() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("JAKR", 9, 0.0, "07/02/15");
        assertEquals(6, agreement.calculateChargeableDays());
    }

    @Test
    public void testRentalAgreementJAKR_4days() throws ToolRentalException {
        RentalAgreement agreement = ToolRentalApp.generateRentalAgreement("JAKR", 4, 50.0, "07/02/20");
        assertEquals(1, agreement.calculateChargeableDays());
    }
}
