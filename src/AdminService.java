import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private List<Category> categories = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();
    private List<PharmacyBranch> branches = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public AdminService(Database database) {
        initializeCategories();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public List<PharmacyBranch> getBranches() {
        return branches;
    }

    public List<Order> getOrders() {
        return orders;
    }

    private void initializeCategories() {
        categories.add(new Category("Painkillers"));
        categories.add(new Category("Antibiotics"));
        categories.add(new Category("Vitamins"));


        medicines.add(new Medicine("Paracetamol", "Painkillers",2000., categories.get(0)));
        medicines.add(new Medicine("Ibuprofen", "Painkillers",2000., categories.get(0)));
        medicines.add(new Medicine("Amoxicillin", "Antibiotics", 3000.0,categories.get(1)));
        medicines.add(new Medicine("Azithromycin", "Antibiotics", 40000.,categories.get(1)));
        medicines.add(new Medicine("Vitamin C", "Vitamins", 50000,categories.get(2)));
        medicines.add(new Medicine("Vitamin D", "Vitamins",6000, categories.get(2)));
    }

        public void adminPanel(Scanner scanner) {
        while (true) {
            System.out.println("Admin Panel");
            System.out.println("1. View All Users");
            System.out.println("2. View All Orders");
            System.out.println("3. Add Medicine");
            System.out.println("4. Add Pharmacy Branch");
            System.out.println("5. View Orders by Branch");
            System.out.println("6. Log Out");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                viewAllUsers();
            } else if (choice == 2) {
                viewAllOrders();
            } else if (choice == 3) {
                addMedicine(scanner);
            } else if (choice == 4) {
                addPharmacyBranch(scanner);
            } else if (choice == 5) {
                viewOrdersByBranch(scanner);
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void viewAllUsers() {
        System.out.println("All Users:");
        for (User user : UserService.users) {
            System.out.println(user);
        }
    }



    private void viewAllOrders() {
        System.out.println("All Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void addMedicine(Scanner scanner) {
        System.out.print("Enter medicine name: ");
        String medicineName = scanner.nextLine();
        System.out.println("Select category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (categoryChoice > 0 && categoryChoice <= categories.size()) {
            Medicine newMedicine = new Medicine(medicineName, categories.get(categoryChoice - 1));
            medicines.add(newMedicine);
            System.out.println("Medicine added successfully.");
        } else {
            System.out.println("Invalid category.");
        }
    }

    private void addPharmacyBranch(Scanner scanner) {
        System.out.print("Enter pharmacy branch name: ");
        String branchName = scanner.nextLine();
        PharmacyBranch newBranch = new PharmacyBranch(branchName);
        branches.add(newBranch);
        System.out.println("Pharmacy branch added successfully.");
    }
    private void addPharmacyBranch(){
        String branchName="yunusobod";
        String branchName2="chilonzor";
        String branchName3="olmazor";
        PharmacyBranch newBranch=new PharmacyBranch(branchName);
        PharmacyBranch newBranch2=new PharmacyBranch(branchName2);
        PharmacyBranch newBranch3=new PharmacyBranch(branchName3);
        branches.add(newBranch);
        branches.add(newBranch2);
        branches.add(newBranch3);
    }

    private void viewOrdersByBranch(Scanner scanner) {
        System.out.println("Select branch:");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + ". " + branches.get(i).getName());
        }
        int branchChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (branchChoice > 0 && branchChoice <= branches.size()) {
            PharmacyBranch selectedBranch = branches.get(branchChoice - 1);
            System.out.println("Orders for " + selectedBranch.getName() + ":");
            for (Order order : orders) {
                if (order.getBranch().equals(selectedBranch)) {
                    System.out.println(order);
                }
            }
        } else {
            System.out.println("Invalid branch.");
        }
    }
}

