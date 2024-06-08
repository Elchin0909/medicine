public class User {
    private String username;
    private String password;
    private String role;
    private double balance;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = 1000.0; // Initial balance for users
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void subtractBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", balance=" + balance +
                '}';
    }
}
