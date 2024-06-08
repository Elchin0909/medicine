import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.fatboyindustrial.gsonjavatime.Converters;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> users;
    private List<Medicine> medicines;
    private List<PharmacyBranch> branches;
    private List<Order> orders;
    private List<Category> categories;
    private final String USERS_FILE = "users.json";
    private final String MEDICINES_FILE = "medicines.json";
    private final String BRANCHES_FILE = "branches.json";
    private final String ORDERS_FILE = "orders.json";
    private final String CATEGORIES_FILE = "categories.json";

    private Gson gson;

    public Database() {
        this.gson = Converters.registerLocalDate(new GsonBuilder()).create();

        this.users = loadData(USERS_FILE, new TypeToken<List<User>>(){}.getType());
        if (this.users == null) {
            this.users = new ArrayList<>();
            this.users.add(new User("admin", "admin", "admin"));
            saveData(this.users, USERS_FILE);
        }

        this.medicines = loadData(MEDICINES_FILE, new TypeToken<List<Medicine>>(){}.getType());
        if (this.medicines == null) {
            this.medicines = new ArrayList<>();
            saveData(this.medicines, MEDICINES_FILE);
        }

        this.branches = loadData(BRANCHES_FILE, new TypeToken<List<PharmacyBranch>>(){}.getType());
        if (this.branches == null) {
            this.branches = new ArrayList<>();
            saveData(this.branches, BRANCHES_FILE);
        }

        this.orders = loadData(ORDERS_FILE, new TypeToken<List<Order>>(){}.getType());
        if (this.orders == null) {
            this.orders = new ArrayList<>();
            saveData(this.orders, ORDERS_FILE);
        }

        this.categories = loadData(CATEGORIES_FILE, new TypeToken<List<Category>>(){}.getType());
        if (this.categories == null) {
            this.categories = new ArrayList<>();
            saveData(this.categories, CATEGORIES_FILE);
        }
    }

    public List<User> getUsers() {
        return users;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void saveUsers() {
        saveData(users, USERS_FILE);
    }

    public void saveMedicines() {
        saveData(medicines, MEDICINES_FILE);
    }

    public void saveBranches() {
        saveData(branches, BRANCHES_FILE);
    }

    public void saveOrders() {
        saveData(orders, ORDERS_FILE);
    }

    public void saveCategories() {
        saveData(categories, CATEGORIES_FILE);
    }

    private <T> List<T> loadData(String filename, Type type) {
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return null;
        }
    }

    private <T> void saveData(List<T> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
