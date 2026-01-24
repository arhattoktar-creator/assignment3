package controller;

import service.EnrollmentService;

public class EnrollmentController {

    private final EnrollmentService service = new EnrollmentService();

    public void enroll(int studentId, int courseId) {
        service.enroll(studentId, courseId);
        System.out.println("🎫 Enrollment created: studentId=" + studentId + " -> courseId=" + courseId);
    }
}

