package model;

public class Student extends BaseEntity {
    private String email;

    public Student(int id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    @Override
    public String getInfo() {
        return "Student: " + name + " | email: " + email;
    }

    @Override
    public boolean isValid() {
        return name != null && !name.isEmpty()
                && email != null && email.contains("@");
    }

    public String getEmail() {
        return email;
    }
}
