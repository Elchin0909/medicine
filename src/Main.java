import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);

        AdminService adminService = new AdminService(database);
        UserService userService = new UserService(database);
        OrderService orderService = new OrderService(adminService);

        while (true) {
            System.out.println("Welcome to the Pharmacy System");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = null;
                for (User u : database.getUsers()) {
                    if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        user = u;
                        break;
                    }
                }

                if (user == null) {
                    System.out.println("Invalid username or password.");
                    continue;
                }

                if (user.getRole().equals("admin")) {
                    adminService.adminPanel(scanner);
                } else {
                    userService.userPanel(user, scanner);
                }
            } else if (choice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter role (admin/user): ");
                String role = scanner.nextLine();

                User newUser = new User(username, password, role);
                database.getUsers().add(newUser);
                database.saveUsers();
                System.out.println("User signed up successfully.");
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
