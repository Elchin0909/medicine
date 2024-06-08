import java.util.Scanner;

public class OrderService {
    private AdminService adminService;

    public OrderService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void placeOrder(Scanner scanner, User user) {
        System.out.println("Select branch:");
        for (int i = 0; i < adminService.getBranches().size(); i++) {
            System.out.println((i + 1) + ". " + adminService.getBranches().get(i).getName());
        }
        int branchChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (branchChoice > 0 && branchChoice <= adminService.getBranches().size()) {
            PharmacyBranch selectedBranch = adminService.getBranches().get(branchChoice - 1);

            System.out.println("Select category:");
            for (int i = 0; i < adminService.getCategories().size(); i++) {
                System.out.println((i + 1) + ". " + adminService.getCategories().get(i).getName());
            }
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (categoryChoice > 0 && categoryChoice <= adminService.getCategories().size()) {
                Category selectedCategory = adminService.getCategories().get(categoryChoice - 1);

                System.out.println("Select medicine:");
                for (int i = 0; i < adminService.getMedicines().size(); i++) {
                    Medicine medicine = adminService.getMedicines().get(i);
                    if (medicine.getCategory().equals(selectedCategory)) {
                        System.out.println((i + 1) + ". " + medicine.getName() + " - " + medicine.getPrice());
                    }
                }
                int medicineChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (medicineChoice > 0 && medicineChoice <= adminService.getMedicines().size()) {
                    Medicine selectedMedicine = adminService.getMedicines().get(medicineChoice - 1);

                    if (user.getBalance() >= selectedMedicine.getPrice()) {
                        Order newOrder = new Order(user, selectedMedicine, java.time.LocalDate.now(), selectedBranch);
                        adminService.getOrders().add(newOrder);
                        user.subtractBalance(selectedMedicine.getPrice());
                        System.out.println("Order placed successfully. Remaining balance: " + user.getBalance());
                    } else {
                        System.out.println("Insufficient balance. Please refill your balance.");
                    }
                } else {
                    System.out.println("Invalid medicine choice.");
                }
            } else {
                System.out.println("Invalid category choice.");
            }
        } else {
            System.out.println("Invalid branch choice.");
        }
    }
}
