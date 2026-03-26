import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    private List<RegisteredUsers> registeredUserListArrayList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void userManagementOptions() {
        while (true) {
            System.out.println("Welcome to E-Ryder Admininstrator Panel");
            System.out.println("What do you want to do");
            System.out.println("1.Add New Users");
            System.out.println("2.View Registered Users");
            System.out.println("3.Remove Registered Users");
            System.out.println("4.Update Registered Users");
            System.out.println("5.EXIT");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice.Please try again (enter a number 1-5)");
                sc.next();
                continue;
            }
            int num = sc.nextInt();
            sc.nextLine();

            if (num == 1) {
                addNewUsers();
            } else if (num == 2) {
                viewRegisteredUsers();
            } else if (num == 3) {
                removeRegisteredUsers();
            } else if (num == 4) {
                updateRegisteredUsers();
            } else if (num == 5) {
                System.out.println("Exiting program...");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("Invalid choice.Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.println("How many users you want to add");
        int userCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            System.out.println("name:");
            String name = sc.nextLine();
            System.out.println("emailAddress");
            String emailAddress = sc.nextLine();
            System.out.println("dateOfBirth");
            String dateOfBirth = sc.nextLine();
            System.out.println("cardNumber");
            String cardNumber = sc.nextLine();
            System.out.println("cardProvider");
            String cardProvider = sc.nextLine();
            System.out.println("cardExpiryDate");
            String cardExpiryDate = sc.nextLine();
            System.out.println("cvv");
            String cvv = sc.nextLine();
            System.out.println("userType");
            String userType = sc.nextLine();

            System.out.println("enter your last three trips");
            String[] lastThreeTrips = new String[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("date(YYYY-MM-DD)");
                String date = sc.nextLine();
                System.out.println("source");
                String source = sc.nextLine();
                System.out.println("destination");
                String destination = sc.nextLine();
                System.out.println("the pay for that trip");
                double pay = sc.nextDouble();
                sc.nextLine();
                System.out.println("feedback for this trip (press ENTER for NULL)");
                String feedback = sc.nextLine();

                StringBuilder trip = new StringBuilder();
                trip.append("Date: ").append(date)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare (€): ").append(pay)
                        .append(", Feedback: ").append(feedback.isEmpty() ? "NULL" : feedback);
                lastThreeTrips[j] = trip.toString();
            }

            RegisteredUsers newUser = new RegisteredUsers(name, emailAddress, dateOfBirth,
                    Long.parseLong(cardNumber), cardProvider, cardExpiryDate,
                    Integer.parseInt(cvv), userType, lastThreeTrips);
            registeredUserListArrayList.add(newUser);
            System.out.println("User added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUserListArrayList.isEmpty()) {
            System.out.println("No registered users to display");
        } else {
            for (RegisteredUsers newUser : registeredUserListArrayList) {
                System.out.println("\n--- User Info ---");
                System.out.println("name:" + newUser.getFullName()
                        + " emailAddress:" + newUser.getEmailAddress()
                        + " dateOfBirth:" + newUser.getDateOfBirth()
                        + " cardNumber:" + newUser.getCardNumber()
                        + " cardProvider:" + newUser.getCardProvider()
                        + " cardExpiryDate:" + newUser.getCardExpiryDate()
                        + " cvv:" + newUser.getCvv()
                        + " userType:" + newUser.getUserType());

                String[] trips = newUser.getLastThreeTrips();
                for (int a = 0; a < trips.length; a++) {
                    System.out.println("threetrips:" + trips[a]);
                }
                System.out.println("-------------------");
            }
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUserListArrayList.isEmpty()) {
            System.out.println("No registered users to remove");
        } else {
            System.out.println("enter the address you want to remove");
            String targetEmail = sc.nextLine();
            boolean found = false;

            Iterator<RegisteredUsers> iterator = registeredUserListArrayList.iterator();
            while (iterator.hasNext()) {
                RegisteredUsers user = iterator.next();
                if (user.getEmailAddress().equals(targetEmail)) {
                    iterator.remove();
                    found = true;
                    System.out.println("User removed successfully!");
                    break;
                }
            }

            if (!found) {
                System.out.println("No user found with this email address");
            }
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUserListArrayList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        System.out.println("enter the email address of the user to update");
        String targetEmail = sc.nextLine();
        RegisteredUsers targetUser = null;

        for (RegisteredUsers user : registeredUserListArrayList) {
            if (user.getEmailAddress().equals(targetEmail)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\n--- Update User Info (Press ENTER for no change, enter 0 for number no change) ---");
        System.out.print("new full name (" + targetUser.getFullName() + "): ");
        String newFullName = sc.nextLine();
        if (!newFullName.isEmpty()) targetUser.setFullName(newFullName);

        System.out.print("new emailAddress (" + targetUser.getEmailAddress() + "): ");
        String newEmail = sc.nextLine();
        if (!newEmail.isEmpty()) targetUser.setEmailAddress(newEmail);

        System.out.print("new dateOfBirth (" + targetUser.getDateOfBirth() + "): ");
        String newDob = sc.nextLine();
        if (!newDob.isEmpty()) targetUser.setDateOfBirth(newDob);

        System.out.print("new cardProvider (" + targetUser.getCardProvider() + "): ");
        String newProvider = sc.nextLine();
        if (!newProvider.isEmpty()) targetUser.setCardProvider(newProvider);

        System.out.print("new cardExpiryDate (" + targetUser.getCardExpiryDate() + "): ");
        String newExpiry = sc.nextLine();
        if (!newExpiry.isEmpty()) targetUser.setCardExpiryDate(newExpiry);

        System.out.print("new userType (" + targetUser.getUserType() + "): ");
        String newUserType = sc.nextLine();
        if (!newUserType.isEmpty()) targetUser.setUserType(newUserType);

        System.out.print("new cardNumber (" + targetUser.getCardNumber() + "): ");
        String newCardNum = sc.nextLine();
        if (!newCardNum.equals("0")) targetUser.setCardNumber(Long.parseLong(newCardNum));

        System.out.print("new cvv (" + targetUser.getCvv() + "): ");
        String newCvv = sc.nextLine();
        if (!newCvv.equals("0")) targetUser.setCvv(Integer.parseInt(newCvv));

        System.out.println("User updated successfully!");
    }
}
