import java.time.LocalDate;

public class Order {
    private User user;
    private Medicine medicine;
    private LocalDate orderDate;
    private PharmacyBranch branch;

    public Order(User user, Medicine medicine, LocalDate orderDate, PharmacyBranch branch) {
        this.user = user;
        this.medicine = medicine;
        this.orderDate = orderDate;
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PharmacyBranch getBranch() {
        return branch;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user.getUsername() +
                ", medicine=" + medicine.getName() +
                ", orderDate=" + orderDate +
                ", branch=" + branch.getName() +
                '}';
    }
}
