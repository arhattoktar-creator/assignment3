package controller;

import model.Student;
import service.StudentService;

public class StudentController {

    private final StudentService service = new StudentService();

    public void create(Student s) {
        service.addStudent(s);
        System.out.println("✅ Student created");
    }

    public void showAll() {
        System.out.println("📋 Students list:");
        service.getAllStudents()
                .forEach(st -> System.out.println("  " + st.getInfo()));
    }

    public void update(Student newData) {
        service.updateStudent(newData);
        System.out.println("✏️ Student updated");
    }

    public void delete(int id) {
        service.deleteStudent(id);
        System.out.println("🗑️ Student deleted: id=" + id);
    }
}
