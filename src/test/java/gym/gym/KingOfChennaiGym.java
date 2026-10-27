package gym.gym;




import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KingOfChennaiGym {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> clients = new HashMap<>();
    static Map<String, String> memberships = new HashMap<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("      KING OF CHENNAI GYM");
            System.out.println("   Poonamallee, Chennai");
            System.out.println("==================================");
            System.out.println("1. Client Registration");
            System.out.println("2. Client Login");
            System.out.println("3. Membership Plans");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = readChoice();

            switch (choice) {
                case 1:
                    registerClient();
                    break;
                case 2:
                    loginClient();
                    break;
                case 3:
                    showPlans();
                    break;
                case 4:
                    System.out.println("Thank you for visiting King of Chennai Gym!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    static int readChoice() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void registerClient() {
        System.out.println("\n--- Client Registration ---");

        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();

        System.out.print("Create username: ");
        String username = sc.nextLine().trim();

        if (name.isEmpty() || username.isEmpty()) {
            System.out.println("Name and username cannot be empty.");
            return;
        }

        if (clients.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }

        System.out.print("Create password: ");
        String password = sc.nextLine();

        if (password.length() < 6) {
            System.out.println("Password must have at least 6 characters.");
            return;
        }

        clients.put(username, password);

        System.out.println("Choose membership:");
        showPlans();

        System.out.print("Enter plan number (1-3): ");
        int plan = readChoice();

        if (plan < 1 || plan > 3) {
            clients.remove(username);
            System.out.println("Invalid plan. Registration cancelled.");
            return;
        }

        String[] planNames = {
            "Monthly", "Quarterly", "Yearly"
        };

        memberships.put(username, planNames[plan - 1]);

        System.out.println("\nRegistration successful!");
        System.out.println("Welcome to King of Chennai Gym, " + name + "!");
    }

    static void loginClient() {
        System.out.println("\n--- Client Login ---");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (clients.containsKey(username)
                && clients.get(username).equals(password)) {

            System.out.println("\nLogin successful!");
            System.out.println("Welcome, " + username + "!");
            showDashboard(username);

        } else {
            System.out.println("Invalid username or password.");
        }
    }

    static void showDashboard(String username) {
        int choice;

        do {
            System.out.println("\n--- CLIENT DASHBOARD ---");
            System.out.println("Gym: King of Chennai");
            System.out.println("Location: Poonamallee, Chennai");
            System.out.println("Membership: " + memberships.get(username));
            System.out.println("1. View Membership");
            System.out.println("2. Logout");
            System.out.print("Enter your choice: ");

            choice = readChoice();

            switch (choice) {
                case 1:
                    System.out.println("Your plan: "
                            + memberships.get(username));
                    break;
                case 2:
                    System.out.println("You have logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 2);
    }

    static void showPlans() {
        System.out.println("\n--- GYM MEMBERSHIP PLANS ---");
        System.out.println("1. Monthly   - Rs. 1,000");
        System.out.println("2. Quarterly - Rs. 2,700");
        System.out.println("3. Yearly    - Rs. 9,000");
    }
}

