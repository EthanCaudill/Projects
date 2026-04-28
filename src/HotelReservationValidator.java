public class HotelReservationValidator {
    public static boolean isValidReservation(int guests, int nights, int age,
                                             double rate, String promoCode, String email) {
        return isValidGuests(guests)
                && isValidNights(nights)
                && isValidAge(age)
                && isValidRate(rate)
                && isValidPromoCode(promoCode)
                && isValidEmail(email);
    }
    public static boolean isValidGuests(int guests) {
        return guests >= 1 && guests <= 4;
    }
    public static boolean isValidNights(int nights) {
        return nights >= 1 && nights <= 14;
    }
    public static boolean isValidAge(int age) {
        return age >= 18;
    }
    public static boolean isValidRate(double rate) {
        return rate >= 80.00 && rate <= 500.00 && hasAtMostTwoDecimalPlaces(rate);
    }
    public static boolean isValidPromoCode(String promoCode) {
        if (promoCode == null || promoCode.isEmpty()) {
            return true;
        }
        return promoCode.matches("[A-Za-z0-9]{6}");
    }
    public static boolean isValidEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        return email.endsWith(".com")
                || email.endsWith(".edu")
                || email.endsWith(".org");
    }
    private static boolean hasAtMostTwoDecimalPlaces(double value) {
        return Math.round(value * 100) == value * 100;
    }
}