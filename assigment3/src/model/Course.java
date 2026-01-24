package model;

public class Course extends BaseEntity {
    private int credits;

    public Course(int id, String name, int credits) {
        super(id, name);
        this.credits = credits;
    }

    @Override
    public String getInfo() {
        return "Course{id=" + id + ", name='" + name + "', credits=" + credits + "}";
    }

    @Override
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && credits > 0;
    }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
