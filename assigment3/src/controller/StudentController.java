package controller;

import model.Student;
import service.StudentService;

public class StudentController {

    private final StudentService service = new StudentService();

    public int create(Student s) {
        int id = service.addStudent(s);
        System.out.println("✅ Student created with id=" + id);
        return id;
    }

    public void showAll() {
        System.out.println("📋 Students list:");
        service.getAllStudents().forEach(st -> System.out.println("  " + st.getInfo()));
    }

    public void update(int id, Student newData) {
        service.updateStudent(id, newData);
        System.out.println("✏️ Student updated: id=" + id);
    }

    public void delete(int id) {
        service.deleteStudent(id);
        System.out.println("🗑️ Student deleted: id=" + id);
    }
}
