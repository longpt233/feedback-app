package model;

public class GroupHasLetter {
    private String id_letter;
    private int id_group;

    public GroupHasLetter() {
    }

    public GroupHasLetter(String id_letter, int id_group) {
        this.id_group = id_group;
        this.id_letter = id_letter;
    }

    @Override
    public String toString() {
        return "GroupHasLetter{" +
                ", id_group=" + id_group +
                ", id_letter='" + id_letter + '\'' +
                '}';
    }


    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getId_letter() {
        return id_letter;
    }

    public void setId_letter(String id_letter) {
        this.id_letter = id_letter;
    }
}
