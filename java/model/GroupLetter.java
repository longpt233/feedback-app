package model;
public class GroupLetter {
    private int id;
    private String name;
    private int status;
    private int quantity;

    public GroupLetter() {
    }

    public GroupLetter(int id, String name, int status, int quantity) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "GroupLetter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
