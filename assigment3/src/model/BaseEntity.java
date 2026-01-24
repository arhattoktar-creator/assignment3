package model;

public abstract class BaseEntity {
    protected int id;
    protected String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract String getInfo();
    public abstract boolean isValid();

    public int getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
