import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class UserRegistration {
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;
    private Scanner scanner;

    public static void main(String[] args) {
        UserRegistration registration = new UserRegistration();
        registration.scanner = new Scanner(System.in);
        registration.registration();
        System.out.println("\n" + registration.toString());
        registration.scanner.close();
    }

    public void registration() {
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2):");

        int choice = 0;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice! Please enter 1 or 2:");
                }
            } else {
                System.out.println("Invalid input! Please enter a number (1 or 2):");
                scanner.next();
            }
        }
        scanner.nextLine();

        if (choice == 1) {
            userType = "Regular User";
        } else if (choice == 2) {
            userType = "VIP User";
        }

        System.out.println("Enter your full name:");
        fullName = scanner.nextLine();

        do {
            System.out.println("Enter your email address:");
            emailAddress = scanner.nextLine();
            emailValid = analyseEmail(emailAddress);
        } while (!emailValid);

        LocalDate dob = null;
        ageValid = false;
        while (!ageValid) {
            System.out.println("Enter your date of birth (YYYY-MM-DD):");
            dateOfBirth = scanner.nextLine();
            try {
                dob = LocalDate.parse(dateOfBirth);
                ageValid = analyseAge(dob);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please use YYYY-MM-DD (e.g., 2000-01-01).");
                ageValid = false;
            }
        }

        cardNumberValid = false;
        while (!cardNumberValid) {
            System.out.println("Enter your card number (Visa, MasterCard, American Express only):");
            if (scanner.hasNextLong()) {
                cardNumber = scanner.nextLong();
                scanner.nextLine();
                cardNumberValid = analyseCardNumber(cardNumber);
            } else {
                System.out.println("Invalid card number! Please enter only digits.");
                scanner.next();
                cardNumberValid = false;
            }
        }

        cardStillValid = false;
        while (!cardStillValid) {
            System.out.println("Enter your card expiry date (MM/YY):");
            cardExpiryDate = scanner.nextLine();
            if (cardExpiryDate.matches("^\\d{2}/\\d{2}$")) {
                cardStillValid = analyseCardExpiryDate(cardExpiryDate);
            } else {
                System.out.println("Invalid expiry date format! Please use MM/YY (e.g., 12/25).");
                cardStillValid = false;
            }
        }

        validCVV = false;
        while (!validCVV) {
            System.out.println("Enter your card CVV:");
            if (scanner.hasNextInt()) {
                cvv = scanner.nextInt();
                scanner.nextLine();
                validCVV = analyseCVV(cvv);
            } else {
                System.out.println("Invalid CVV! Please enter only digits.");
                scanner.next();
                validCVV = false;
            }
        }

        chargeFees();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Please re-enter a valid email.");
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();

        boolean isBirthday = (dob.getMonthValue() == currentDate.getMonthValue())
                && (dob.getDayOfMonth() == currentDate.getDayOfMonth());

        if (age <= 12 || age > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
        }

        if ("VIP User".equals(userType)) {
            if (isBirthday && age <= 18 && age > 12) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && age <= 18 && age > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }

        return true;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = String.valueOf(cardNumber);
        int firstTwoDigits = 0;
        int firstFourDigits = 0;

        if (cardNumStr.length() >= 2) {
            firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        }
        if (cardNumStr.length() >= 4) {
            firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        }

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 16) && cardNumStr.startsWith("4")) { // Visa是13或16位
            cardProvider = "VISA";
            System.out.println("Card number is valid (VISA).");
            return true;
        } else if (cardNumStr.length() == 16 &&
                ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            System.out.println("Card number is valid (MasterCard).");
            return true;
        } else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            System.out.println("Card number is valid (American Express).");
            return true;
        } else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please re-enter a valid card number.");
            return false;
        }
    }

    private boolean analyseCardExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000;


        if (month < 1 || month > 12) {
            System.out.println("Invalid month! Month must be between 01 and 12. Please re-enter a valid expiry date.");
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card and re-enter expiry date.");
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);

        if ((cardProvider.equals("American Express") && cvvStr.length() == 4) ||
                ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card. Please re-enter a valid CVV.");
            return false;
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);

        System.out.printf("Thank you for your payment.%n");
        System.out.printf("A fee of %.2f has been charged to your card ending with %s%n", feeToCharge, lastFourDigits);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;
        
        return "Registration successful! Here are your details:\n" +
                "User Type: " + userType + "\n" +
                "Full Name: " + fullName + "\n" +
                "Email Address: " + emailAddress + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Card Number: " + censoredNumber + "\n" +
                "Card Provider: " + cardProvider + "\n" +
                "Card Expiry Date: " + cardExpiryDate;
    }
}
