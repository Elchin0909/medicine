import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private Database database;
    public static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public UserService(Database database) {
        this.database = database;
    }

    public void userPanel(User user, Scanner scanner) {
        while (true) {
            System.out.println("User Panel");
            System.out.println("1. Order Medicine");
            System.out.println("2. View Balance");
            System.out.println("3. Refill Balance");
            System.out.println("4. Log Out");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                orderMedicine(user, scanner);
            } else if (choice == 2) {
                System.out.println("Your balance: " + user.getBalance());
            } else if (choice == 3) {
                refillBalance(user, scanner);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void orderMedicine(User user, Scanner scanner) {
        // Display available branches
        System.out.println("Available Branches:");
        System.out.println("1. Yunusobod");
        System.out.println("2. Chilonzor");
        System.out.println("3. Olmazor");

        System.out.print("Enter the number of the branch from where you want to order: ");
        int branchIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PharmacyBranch pharmacyBranch;
        switch (branchIndex) {
            case 1:
                pharmacyBranch = new PharmacyBranch("Yunusobod");
                break;
            case 2:
                pharmacyBranch = new PharmacyBranch("Chilonzor");
                break;
            case 3:
                pharmacyBranch = new PharmacyBranch("Olmazor");
                break;
            default:
                System.out.println("Invalid branch selection.");
                return;
        }

        List<Category> categories = database.getCategories();
        System.out.println("Available Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }

        System.out.print("Enter the number of the category of the medicine you want to order: ");
        int categoryIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (categoryIndex < 1 || categoryIndex > categories.size()) {
            System.out.println("Invalid category selection.");
            return;
        }

        String categoryName = categories.get(categoryIndex - 1).getName();

        // Display medicines under the selected category
        System.out.println("Medicines under " + categoryName + " category:");
        List<Medicine> categoryMedicines = new ArrayList<>();
        for (Medicine medicine : database.getMedicines()) {
            if (medicine.getCategory().getName().equalsIgnoreCase(categoryName)) {
                categoryMedicines.add(medicine);
            }
        }

        if (categoryMedicines.isEmpty()) {
            System.out.println("No medicines found in this category.");
            return;
        }

        for (int i = 0; i < categoryMedicines.size(); i++) {
            Medicine medicine = categoryMedicines.get(i);
            System.out.println((i + 1) + ". " + medicine.getName() + " - Price: " + medicine.getPrice());
        }

        System.out.print("Enter the number of the medicine you want to order: ");
        int medicineIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (medicineIndex < 1 || medicineIndex > categoryMedicines.size()) {
            System.out.println("Invalid medicine selection.");
            return;
        }

        Medicine selectedMedicine = categoryMedicines.get(medicineIndex - 1);

        if (user.getBalance() < selectedMedicine.getPrice()) {
            System.out.println("Insufficient balance. Please refill your balance.");
            return;
        }

        // Proceed with the order
        Order newOrder = new Order(user, selectedMedicine, java.time.LocalDate.now(), pharmacyBranch);
        database.getOrders().add(newOrder);
        database.saveOrders();
        user.subtractBalance(selectedMedicine.getPrice());
        database.saveUsers();
        System.out.println("Order placed successfully from " + pharmacyBranch.getName() + " branch. Remaining balance: " + user.getBalance());
    }

    private void refillBalance(User user, Scanner scanner) {
        System.out.print("Enter amount to refill: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        user.addBalance(amount);
        database.saveUsers();
        System.out.println("Balance refilled successfully. Current balance: " + user.getBalance());
    }
}

