import static org.junit.Assert.*;
import org.junit.Test;

public class HotelReservationValidatorTest {


    // Equivalence Partitioning Tests

    // EP1: All valid inputs
    @Test
    public void testValidReservation_AllValidInputs() {
        assertTrue(HotelReservationValidator.isValidReservation(
                2, 5, 25, 150.00, "ABC123", "test@example.com"));
    }

    // EP2: Invalid guests (<1)
    @Test
    public void testInvalidGuests_TooFew() {
        assertFalse(HotelReservationValidator.isValidReservation(
                0, 5, 25, 150.00, "ABC123", "test@example.com"));
    }

    // EP3: Invalid nights (>14)
    @Test
    public void testInvalidNights_TooMany() {
        assertFalse(HotelReservationValidator.isValidReservation(
                2, 15, 25, 150.00, "ABC123", "test@example.com"));
    }

    // EP4: Invalid age (<18)
    @Test
    public void testInvalidAge_Under18() {
        assertFalse(HotelReservationValidator.isValidReservation(
                2, 5, 17, 150.00, "ABC123", "test@example.com"));
    }

    // EP5: Invalid rate (<80)
    @Test
    public void testInvalidRate_TooLow() {
        assertFalse(HotelReservationValidator.isValidReservation(
                2, 5, 25, 79.99, "ABC123", "test@example.com"));
    }

    // EP6: Invalid promo code (wrong length)
    @Test
    public void testInvalidPromoCode_WrongLength() {
        assertFalse(HotelReservationValidator.isValidReservation(
                2, 5, 25, 150.00, "ABC12", "test@example.com"));
    }

    // EP7: Invalid email (missing @)
    @Test
    public void testInvalidEmail_NoAtSymbol() {
        assertFalse(HotelReservationValidator.isValidReservation(
                2, 5, 25, 150.00, "ABC123", "testexample.com"));
    }

    // EP8: Valid with empty promo code
    @Test
    public void testValidPromoCode_Empty() {
        assertTrue(HotelReservationValidator.isValidReservation(
                2, 5, 25, 150.00, "", "test@example.com"));
    }


    // Boundary Value Analysis Tests

    // BVA1: Guests = 0 (just below)
    @Test
    public void testGuests_BelowMinimum() {
        assertFalse(HotelReservationValidator.isValidGuests(0));
    }

    // BVA2: Guests = 1 (min)
    @Test
    public void testGuests_AtMinimum() {
        assertTrue(HotelReservationValidator.isValidGuests(1));
    }

    // BVA3: Guests = 4 (max)
    @Test
    public void testGuests_AtMaximum() {
        assertTrue(HotelReservationValidator.isValidGuests(4));
    }

    // BVA4: Guests = 5 (above max)
    @Test
    public void testGuests_AboveMaximum() {
        assertFalse(HotelReservationValidator.isValidGuests(5));
    }

    // BVA5: Nights = 0 (below min)
    @Test
    public void testNights_BelowMinimum() {
        assertFalse(HotelReservationValidator.isValidNights(0));
    }

    // BVA6: Nights = 14 (max)
    @Test
    public void testNights_AtMaximum() {
        assertTrue(HotelReservationValidator.isValidNights(14));
    }

    // BVA7: Age = 17 (below min)
    @Test
    public void testAge_BelowMinimum() {
        assertFalse(HotelReservationValidator.isValidAge(17));
    }

    // BVA8: Age = 18 (min)
    @Test
    public void testAge_AtMinimum() {
        assertTrue(HotelReservationValidator.isValidAge(18));
    }

    // BVA9: Rate = 80.00 (min)
    @Test
    public void testRate_AtMinimum() {
        assertTrue(HotelReservationValidator.isValidRate(80.00));
    }

    // BVA10: Rate = 500.00 (max)
    @Test
    public void testRate_AtMaximum() {
        assertTrue(HotelReservationValidator.isValidRate(500.00));
    }

    // BVA11: Rate = 79.99 (below min)
    @Test
    public void testRate_BelowMinimum() {
        assertFalse(HotelReservationValidator.isValidRate(79.99));
    }

    // BVA12: Rate = 500.01 (above max)
    @Test
    public void testRate_AboveMaximum() {
        assertFalse(HotelReservationValidator.isValidRate(500.01));
    }
}