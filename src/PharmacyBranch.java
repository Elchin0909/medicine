public class PharmacyBranch {
    private String name;

    public PharmacyBranch(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PharmacyBranch{" +
                "name='" + name + '\'' +
                '}';
    }
}

